# How to connect to AWS locally

## 1. Install AWS CLI on Windows
### Using the MSI Installer (Recommended for Windows users)
1. Download the AWS CLI MSI Installer: Go to the  [AWS CLI MSI Installer for Windows](https://docs.aws.amazon.com/cli/latest/userguide/install-cliv2-windows.html) and download the MSI installer.

2. Run the Installer: Once the MSI file is downloaded, run it and follow the installation prompts.

3. Verify Installation: After installation, open a new Command Prompt window and run:

```bash
aws --version
```
If everything is installed correctly, you should see the version of the AWS CLI.

### Using Chocolatey (Alternative method)
If you have [Chocolatey](https://chocolatey.org/) installed, you can install the AWS CLI using (Make sure you're running with administrative rights):

```bash
choco install awscli
```
## 2. Install AWS CLI on Linux
1. For Ubuntu/Debian-based systems (e.g., Ubuntu, Mint):
```bash
sudo apt-get update
sudo apt-get install awscli
```
For RedHat/CentOS-based systems:
```bash
sudo yum install aws-cli
```
2. Download the AWS CLI v2 installer:
```bash
curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
```
2. Unzip the installer:
```bash
unzip awscliv2.zip
```
3. Run the install command:
```bash
sudo ./aws/install
```
4. Verify installation:
```bash
aws --version
```
## 3. Configure AWS CLI with Your Credentials
Once you’ve installed the AWS CLI, you need to configure it with your AWS credentials. Run:
```bash
aws configure --profile iamind-developer
```
**This command will prompt you to enter:**

**AWS Access Key ID** – You can find this in the AWS Management Console under IAM (Identity and Access Management) > Users > [Your User] > Security Credentials. \
**AWS Secret Access Key** – This can also be found in the same place as your access key. \
**Default region name** – This is the AWS region you want to use (e.g., us-west-2, us-east-1). \
**Default output format** – The format in which you want the AWS CLI to output information (options are json, yaml, text, or table). \

## 4.  Manually Edit the ~/.aws/config File to Automatically Assume the Role correct role
After you have set up the base profile, manually edit the ~/.aws/config file to set up the role assumption.
1. Open the ~/.aws/config file: You can open this file in a text editor like nano, vim, or any text editor you prefer.
```bash
nano ~/.aws/config
```
2. Add a profile that automatically assumes the role:
In the ~/.aws/config file, add a profile that assumes the role using the role_arn and source_profile options. \
Here's an example: \
```bash
[profile iamind-developer]
role_arn = arn:aws:iam::108782061116:role/iamind_developer_role
region = eu-west-1
output = json
source_profile = iamind-developer
```
- **role_arn**: The ARN of the role you want to assume.
- **source_profile**: This refers to the base profile you created earlier with aws configure.
- **region**: The AWS region you want to work with (e.g., us-west-1).
- **output**: The output format (e.g., json).

3. Using the Role
Now, whenever you use the iamind-developer profile, the AWS CLI will automatically assume the role. \
For example, to list dynamodb tables using the assumed role: \
```bash
aws dynamodb list-tables --profile iamind-developer
```

## Using IntelliJ
Once you have setup awscli, do the following In IntelliJ IDEA to run your app locally. You can do this by adding environment variables in your run/debug configurations:

1. Open IntelliJ IDEA and go to Run > Edit Configurations.
3. In the Environment Variables section, add the following variables:
    - **AWS_ACCESS_KEY_ID**=your-access-key-id
    - **AWS_SECRET_ACCESS_KEY**=your-secret-key
    - **AWS_PROFILE**=iamind-developer
    - **AWS_REGION**=eu-west-1


## Using Visual Studio Code
Install the [AWS Toolkit for Visual Studio Code](https://marketplace.visualstudio.com/items?itemName=amazonwebservices.aws-toolkit-vscode). The AWS Toolkit provides an integrated experience for working with AWS services.

1. Open VSCode
2. Go to the Extensions Marketplace (Ctrl+Shift+X or Cmd+Shift+X on macOS)
3. Search for AWS Toolkit and click Install
4. Once installed open the AWS Toolkit in VSCode by clicking the AWS icon in the Activity Bar on the left
5. Click Connect to AWS and select the appropriate AWS profile (configured in ~/.aws/credentials)
6. After connecting, the AWS Explorer will display your available services and resources (e.g., S3 buckets, Lambda functions, etc.)
7. Create a .env file and add the following variables:
    - **AWS_ACCESS_KEY_ID**=your-access-key-id
    - **AWS_SECRET_ACCESS_KEY**=your-secret-key
    - **AWS_PROFILE**=iamind-developer
    - **AWS_REGION**=eu-west-1