package UiAutomation;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SmartCarePro {

    private static final String CSV_FILE_PATH = "CSVFiles/Sample Dataset.csv";

    public static void main(String[] args) throws CsvException, ParseException {
        WebDriver driver = setupWebDriver();
        loginToSmartCare(driver);
        selectProvinceAndDistrict(driver);
        searchAndAttendPatient(driver);
        navigateToVitals(driver);
        fillVitalsForm(driver);
        driver.quit();
    }

    private static WebDriver setupWebDriver() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        //driver.manage().window().maximize();
        return driver;
    }

    private static void loginToSmartCare(WebDriver driver) {
        driver.get("https://carepro-training.ihmafrica.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='username']")));
        usernameField.sendKeys("tester");

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='password']")));
        passwordField.sendKeys("tester2023!");

        WebElement rememberMeCheckbox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='rememberMe']")));
        if (!rememberMeCheckbox.isSelected()) {
            rememberMeCheckbox.click();
        }

        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Sign In']")));
        signInButton.click();
    }

    private static void selectProvinceAndDistrict(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement provinceDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@placeholder='Enter Province']")));
        Select provinceSelect = new Select(provinceDropdown);
        provinceSelect.selectByVisibleText("Lusaka");

        WebElement districtDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@placeholder='Enter District']")));
        districtDropdown.click();

        try {
            Thread.sleep(5000); // Wait for district options to load
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Select districtSelect = new Select(districtDropdown);
        districtSelect.selectByVisibleText("Lusaka");

        WebElement facilityInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search facility']")));
        facilityInput.sendKeys("Dr. Watson Dental Clinic");

        WebElement facilitySuggestion = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Dr. Watson Dental Clinic']")));
        facilitySuggestion.click();

        WebElement enterButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div/div/div[3]/form/div[3]/button")));
        enterButton.click();
    }

    private static void searchAndAttendPatient(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement nrcButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div[2]/div[1]/button[2]")));
        nrcButton.click();

        WebElement nrcInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search by NRC']")));
        nrcInput.clear();
        nrcInput.sendKeys("111111/11/1");

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit' and contains(@class, 'text-whiteColor') and contains(@class, 'bg-greenColor')]")));
        searchButton.click();

        WebElement attendPatientButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div[3]/div/div/div/div[2]/div[2]/button[4]")));
        attendPatientButton.click();
    }

    private static void navigateToVitals(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement vitalElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div[2]/div[3]/div/div/div[2]/div/div[2]/a/div")));
        Actions action = new Actions(driver);
        action.moveToElement(vitalElement).perform();
        vitalElement.click();

        WebElement addVitalElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div/div[2]/div[2]/div[2]/div[1]/div/div/div/div/div[1]/div")));
        action.moveToElement(addVitalElement).perform();
        addVitalElement.click();
    }

    private static void fillVitalsForm(WebDriver driver) throws ParseException, CsvException {
        List<String[]> csvData = readCSV(CSV_FILE_PATH);

        if (csvData != null && !csvData.isEmpty()) {
            String[] firstRow = csvData.get(0);

            String date = firstRow[1];

            String weight = firstRow[3];
            String height = firstRow[4];
            String temperature = firstRow[5];
            String systolic = firstRow[6];
            String diasystolic = firstRow[7];
            String pulseRate = firstRow[8];
            String respiratoryRate = firstRow[9];
            String oxygenSaturation = firstRow[10];

            String formattedDate = formatDate(date);

            fillVitalsFields(driver, formattedDate, weight, height, temperature, systolic, diasystolic, pulseRate, respiratoryRate, oxygenSaturation);
        }
    }

    private static List<String[]> readCSV(String csvFilePath) throws CsvException {
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            List<String[]> csvData = reader.readAll();
            csvData.remove(0); // Remove header
            return csvData;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void fillVitalsFields(WebDriver driver, String date, String weight, String height, String temperature, String systolic, String diasystolic, String pulseRate, String respiratoryRate, String oxygenSaturation) {
        WebElement weightInput = driver.findElement(By.name("weight"));
        weightInput.clear();
        weightInput.sendKeys(weight);

        WebElement heightInput = driver.findElement(By.name("height"));
        heightInput.clear();
        heightInput.sendKeys(height);

        WebElement temperatureInput = driver.findElement(By.name("temperature"));
        temperatureInput.sendKeys(temperature);

        WebElement systolicInput = driver.findElement(By.name("systolic"));
        systolicInput.sendKeys(systolic);

        WebElement diastolicInput = driver.findElement(By.name("diastolic"));
        diastolicInput.sendKeys(diasystolic);

        WebElement pulseRateInput = driver.findElement(By.name("pulseRate"));
        pulseRateInput.sendKeys(pulseRate);

        WebElement respiratoryRateInput = driver.findElement(By.name("respiratoryRate"));
        respiratoryRateInput.sendKeys(respiratoryRate);

        WebElement oxygenSaturationInput = driver.findElement(By.name("oxygenSaturation"));
        oxygenSaturationInput.sendKeys(oxygenSaturation);

        WebElement saveButton = driver.findElement(By.className("main_btn"));
        saveButton.click();
    }

    private static String formatDate(String dateStr) throws ParseException {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = inputFormat.parse(dateStr);
        return outputFormat.format(date);
    }
}
