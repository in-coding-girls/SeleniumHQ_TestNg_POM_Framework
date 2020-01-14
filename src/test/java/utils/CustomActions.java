package utils;

import static utils.CustomWaits.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomActions {
	
	public static void enterValueIntoTextField(WebDriver driver, By by, int WAIT_TIMEOUT_SECONDS, String enteredTerm) {
		WebElement fieldElement = waitForElementLocatedBy(driver, by, WAIT_TIMEOUT_SECONDS);
		fieldElement.clear();
		fieldElement.sendKeys(enteredTerm);
		fieldElement.sendKeys(Keys.ENTER);

	}
	
	public static int generalSearchResultsNumber(WebDriver driver, int WAIT_TIMEOUT_SECONDS, By by) {
		List<WebElement> generalSearchResults = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
		System.out.println("generalSearchResults: " + generalSearchResults);
		return generalSearchResults.size();
	}
	
    public static int countResultNumberWithSearchTerm(WebDriver driver, int WAIT_TIMEOUT_SECONDS, By by) {
        List<WebElement> resultsNumberWithSearchTerm = driver.findElements(by);
        return resultsNumberWithSearchTerm.size();
    }

}
