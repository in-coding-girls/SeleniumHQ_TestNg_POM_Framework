package without_pom;

import base.Base;
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
	private String locatorPathDynamicPartFinish = "//div[contains(@id, 'e')]";
	private String combinedDynamicLocatorPath = locatorPathStart + locatorPathDynamicPartFinish;
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
		driver.switchTo().frame(serchResultsFrameName);
		System.out.println("getIframeTest started");
		System.out.println("switched to frame ");

		final List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
		for (WebElement iframe : iframes) {
			if (iframe.getAttribute("id").equals(serchResultsFrameName)) {
				System.out.println("getIframeTest" + serchResultsFrameName + ": got by id");
			}
			if (iframe.getAttribute("name").equals(serchResultsFrameName)) {
				System.out.println("getIframeTest" + serchResultsFrameName + ": got by name");
			}
		}
		System.out.println("Passed frames test");
	}

	@Test(description = "SearchTermResultsAreNotEmptyTest, Jira binding can be here")
	public void SearchTermResultsAreNotEmptyTest() {
		waitForPageToLoad(driver, HOMEPAGE_URL, WAIT_TIMEOUT_SECONDS);
		enterValueIntoTextField(driver, searchInputByLocator, WAIT_TIMEOUT_SECONDS, searchTerm);
		driver.switchTo().frame(serchResultsFrameName);
		System.out.println("SearchTermResultsAreNotEmptyTest");
		//int searchResultNumber = generalSearchResultsNumber(driver, WAIT_TIMEOUT_SECONDS, dynamicCombinedSearchResultLocator);
		int searchResultNumber = fluentWaitForElementsWithTimeoutPoll(driver, dynamicCombinedSearchResultLocatorBy, WAIT_TIMEOUT_SECONDS,
				3, "Timeout for waiting serach result list has expired").size();
		System.out.println("SearchTermResultsAreNotEmptyTest " + searchResultNumber);
		Assert.assertTrue(searchResultNumber > 0, "Search Term Results are empty");
	}

    @Test(description = "ResultsWithSearchTermAreNotEmptyTest, Jira binding can be here")
    public void ResultsWithSearchTermAreNotEmptyTest() {
		waitForPageToLoad(driver, HOMEPAGE_URL, WAIT_TIMEOUT_SECONDS);
		enterValueIntoTextField(driver, searchInputByLocator, WAIT_TIMEOUT_SECONDS, searchTerm);
		driver.switchTo().frame(serchResultsFrameName);
		int resultsNumberWithSearchTerm = countResultNumberWithSearchTerm(driver, WAIT_TIMEOUT_SECONDS, dynamicCombinedSearchResultLocatorBy);
        System.out.println("ResultsWithSearchTermAreNotEmptyTest");
        System.out.println("Search results number with requested term: " + resultsNumberWithSearchTerm);
        Assert.assertTrue(resultsNumberWithSearchTerm > 0,"Search Term Results with Search Term are empty" );
    }
}