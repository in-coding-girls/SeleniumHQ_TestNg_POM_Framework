package utils;

import static utils.CustomLocators.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CustomWaits {

	// waiting for 30 seconds for an element to be present on the page,
	// checking for its presence once every five seconds.
	public static void timeoutPollFluentWait(WebDriver driver, int WAIT_TIMEOUT_SECONDS, int POLL_EVERY_SECONDS,
			String errorMessage) {
		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
				.withTimeout(WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS).pollingEvery(POLL_EVERY_SECONDS, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
				.withMessage(errorMessage);
	}

	public static void fluentWaitForElementWithTimeoutPoll(WebDriver driver, By by, int WAIT_TIMEOUT_SECONDS,
			int POLL_EVERY_SECONDS, String errorMessage) {
		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
				.withTimeout(WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS).pollingEvery(POLL_EVERY_SECONDS, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
				.withMessage(errorMessage);

		final By byLocator = by;
		WebElement fluentWaitElement = fluentWait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(byLocator);
			}

		});

	}

	public static List<WebElement> fluentWaitForElementsWithTimeoutPoll(WebDriver driver, By by,
			int WAIT_TIMEOUT_SECONDS, int POLL_EVERY_SECONDS, String errorMessage) {
		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
				.withTimeout(WAIT_TIMEOUT_SECONDS, TimeUnit.SECONDS).pollingEvery(POLL_EVERY_SECONDS, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class)
				.withMessage(errorMessage);

		final By byLocator = by;
		List<WebElement> fluentWaitElements = fluentWait.until(new Function<WebDriver, List<WebElement>>() {
			public List<WebElement> apply(WebDriver driver) {
				return driver.findElements(byLocator);
			}

		});

		return fluentWaitElements;

	}

	public static ExpectedCondition<Boolean> jQueryAJAXCompleted() {
		return new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return (Boolean) ((JavascriptExecutor) driver)
						.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
	}

	public static void waitForjQueryAJAXToComplete(WebDriver driver, int WAIT_TIMEOUT_SECONDS) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
			wait.until(jQueryAJAXCompleted());
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Page Load Request to complete.");
		}
	}

	public static void waitForjQueryAJAXToCompleteOnPage(WebDriver driver, String url, int WAIT_TIMEOUT_SECONDS) {
		driver.get(url);
		try {
			WebDriverWait wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
			wait.until(jQueryAJAXCompleted());
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Page Load Request to complete.");
		}
	}

	public static void waitForPageToLoad(WebDriver driver, String url, int WAIT_TIMEOUT_SECONDS) {
		driver.get(url);
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};

		try {
			WebDriverWait wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
			wait.until(pageLoadCondition);
		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Page Load Request to complete.");
		}
	}

	public static WebElement waitForElementLocatedBy(WebDriver driver, By by, int WAIT_TIMEOUT_SECONDS) {
		return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.presenceOfElementLocated(by));

	}

	public static List<WebElement> waitForElementsLocatedBy(WebDriver driver, By by, int WAIT_TIMEOUT_SECONDS) {
		return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
	}
}
