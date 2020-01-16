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
//		List<WebElement> generalSearchResults = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
//				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
		List<WebElement> generalSearchResults = waitForElementsLocatedBy(driver, by, WAIT_TIMEOUT_SECONDS);
		System.out.println("generalSearchResults: " + generalSearchResults);
		return generalSearchResults.size();
	}

	public static int countGeneralSearchResultsNumber(List<WebElement> generalSearchResultsList) {
		System.out.println("countGeneralSearchResultsNumber: " + generalSearchResultsList.size());
		return generalSearchResultsList.size();
	}

	public static int countAllTextOcurrences(WebDriver driver, int WAIT_TIMEOUT_SECONDS, String xpathPath,
			String searchTerm) {
		
		// split searchTerm
		final String splitRegex = "\\s";
		String[] terms = searchTerm.split(splitRegex);
		
		int countedTermOccurences = 0;
		
		for (String term : terms) {

			//By xpathBy = By.xpath(xpathPath + "//*[translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='" + term.toLowerCase() + "']");		
			By xpathBy = By.xpath(xpathPath + "//*[contains(translate(text(),'ABCDEFGHIJKLMNOPURSTUWXYZ','abcdefghijklmnopurstuwxyz') , '" + term.toLowerCase() + "')]");			
			//By xpathBy = By.xpath(xpathPath + "//*[contains(text(), '" + term + "')]");
			//By xpathBy = By.xpath(xpathPath + "//text()[contains(translate(., '"+term.toUpperCase()+"', '"+term.toLowerCase()+ "'), '" + term.toLowerCase() + "')]");
			
			
			// List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + text + "')]"));
			List<WebElement> elementsWithTextList = waitForElementsLocatedBy(driver, xpathBy, WAIT_TIMEOUT_SECONDS);
			
			System.out.println("countTextOcurrences: for term " + term + " is: " + elementsWithTextList.size());
			if(elementsWithTextList.size() > 0) { countedTermOccurences += elementsWithTextList.size(); }		
		}
		System.out.println("All countedTermOccurences: " + countedTermOccurences);
		return countedTermOccurences;
	}

	public static int countResultNumberWithSearchTerm(WebDriver driver, int WAIT_TIMEOUT_SECONDS, By by,
			String searchTerm) {
		// List<WebElement> generalSearchResults = driver.findElements(by);
		List<WebElement> generalSearchResults = waitForElementsLocatedBy(driver, by, WAIT_TIMEOUT_SECONDS);

		System.out.println("searchTerm is" + searchTerm);

		// split searchTerm
		final String splitRegex = "\\s";
		String[] terms = searchTerm.split(splitRegex);

		int countedTermOccurences = 0;
		int iterationCounter = 0;
		for (String term : terms) {

			for (WebElement result : generalSearchResults) {

				System.out.println("==========\n" + "Iteration # " + iterationCounter++);

				// String elementText = result.getAttribute("innerHTML");
				// String elementText = result.getAttribute("innerText");
				String elementText = result.getText();
				//System.out.println("elementText is: " + elementText);

				Boolean textMatch = elementText.toLowerCase().contains(term.toLowerCase());
				System.out.println("Contains term is: " + textMatch);
				if (textMatch) {
					countedTermOccurences++;
					System.out.println("countedTermOccurences in the if condition for term " + term + ": " + countedTermOccurences);
				};
				System.out.println("countedTermOccurences for term "+ term + " in the inner loop: " + countedTermOccurences);
			}
			System.out.println("countedTermOccurences  for term "+ term + "in the outer loop: " + countedTermOccurences);
		}
		System.out.println("countedTermOccurences in general: " + countedTermOccurences);
		return countedTermOccurences;
	}

	public static int countResultNumberWithSearchTerm(List<WebElement> generalSearchResultsList, String searchTerm) {
		System.out.println("countGeneralResultNumber: " + generalSearchResultsList.size());

		System.out.println("searchTerm is" + searchTerm);

		// split searchTerm
		final String splitRegex = "\\s";
		String[] terms = searchTerm.split(splitRegex);

		int countedTermOccurences = 0;
		int iterationCounter = 0;
		for (String term : terms) {

			for (WebElement result : generalSearchResultsList) {

				System.out.println("==========\n" + "Iteration # " + iterationCounter++);

				// String elementText = result.getAttribute("innerHTML");
				String elementText = result.getAttribute("innerText");
				//String elementText = result.getText();
				System.out.println("elementText is: " + elementText);

				Boolean textMatch = elementText.toLowerCase().contains(term.toLowerCase());
				System.out.println("Contains term " + term + " is: " + textMatch);
				if (textMatch) {
					countedTermOccurences++;
				};
				System.out.println("countedTermOccurences (now on term " + term + ") is: " + countedTermOccurences);
			}
			System.out.println("countedTermOccurences (now on term " + term + ") is: " + countedTermOccurences);
		}
		System.out.println("All countedTermOccurences: " + countedTermOccurences);
		return countedTermOccurences;
	}

	public static void switchToFrameAndCountFrames(WebDriver driver, String serchResultsFrameName) {
		driver.switchTo().frame(serchResultsFrameName);

		System.out.println("switchToFrameAndCountFrames started");
		System.out.println("switched to frame " + serchResultsFrameName);

		List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
		if (iframes.size() > 0) {
			for (WebElement iframe : iframes) {
				System.out.println("iFrame Id: " + iframe.getAttribute("id"));
				System.out.println("iFrame name: " + iframe.getAttribute("name"));

				if (iframe.getAttribute("id").equals(serchResultsFrameName)) {
					System.out.println("switchToFrameAndCountFrames, iframe: " + serchResultsFrameName + ": got by id");
				}
				if (iframe.getAttribute("name").equals(serchResultsFrameName)) {
					System.out
							.println("switchToFrameAndCountFrames, iframe: " + serchResultsFrameName + ": got by name");
				}
			}
		}

		List<WebElement> frames = driver.findElements(By.tagName("frame"));
		if (frames.size() > 0) {
			for (WebElement frame : frames) {
				System.out.println("Frame Id: " + frame.getAttribute("id"));
				System.out.println("Frame name: " + frame.getAttribute("name"));

				if (frame.getAttribute("id").equals(serchResultsFrameName)) {
					System.out.println("switchToFrameAndCountFrames, frame: " + serchResultsFrameName + ": got by id");
				}
				if (frame.getAttribute("name").equals(serchResultsFrameName)) {
					System.out
							.println("switchToFrameAndCountFrames, frame: " + serchResultsFrameName + ": got by name");
				}
			}
		}
		System.out.println("Number of iframes in a page :" + iframes.size());
		System.out.println("Number of frames in a page :" + frames.size());
		System.out.println("switchToFrameAndCountFrames ended");
	}

}
