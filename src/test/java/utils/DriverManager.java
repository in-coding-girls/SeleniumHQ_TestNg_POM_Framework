package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;

public class DriverManager {

    public static WebDriver driver;

    /*###############################################################################*/

    /************overloaded setWebDriverManager()***********/

    public static WebDriver setWebDriverManager(String browser) {

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("ie")) {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("opera")) {
            WebDriverManager.operadriver().setup();

            OperaOptions options = new OperaOptions();
            options.setBinary("C:\\Users\\yulia\\AppData\\Local\\Programs\\Opera\\60.0.3255.170\\opera.exe");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(OperaOptions.CAPABILITY, options);

            driver = new OperaDriver(capabilities);
        } else {
            throw new RuntimeException("Unknown browser: " + browser);
        }
        return driver;
    }

    public void setWebDriverManager(String browser, String url) {

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("ie")) {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browser.equals("opera")) {
            WebDriverManager.operadriver().setup();

            OperaOptions options = new OperaOptions();
            options.setBinary("C:\\Users\\yulia\\AppData\\Local\\Programs\\Opera\\60.0.3255.170\\opera.exe");
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(OperaOptions.CAPABILITY, options);

            driver = new OperaDriver(capabilities);
        } else {
            throw new RuntimeException("Unknown browser: " + browser);
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(url);
        Reporter.log("Application is set up successfully");
    }

    public void killBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        Reporter.log("Browser Session End");
    }

}
