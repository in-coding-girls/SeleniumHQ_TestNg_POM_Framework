package pom_pagefactory_with_abstractclass.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utils.DriverManager;

public abstract class AbstractPage extends DriverManager {
	
	protected WebDriver driver;
	protected final int WAIT_TIMEOUT_SECONDS = 10;
	protected abstract AbstractPage openPage();
	
	protected AbstractPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
    }
	
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
