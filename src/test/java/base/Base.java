package base;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import utils.DriverManager;


public class Base extends DriverManager {

    public static WebDriver driver;

    @BeforeMethod
    public void browserSetup() {
        driver = setWebDriverManager("chrome");
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        System.out.println("\n*************");
        System.out.println("Opened browser");
    }

    @AfterMethod
    public void browserTearDown() {
        killBrowser();
        System.out.println("Killed browser");
        System.out.println("*************\n");
    }
}