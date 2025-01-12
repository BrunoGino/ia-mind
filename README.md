# How to run

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
aws configure
```
**This command will prompt you to enter:**

**AWS Access Key ID** – You can find this in the AWS Management Console under IAM (Identity and Access Management) > Users > [Your User] > Security Credentials.
**AWS Secret Access Key** – This can also be found in the same place as your access key.
**Default region name** – This is the AWS region you want to use (e.g., us-west-2, us-east-1).
**Default output format** – The format in which you want the AWS CLI to output information (options are json, yaml, text, or table).