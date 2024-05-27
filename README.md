# Smart Care Pro UI Automation

## Prerequisites

- Ensure Java is installed on your system.
- Add ChromeDriver to your system's PATH.
- Install the necessary dependencies:
- Selenium WebDriver
- OpenCSV
## Project Setup:

- Create a new directory for the project.
- Inside this directory, create a CSVFiles directory and place your Sample Dataset.csv file there.
- Create a src directory and place the SmartCarePro.java file in a UiAutomation package within this directory.
## Maven/Gradle Configuration (Optional but recommended):


* If you're using Maven, create a pom.xml file with the following dependencies:
xml - Copy code
```
<dependencies>
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.1.0</version>
    </dependency>
    <dependency>
        <groupId>com.opencsv</groupId>
        <artifactId>opencsv</artifactId>
        <version>5.5.2</version>
    </dependency>
    <dependency>
        <groupId>io.github.bonigarcia</groupId>
        <artifactId>webdrivermanager</artifactId>
        <version>5.0.3</version>
    </dependency>
</dependencies>
```


## Running the Script:

* Compile the Java file:
sh Copy code
```
javac -d bin -cp .;path/to/selenium-java.jar;path/to/opencsv.jar;path/to/webdrivermanager.jar src/UiAutomation/SmartCarePro.java
```
* Run the compiled class:
sh
```
Copy code java -cp .;bin;path/to/selenium-java.jar;path/to/opencsv.jar;path/to/webdrivermanager.jar Ui
```
## For CSV File Format

I read only the first row from Sample data.CSV. Then from CSV file I took inputs for automation as follows :

| Weight | Height | Temperature | Systolic | Diastolic | Pulse Rate | Respiratory Rate | Oxygen Saturation |
|--------|--------|-------------|----------|-----------|------------|------------------|-------------------|
|70 | 170 | 36.6 | 120 | 80 | 70 | 18 | 98 |


