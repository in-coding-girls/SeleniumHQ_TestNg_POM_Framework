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
				System.out.println("term is: " + term);					
				
				//String elementText = result.getAttribute("innerHTML");
				String elementText = result.getText();
				System.out.println("elementText is: " + elementText);
				
				Boolean textMatch = elementText.toLowerCase().contains(term.toLowerCase());			
				System.out.println("Contains term is: " + textMatch);								
				if (textMatch) {
					countedTermOccurences++;
					System.out.println("countedTermOccurences in the if condition: " + countedTermOccurences);
					
				};
				System.out.println("countedTermOccurences in the inner loop: " + countedTermOccurences);				
			}
			System.out.println("countedTermOccurences in the outer loop: " + countedTermOccurences);			
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
				System.out.println("term is: " + term);					
				
				//String elementText = result.getAttribute("innerHTML");
				String elementText = result.getText();
				System.out.println("elementText is: " + elementText);
				
				Boolean textMatch = elementText.toLowerCase().contains(term.toLowerCase());			
				System.out.println("Contains term is: " + textMatch);											
				if (textMatch) { countedTermOccurences++; };			
			}
		}
		System.out.println("countedTermOccurences in general: " + countedTermOccurences);
		return countedTermOccurences;
	}

	public static void switchToFrameAndCountFrames(WebDriver driver, String serchResultsFrameName) {
		driver.switchTo().frame(serchResultsFrameName);

		System.out.println("switchToFrameAndCountFrames started");
		System.out.println("switched to frame " + serchResultsFrameName);

		List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
		for (WebElement iframe : iframes) {
			System.out.println("Frame Id :" + iframe.getAttribute("id"));
			System.out.println("Frame name :" + iframe.getAttribute("name"));

			if (iframe.getAttribute("id").equals(serchResultsFrameName)) {
				System.out.println("switchToFrameAndCountFrames" + serchResultsFrameName + ": got by id");
			}
			if (iframe.getAttribute("name").equals(serchResultsFrameName)) {
				System.out.println("switchToFrameAndCountFrames" + serchResultsFrameName + ": got by name");
			}
		}

		List<WebElement> frames = driver.findElements(By.tagName("frame"));

		for (WebElement frame : frames) {
			System.out.println("Frame Id :" + frame.getAttribute("id"));
			System.out.println("Frame name :" + frame.getAttribute("name"));

			if (frame.getAttribute("id").equals(serchResultsFrameName)) {
				System.out.println("switchToFrameAndCountFrames" + serchResultsFrameName + ": got by id");
			}
			if (frame.getAttribute("name").equals(serchResultsFrameName)) {
				System.out.println("switchToFrameAndCountFrames" + serchResultsFrameName + ": got by name");
			}
		}
		System.out.println("Number of iframes in a page :" + iframes.size());
		System.out.println("Number of frames in a page :" + frames.size());
		System.out.println("switchToFrameAndCountFrames ended");
	}

}
