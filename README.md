Ecco una bozza per il tuo file README in inglese che puoi caricare su GitHub:

Cultivation Control Program (CCP) - GUI

This project, Cultivation Control Program (CCP) - GUI, is an auxiliary tool for the main Cultivation Control Program project. It provides a graphical user interface (GUI) for viewing data stored in a DynamoDB database. The primary focus of this project is to demonstrate how to integrate the AWS SDK for Java to interact with DynamoDB in a Java-based application.

Note: This is not the main project, but it depends on it. The original project is located at: [Cultivation Control Program](https://github.com/AntonioKill98/CultivationControlProgram).

Overview

The GUI was developed to easily access and visualize data from the DynamoDB database used in the main CCP project. This tool not only helps CCP users but is highly customizable, making it useful even outside of the CCP context for any DynamoDB-related visualization tasks.

Features

   • AWS SDK for Java: Demonstrates the use of the AWS SDK to interact with DynamoDB.
   • Highly Customizable: The configuration can be easily modified via a JSON file to change the AWS credentials, DynamoDB table name, columns to display, and more.
   • Standalone GUI: A Java-based graphical user interface for accessing DynamoDB without the need for command-line interactions.

Prerequisites

Before using this GUI:

   1. Ensure you have installed and set up the main project: Cultivation Control Program.
   2. You should have DynamoDB set up, ideally through LocalStack if you are running locally.

Installation Guide

Installing Java and Maven on Debian

   1. Install Java (OpenJDK 17):
   ```
   sudo apt update
   sudo apt install openjdk-17-jdk
   ```
   Verify Java installation:
   ```
   java -version
   ```

   2. Install Maven:
   ```
   sudo apt install maven
   ```
   Verify Maven installation:
   ```
   mvn -version
   ```


Installing Java and Maven on macOS

   1. Install Java (OpenJDK 17):
   ```
   brew install openjdk@17
   ```
   After installing, add the following to your shell configuration (e.g., .zshrc or .bash_profile):
   ```
   export PATH="/usr/local/opt/openjdk@17/bin:$PATH"
   export CPPFLAGS="-I/usr/local/opt/openjdk@17/include"
   ```
   Verify Java installation:
   ```
   java -version
   ```

   2. Install Maven:
   ```
   brew install maven
   ```
   Verify Maven installation:
   ```
   mvn -version
   ```


Cloning the Project

   1. Open a terminal and navigate to the directory where you want to clone the repository.
   2. Clone the project using Git:
   ```
   git clone https://github.com/AntonioKill98/cultivationControlProgram_GUI.git
   ```
   3. Navigate into the project directory:
   ```
   cd cultivationControlProgram_GUI
   ```


Building the Project

   1. Install dependencies and build the project using Maven:
   ```
   mvn clean install
   ```

   2. This command will download all required dependencies and compile the project. It will also generate a JAR file in the target directory.

   Running the GUI

   Once the build process is complete, you can run the GUI as follows:

   1. Navigate to the target directory:
   ```
   cd target
   ```

   2. Run the application:
   ```
   java -jar cultivationControlProgram_GUI-1.0-SNAPSHOT.jar
   ```


Configuration

The GUI is fully customizable via a configuration file named config.json. This file allows you to:

   • Set AWS credentials (access key, secret key).
   • Define the endpoint (useful for LocalStack or AWS services).
   • Specify the DynamoDB table to query.
   • Choose which columns to display.

If the configuration file is missing, the application will generate a default config.json file upon first execution, and you will need to modify it before running the application again.

Customization

The config.json file structure:
```
{
  "aws_access_key_id": "your_access_key",
  "aws_secret_access_key": "your_secret_key",
  "region": "us-east-2",
  "endpoint": "http://localhost:4566",
  "table_name": "your_table_name",
  "columns": ["column1", "column2", "column3"]
}
```
Modify this file to suit your needs. Once you have configured it correctly, re-run the application to see your changes in action.
