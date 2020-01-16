package pom_pagefactory_with_abstractclass.page;

import static utils.CustomActions.enterValueIntoTextField;
import static utils.CustomWaits.waitForPageToLoad;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class SeleniumHQHomePagePFWithAbstractClass extends AbstractPage {

	private static final String HOMEPAGE_URL = "https://selenium.dev/";
	private String searchTerm = "selenium java";
	
	private final By searchInputLocatorBy = By.name("search");		
	@FindBy(name = "search")
	private WebElement searchInput;
	
    //private final By searchButtonLocator = By.xpath("//*[@value='GO']");
	@FindBy(xpath = "//*[@class='search-icon']")
	private WebElement searchButton;
	
	public SeleniumHQHomePagePFWithAbstractClass(WebDriver driver) {
        super(driver);
    }

    public SeleniumHQHomePagePFWithAbstractClass openPage() {
    	waitForPageToLoad(driver, HOMEPAGE_URL, WAIT_TIMEOUT_SECONDS);
        return this;
    }

	public SeleniumHQSearchResultPagePFWithAbstractClass searchForTerms() {
    	enterValueIntoTextField(driver, searchInputLocatorBy, WAIT_TIMEOUT_SECONDS, searchTerm);
        return new SeleniumHQSearchResultPagePFWithAbstractClass(driver, searchTerm);
    }
	
	public SeleniumHQSearchResultPagePFWithAbstractClass searchForTermsPF() {
		searchInput.clear();
		searchInput.sendKeys(searchTerm);
		//searchInput.sendKeys(Keys.ENTER);
		searchButton.click();
        return new SeleniumHQSearchResultPagePFWithAbstractClass(driver, searchTerm);
    }
}
