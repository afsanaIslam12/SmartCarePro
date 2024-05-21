# Smart Care Pro UI Automation

## Description
This project is an automation script for the Smart Care Pro website using Selenium WebDriver. It logs into the website, selects a province and district, searches and attends to a patient, navigates to the vitals section, and fills out a vitals form using data from a CSV file.

## Prerequisites
- Java Development Kit (JDK) 8 or higher
- Maven
- Google Chrome browser
- Internet connection

## Dependencies
The project uses the following dependencies:
- Selenium WebDriver
- OpenCSV
- WebDriverManager

These dependencies are managed using Maven.

## Setup Instructions

1. **Clone the repository**:
    ```sh
    git clone https://github.com/your-username/SmartCarePro.git
    cd SmartCarePro
    ```

2. **Install the dependencies**:
    Make sure you have Maven installed, then run:
    ```sh
    mvn clean install
    ```

3. **Prepare the CSV file**:
    - Create a directory named `CSVFiles` in the root of your project.
    - Place your `Sample Dataset.csv` file inside the `CSVFiles` directory.

## Running the Automation Script

1. **Execute the main class**:
    - You can run the main class `SmartCarePro` from your IDE or using Maven with the following command:
    ```sh
    mvn exec:java -Dexec.mainClass="UiAutomation.SmartCarePro"
    ```

## CSV File Format

I read first row from Sample data.CSV. Then from CSV file I took inputs for automation as follows :

| Weight | Height | Temperature | Systolic | Diastolic | Pulse Rate | Respiratory Rate | Oxygen Saturation |
|--------|--------|-------------|----------|-----------|------------|------------------|-------------------|
|70 | 170 | 36.6 | 120 | 80 | 70 | 18 | 98 |


