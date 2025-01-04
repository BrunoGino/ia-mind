# data "aws_region" "current" {}

# resource "aws_vpc_ipam" "iamind_vpc_ipam" {
#   operating_regions {
#     region_name = local.main_aws_region
#   }
#   tags = local.default_tags
# }

# resource "aws_vpc_ipam_pool" "iamind_vpc_ipam_pool" {
#   address_family = "ipv4"
#   ipam_scope_id  = aws_vpc_ipam.iamind_vpc_ipam.private_default_scope_id
#   locale         = data.aws_region.current.name
#   tags           = local.default_tags
# }

# resource "aws_vpc_ipam_pool_cidr" "iamind_vpc_ipam_pool_cidr" {
#   ipam_pool_id = aws_vpc_ipam.iamind_vpc_ipam.id
#   cidr         = "13.3.0.0/16"
# }

# resource "aws_vpc" "iamind_vpc" {
#   instance_tenancy    = "default"
#   ipv4_ipam_pool_id   = aws_vpc_ipam.iamind_vpc_ipam.id
#   ipv4_netmask_length = 16

#   depends_on = [aws_vpc_ipam_pool_cidr.iamind_vpc_ipam_pool_cidr]

#   enable_dns_support                   = true
#   enable_dns_hostnames                 = true
#   enable_network_address_usage_metrics = true

#   tags = local.default_tags
# }

# resource "aws_subnet" "iamind_subnet_public1" {
#   vpc_id            = aws_vpc.iamind_vpc.id
#   cidr_block        = "13.3.0.0/20"
#   availability_zone = "eu-west-1a"

#   tags = merge(local.default_tags, { Name = "iamind-public-subnet-1" })
# }

# resource "aws_subnet" "iamind_subnet_public2" {
#   vpc_id            = aws_vpc.iamind_vpc.id
#   cidr_block        = "13.3.16.0/20"
#   availability_zone = "eu-west-1b"

#   tags = merge(local.default_tags, { Name = "iamind-public-subnet-2" })
# }

# resource "aws_subnet" "iamind_subnet_private1" {
#   vpc_id            = aws_vpc.iamind_vpc.id
#   cidr_block        = "13.3.128.0/20"
#   availability_zone = "eu-west-1a"

#   tags = merge(local.default_tags, { Name = "iamind-private-subnet-1" })
# }

# resource "aws_subnet" "iamind_subnet_private2" {
#   vpc_id            = aws_vpc.iamind_vpc.id
#   cidr_block        = "13.3.144.0/20"
#   availability_zone = "eu-west-1b"

#   tags = merge(local.default_tags, { Name = "iamind-private-subnet-2" })
# }


# resource "aws_security_group" "iamind_sg_tls_http" {
#   name        = "iamind_sg_tls_http"
#   description = "Allow HTTP/HTTPS inbound and outbound traffic"
#   vpc_id      = aws_vpc.iamind_vpc.id

#   tags = local.default_tags
# }

# resource "aws_vpc_security_group_ingress_rule" "allow_tls_ipv4" {
#   security_group_id = aws_security_group.iamind_sg_tls_http.id
#   cidr_ipv4         = aws_vpc.iamind_vpc.cidr_block
#   from_port         = 443
#   ip_protocol       = "tcp"
#   to_port           = 443
#   tags              = local.default_tags
# }

# resource "aws_vpc_security_group_ingress_rule" "allow_http_ipv4" {
#   security_group_id = aws_security_group.iamind_sg_tls_http.id
#   cidr_ipv4         = aws_vpc.iamind_vpc.cidr_block
#   from_port         = 80
#   ip_protocol       = "tcp"
#   to_port           = 80
#   tags              = local.default_tags
# }

# resource "aws_vpc_security_group_egress_rule" "allow_http_traffic_ipv4" {
#   security_group_id = aws_security_group.iamind_sg_tls_http.id
#   cidr_ipv4         = "0.0.0.0/0"
#   from_port         = 80
#   ip_protocol       = "tcp"
#   to_port           = 80
#   tags              = local.default_tags
# }

# resource "aws_vpc_security_group_egress_rule" "allow_tls_traffic_ipv4" {
#   security_group_id = aws_security_group.iamind_sg_tls_http.id
#   cidr_ipv4         = "0.0.0.0/0"
#   from_port         = 443
#   ip_protocol       = "tcp"
#   to_port           = 443
#   tags              = local.default_tags
# }

# resource "aws_network_acl" "iamind_main_nacl" {
#   vpc_id = aws_vpc.iamind_vpc.id

#   subnet_ids = [
#     aws_subnet.iamind_subnet_public1.id,
#     aws_subnet.iamind_subnet_public2.id,
#     aws_subnet.iamind_subnet_private1.id,
#     aws_subnet.iamind_subnet_private2.id
#   ]

#   egress {
#     protocol   = "tcp"
#     rule_no    = 200
#     action     = "allow"
#     cidr_block = "0.0.0.0/0"
#     from_port  = 443
#     to_port    = 443
#   }

#   egress {
#     protocol   = "tcp"
#     rule_no    = 200
#     action     = "allow"
#     cidr_block = "0.0.0.0/0"
#     from_port  = 80
#     to_port    = 80
#   }

#   ingress {
#     protocol   = "tcp"
#     rule_no    = 200
#     action     = "allow"
#     cidr_block = "0.0.0.0/0"
#     from_port  = 443
#     to_port    = 443
#   }

#   ingress {
#     protocol   = "tcp"
#     rule_no    = 100
#     action     = "allow"
#     cidr_block = "0.0.0.0/0"
#     from_port  = 80
#     to_port    = 80
#   }

#   tags = local.default_tags
# }

# resource "aws_internet_gateway" "iamind_vpc_ig" {
#   vpc_id = aws_vpc.iamind_vpc.id

#   tags = local.default_tags
# }

# resource "aws_route_table" "iamind_rtb_public" {
#   vpc_id = aws_vpc.iamind_vpc.id

#   route {
#     cidr_block = "0.0.0.0/0"
#     gateway_id = aws_internet_gateway.iamind_vpc_ig.id
#   }

#   route {
#     cidr_block = "13.3.0.0/16"
#     gateway_id = "local"
#   }

#   tags = local.default_tags
# }

# resource "aws_route_table_association" "iamind_rtb_association_public1" {
#   subnet_id      = aws_subnet.iamind_subnet_public1.id
#   route_table_id = aws_route_table.iamind_rtb_public.id
# }

# resource "aws_route_table_association" "iamind_rtb_association_public2" {
#   subnet_id      = aws_subnet.iamind_subnet_public2.id
#   route_table_id = aws_route_table.iamind_rtb_public.id
# }

