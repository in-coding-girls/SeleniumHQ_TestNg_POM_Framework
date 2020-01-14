package pom_with_pagefactory.page;

import static utils.CustomActions.enterValueIntoTextField;
import static utils.CustomWaits.waitForPageToLoad;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class SeleniumHQHomePagePF extends Base {
	private WebDriver driver;
	private final int WAIT_TIMEOUT_SECONDS = 10;
	private static final String HOMEPAGE_URL = "https://selenium.dev/";
	private String searchTerm = "selenium java";

    //private final By searchButtonLocator = By.xpath("//*[@value='GO']");
	
	private final static By searchInputLocatorBy = By.name("search");		
	@FindBy(name = "search")
	private WebElement searchInput;
	
    //private final By searchButtonLocator = By.xpath("//*[@value='GO']");
	@FindBy(xpath = "//*[@class='search-icon']")
	private WebElement searchButton;
	
	public SeleniumHQHomePagePF(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public SeleniumHQHomePagePF openPage() {
    	waitForPageToLoad(driver, HOMEPAGE_URL, WAIT_TIMEOUT_SECONDS);
        return this;
    }

	public SeleniumHQSearchResultPagePF searchForTerms() {
    	enterValueIntoTextField(driver, searchInputLocatorBy, WAIT_TIMEOUT_SECONDS, searchTerm);
        return new SeleniumHQSearchResultPagePF(driver, searchTerm);
    }
	
	public SeleniumHQSearchResultPagePF searchForTermsPF() {
		searchInput.clear();
		searchInput.sendKeys(searchTerm);
		//searchInput.sendKeys(Keys.ENTER);
		searchButton.click();
        return new SeleniumHQSearchResultPagePF(driver, searchTerm);
    }
}
