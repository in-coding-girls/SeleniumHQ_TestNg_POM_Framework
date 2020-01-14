package pom_without_pagefactory.page;

import static utils.CustomActions.enterValueIntoTextField;
import static utils.CustomWaits.waitForPageToLoad;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.Base;

public class SeleniumHQHomePageNoPF extends Base {
 
	private WebDriver driver;
	private final int WAIT_TIMEOUT_SECONDS = 10;
	private static final String HOMEPAGE_URL = "https://selenium.dev/";
	private String searchTerm = "selenium java";
	private final static By searchInputLocatorBy = By.name("search");

    public SeleniumHQHomePageNoPF(WebDriver driver) {
        this.driver = driver;
    }

    public SeleniumHQHomePageNoPF openPage() {
    	waitForPageToLoad(driver, HOMEPAGE_URL, WAIT_TIMEOUT_SECONDS);
        return this;
    }

	public SeleniumHQSearchResultPageNoPF searchForTerms() {
    	enterValueIntoTextField(driver, searchInputLocatorBy, WAIT_TIMEOUT_SECONDS, searchTerm);
        return new SeleniumHQSearchResultPageNoPF(driver, searchTerm);
    }
    
 
}