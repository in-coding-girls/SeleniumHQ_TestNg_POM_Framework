package pom_without_pagefactory.page;

import static utils.CustomActions.countResultNumberWithSearchTerm;
import static utils.CustomWaits.fluentWaitForElementsWithTimeoutPoll;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.Base;

public class SeleniumHQSearchResultPageNoPF extends Base {

	private WebDriver driver;
	private final int WAIT_TIMEOUT_SECONDS = 10;
	private String searchTerm = "selenium java";
	private String serchResultsFrameName = "master-1";
	private String locatorPathStart = "//div[contains(@id, 'adBlock')]";
	private String locatorPathDynamicPartFinish = "//div[contains(@id, 'e')]";
	private String combinedDynamicLocatorPath = locatorPathStart + locatorPathDynamicPartFinish;
	private final By dynamicCombinedSearchResultLocatorBy = By.xpath(combinedDynamicLocatorPath);

	public SeleniumHQSearchResultPageNoPF(WebDriver driver, String searchTerm) {
		this.driver = driver;
		this.searchTerm = searchTerm;
	}

//   public int countGeneralNumberofSearchResults() {
//       driver.switchTo().frame(serchResultsFrameName);
//       List<WebElement> generalSearchResults = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
//               .until(ExpectedConditions.presenceOfAllElementsLocatedBy(generalSearchResultLocator ));
//       System.out.println("Total search results number for requested term: " + generalSearchResults);
//       return generalSearchResults.size();
//    }
//
//    public int countResultNumberWithSearchTerm() {
//        driver.switchTo().frame(serchResultsFrameName);
//        List<WebElement> resultsNumberWithSearchTerm = driver.findElements(generalSearchResultLocator);
//        System.out.println("Search results number with requested term: " + resultsNumberWithSearchTerm);
//    return resultsNumberWithSearchTerm.size();
//    }

	public int countGeneralNumberOfSearchResults() {
		driver.switchTo().frame(serchResultsFrameName);
		final int searchResultNumber = fluentWaitForElementsWithTimeoutPoll(driver,
				dynamicCombinedSearchResultLocatorBy, WAIT_TIMEOUT_SECONDS, 3,
				"Timeout for waiting serach result list has expired").size();
		System.out.println("countGeneralNumberOfSearchResults " + searchResultNumber);
		return searchResultNumber;
	}

	public int countResultsWithSearchTerm() {
		driver.switchTo().frame(serchResultsFrameName);
		final int resultsNumberWithSearchTerm = countResultNumberWithSearchTerm(driver, WAIT_TIMEOUT_SECONDS,
				dynamicCombinedSearchResultLocatorBy);
		System.out.println("countResultsWithSearchTerm " + resultsNumberWithSearchTerm);
		return resultsNumberWithSearchTerm;
	}
}