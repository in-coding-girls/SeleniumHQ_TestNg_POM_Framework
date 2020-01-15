package pom_with_pagefactory.page;

import base.Base;
import static utils.CustomActions.*;
import static utils.CustomWaits.fluentWaitForElementsWithTimeoutPoll;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumHQSearchResultPagePF extends Base {

	// private final By searchButtonLocator = By.xpath("//*[@value='GO']");
	private WebDriver driver;
	private final int WAIT_TIMEOUT_SECONDS = 10;
	private String searchTerm = "selenium java";
	private String serchResultsFrameName = "master-1";

	private String locatorPathStart = "//div[contains(@id, 'adBlock')]";
	private String locatorPathDynamicPartFinish = "//div[contains(@id, 'e')]";
	private String combinedDynamicLocatorPath = locatorPathStart + locatorPathDynamicPartFinish;
	private final By dynamicCombinedSearchResultLocatorBy = By.xpath(combinedDynamicLocatorPath);

	@FindBy(xpath = "//div[contains(@id, 'adBlock')]//div[contains(@id, 'e')]")
	private List<WebElement> generalSearchResults;

	public SeleniumHQSearchResultPagePF(WebDriver driver, String searchTerm) {
		this.driver = driver;
		this.searchTerm = searchTerm;
		PageFactory.initElements(driver, this);
	}

	public int countGeneralNumberOfSearchResults() {
		driver.switchTo().frame(serchResultsFrameName);		
		//return generalSearchResults.size();
		return countGeneralSearchResultsNumber(generalSearchResults);
	}

	public int countResultsWithSearchTerm() {
		driver.switchTo().frame(serchResultsFrameName);
//		List<WebElement> resultsNumberWithSearchTerm = driver.findElements(dynamicCombinedSearchResultLocatorBy);
//		System.out.println("countResultsWithSearchTerm " + resultsNumberWithSearchTerm);		
		//return countResultNumberWithSearchTerm(driver, WAIT_TIMEOUT_SECONDS, dynamicCombinedSearchResultLocatorBy);
		return countResultNumberWithSearchTerm(generalSearchResults, searchTerm);
	}
}
