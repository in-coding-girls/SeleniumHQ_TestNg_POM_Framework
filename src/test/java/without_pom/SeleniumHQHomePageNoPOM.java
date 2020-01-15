package without_pom;

import base.Base;
import utils.CustomLocators;
import utils.CustomLocators.*;

import static utils.CustomWaits.*;
import static utils.CustomLocators.*;
import static utils.CustomActions.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SeleniumHQHomePageNoPOM extends Base {

	private static final String HOMEPAGE_URL = "https://selenium.dev/";
	private final static By searchInputByLocator = By.name("search");
	private final int WAIT_TIMEOUT_SECONDS = 10;
	private String searchTerm = "selenium java";

	private String serchResultsFrameName = "master-1";

	private String locatorPathStart = "//div[@id='adBlock']";
	private String locatorPathDynamicPart = "//div[contains(@id, 'e')]";
	// private String combinedDynamicLocatorPath = locatorPathStart +
	// locatorPathDynamicPart;
	// private final By dynamicCombinedSearchResultLocatorBy =
	// By.xpath(combinedDynamicLocatorPath);
	private String combinedDynamicLocatorPath = buildDynamicLocatorPath(locatorPathStart, locatorPathDynamicPart);
	private final By dynamicCombinedSearchResultLocatorBy = By.xpath(combinedDynamicLocatorPath);

	@Test(description = "openHomePageTest, Jira binding can be here")
	public void openHomePageTest() {
		waitForPageToLoad(driver, HOMEPAGE_URL, WAIT_TIMEOUT_SECONDS);
		String actualUrl = driver.getCurrentUrl();
		System.out.println("openHomePageTest " + HOMEPAGE_URL);
		Assert.assertEquals(HOMEPAGE_URL, actualUrl, "Urls did not match");
	}

	@Test(description = "openHomePageTest, Jira binding can be here")
	public void validateHomePageTitleTest() {
		waitForPageToLoad(driver, HOMEPAGE_URL, WAIT_TIMEOUT_SECONDS);
		String expectedTitle = driver.getTitle();
		System.out.println("validateHomePageTitle " + expectedTitle);
		Assert.assertTrue(expectedTitle.contains("SeleniumHQ Browser Automation"), "HomePageTitle did not match");
	}

	@Test(description = "searchForTermsTest, Jira binding can be here")
	public void searchForTermsTest() {
		waitForPageToLoad(driver, HOMEPAGE_URL, WAIT_TIMEOUT_SECONDS);
		enterValueIntoTextField(driver, searchInputByLocator, WAIT_TIMEOUT_SECONDS, searchTerm);
		System.out.println("searchForTermsTest " + searchTerm);
	}

	@Test(description = "getIframeTest, Jira binding can be here")
	public void getIframeTest() {
		waitForPageToLoad(driver, HOMEPAGE_URL, WAIT_TIMEOUT_SECONDS);
		enterValueIntoTextField(driver, searchInputByLocator, WAIT_TIMEOUT_SECONDS, searchTerm);
		System.out.println("entered search term");
		System.out.println("getIframeTest started");
		switchToFrameAndCountFrames(driver, serchResultsFrameName);
	}

	@Test(description = "SearchTermResultsAreNotEmptyTest, Jira binding can be here")
	public void SearchTermResultsAreNotEmptyTest() {
		waitForPageToLoad(driver, HOMEPAGE_URL, WAIT_TIMEOUT_SECONDS);
		enterValueIntoTextField(driver, searchInputByLocator, WAIT_TIMEOUT_SECONDS, searchTerm);
		driver.switchTo().frame(serchResultsFrameName);
		// int searchResultNumber = generalSearchResultsNumber(driver,
		// WAIT_TIMEOUT_SECONDS, dynamicCombinedSearchResultLocator);
		int searchResultNumber = fluentWaitForElementsWithTimeoutPoll(driver, dynamicCombinedSearchResultLocatorBy,
				WAIT_TIMEOUT_SECONDS, 3, "Timeout for waiting serach result list has expired").size();
		System.out.println("SearchTermResultsAreNotEmptyTest " + searchResultNumber);
		Assert.assertTrue(searchResultNumber > 0, "Search Term Results are empty");
	}

	@Test(description = "ResultsWithSearchTermAreNotEmptyTest, Jira binding can be here")
	public void ResultsWithSearchTermAreNotEmptyTest() {
		waitForPageToLoad(driver, HOMEPAGE_URL, WAIT_TIMEOUT_SECONDS);
		enterValueIntoTextField(driver, searchInputByLocator, WAIT_TIMEOUT_SECONDS, searchTerm);
		driver.switchTo().frame(serchResultsFrameName);
//		int resultsNumberWithSearchTerm = countResultNumberWithSearchTerm(driver, WAIT_TIMEOUT_SECONDS,
//				dynamicCombinedSearchResultLocatorBy);

		// int resultsNumberWithSearchTerm = countResultNumberWithSearchTerm(driver,
		// dynamicCombinedSearchResultLocatorBy, searchTerm);
		int resultsNumberWithSearchTerm = countResultNumberWithSearchTerm(driver, WAIT_TIMEOUT_SECONDS,
				dynamicCombinedSearchResultLocatorBy, searchTerm);
		System.out.println("Search results number with requested term: " + resultsNumberWithSearchTerm);
		Assert.assertTrue(resultsNumberWithSearchTerm > 0, "Search Term Results with Search Term are empty");
	}

	@Test(description = "buildDynamicLocatorTest, Jira binding can be here")
	public void buildDynamicLocatorTest() {
		System.out.println(
				"buildDynamicLocatorTest " + buildDynamicLocatorPath(locatorPathStart, locatorPathDynamicPart));

	}

	@Test(description = "addTermToDynamicPathTest, Jira binding can be here")
	public void addTermToDynamicPathTest() {
		System.out.println("addTermToDynamicPathTest"
				+ addCustomStringToDynamicLocatorPath(combinedDynamicLocatorPath, searchTerm));

	}
}