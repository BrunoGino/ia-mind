data "aws_region" "current" {}

#####################################
#    VPC IP Address Manager
#####################################
resource "aws_vpc_ipam" "iamind_vpc_ipam" {
  operating_regions {
    region_name = local.main_aws_region
  }
  tags = local.default_tags
}

resource "aws_vpc_ipam_pool" "iamind_vpc_ipam_pool" {
  address_family = "ipv4"
  ipam_scope_id  = aws_vpc_ipam.iamind_vpc_ipam.private_default_scope_id
  locale         = data.aws_region.current.name
  tags           = local.default_tags
}

resource "aws_vpc_ipam_pool_cidr" "iamind_vpc_ipam_pool_cidr" {
  ipam_pool_id = aws_vpc_ipam_pool.iamind_vpc_ipam_pool.id
  cidr         = "13.3.0.0/16"
}

#####################################
#    VPC
#####################################
resource "aws_vpc" "iamind_vpc" {
  instance_tenancy    = "default"
  ipv4_ipam_pool_id   = aws_vpc_ipam_pool.iamind_vpc_ipam_pool.id
  ipv4_netmask_length = 16

  depends_on = [aws_vpc_ipam_pool_cidr.iamind_vpc_ipam_pool_cidr]

  enable_dns_support   = true
  enable_dns_hostnames = true

  tags = merge(local.default_tags, { Name = "iamind-vpc" })
}

#####################################
#    Subnets
#####################################
resource "aws_subnet" "iamind_subnet_public1" {
  vpc_id            = aws_vpc.iamind_vpc.id
  cidr_block        = "13.3.0.0/20"
  availability_zone = "eu-west-1a"

  map_public_ip_on_launch = true

  tags = merge(local.default_tags, { Name = "iamind-public-subnet-1" })
}

resource "aws_subnet" "iamind_subnet_private1" {
  vpc_id            = aws_vpc.iamind_vpc.id
  cidr_block        = "13.3.128.0/20"
  availability_zone = "eu-west-1a"

  tags = merge(local.default_tags, { Name = "iamind-private-subnet-1" })
}

#####################################
#    Security Groups
#####################################
resource "aws_security_group" "iamind_sg_tls_http" {
  name        = "iamind_sg_tls_http"
  description = "Allow HTTP/HTTPS inbound and outbound traffic"
  vpc_id      = aws_vpc.iamind_vpc.id

  tags = local.default_tags
}

resource "aws_vpc_security_group_ingress_rule" "allow_http_in" {
  security_group_id = aws_security_group.iamind_sg_tls_http.id
  cidr_ipv4         = "0.0.0.0/0"
  from_port         = 80
  to_port           = 80
  ip_protocol       = "tcp"
  tags              = local.default_tags
}

resource "aws_vpc_security_group_ingress_rule" "allow_https_in" {
  security_group_id = aws_security_group.iamind_sg_tls_http.id
  cidr_ipv4         = "0.0.0.0/0"
  from_port         = 443
  to_port           = 443
  ip_protocol       = "tcp"
  tags              = local.default_tags
}

resource "aws_vpc_security_group_ingress_rule" "allow_ephemeral_in" {
  security_group_id = aws_security_group.iamind_sg_tls_http.id
  cidr_ipv4         = "0.0.0.0/0"
  from_port         = 1024
  to_port           = 65535
  ip_protocol       = "tcp"
  tags              = local.default_tags
}

resource "aws_vpc_security_group_egress_rule" "allow_http_traffic_ipv4" {
  security_group_id = aws_security_group.iamind_sg_tls_http.id
  cidr_ipv4         = "0.0.0.0/0"
  ip_protocol       = "-1"
  tags              = local.default_tags
}

#####################################
#    Network Access Control Lists
#####################################
resource "aws_network_acl" "iamind_main_nacl" {
  vpc_id = aws_vpc.iamind_vpc.id

  subnet_ids = [
    aws_subnet.iamind_subnet_public1.id,
    aws_subnet.iamind_subnet_private1.id
  ]

  # Ingress Rules
  ingress {
    protocol   = "tcp"
    rule_no    = 100
    action     = "allow"
    cidr_block = "0.0.0.0/0"
    from_port  = 80
    to_port    = 80
  }

  ingress {
    protocol   = "tcp"
    rule_no    = 110
    action     = "allow"
    cidr_block = "0.0.0.0/0"
    from_port  = 443
    to_port    = 443
  }

  ingress {
    protocol   = "tcp"
    rule_no    = 120
    action     = "allow"
    cidr_block = "0.0.0.0/0"
    from_port  = 1024
    to_port    = 65535
  }

  # Egress Rules
  egress {
    protocol   = "tcp"
    rule_no    = 100
    action     = "allow"
    cidr_block = "0.0.0.0/0"
    from_port  = 80
    to_port    = 80
  }

  egress {
    protocol   = "tcp"
    rule_no    = 110
    action     = "allow"
    cidr_block = "0.0.0.0/0"
    from_port  = 443
    to_port    = 443
  }

  egress {
    protocol   = "tcp"
    rule_no    = 120
    action     = "allow"
    cidr_block = "0.0.0.0/0"
    from_port  = 1024
    to_port    = 65535
  }

  tags = local.default_tags
}

#####################################
#    Internet Gateway
#####################################
resource "aws_internet_gateway" "iamind_vpc_ig" {
  vpc_id = aws_vpc.iamind_vpc.id

  tags = merge(local.default_tags, { Name : "iamind-internet-gateway" })
}

#####################################
#   Public Route Tables
#####################################
resource "aws_route_table" "iamind_rtb_public" {
  vpc_id = aws_vpc.iamind_vpc.id

  route {
    cidr_block = "0.0.0.0/0"
    gateway_id = aws_internet_gateway.iamind_vpc_ig.id
  }


  route {
    cidr_block = "13.3.0.0/16"
    gateway_id = "local"
  }

  tags = merge(local.default_tags, { Name : "iamind-public-route-table" })
}

resource "aws_route_table_association" "iamind_rtb_association_public1" {
  subnet_id      = aws_subnet.iamind_subnet_public1.id
  route_table_id = aws_route_table.iamind_rtb_public.id
}

#####################################
#   Private Route Tables
#####################################
resource "aws_route_table" "iamind_rtb_private1" {
  vpc_id = aws_vpc.iamind_vpc.id

  route {
    cidr_block = "13.3.0.0/16"
    gateway_id = "local"
  }

  tags = merge(local.default_tags, { Name : "iamind-private1-route-table" })
}

resource "aws_route_table" "iamind_rtb_private2" {
  vpc_id = aws_vpc.iamind_vpc.id

  route {
    cidr_block = "13.3.0.0/16"
    gateway_id = "local"
  }

  tags = merge(local.default_tags, { Name : "iamind-private2-route-table" })
}

resource "aws_route_table_association" "iamind_rtb_association_private1" {
  subnet_id      = aws_subnet.iamind_subnet_private1.id
  route_table_id = aws_route_table.iamind_rtb_private1.id
}
