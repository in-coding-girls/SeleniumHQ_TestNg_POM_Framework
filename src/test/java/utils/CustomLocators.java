package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CustomLocators {

	public WebElement getElementByAnyType(WebDriver driver, String identifierType, String pathExpression) {
		switch (identifierType) {
		case "xpath":
			return driver.findElement(By.xpath(pathExpression));

		case "id":
			return driver.findElement(By.id(pathExpression));

		case "tagname":
			return driver.findElement(By.tagName(pathExpression));

		default:
			return driver.findElement(By.xpath(pathExpression));
		}
	}

	public By byOfAnyType(WebDriver driver, String identifierType, String pathExpression) {
		switch (identifierType) {
		case "xpath":
			return By.xpath(pathExpression);

		case "id":
			return By.id(pathExpression);

		case "tagname":
			By.tagName(pathExpression);

		default:
			return By.xpath(pathExpression);
		}
	}

	public String buildDynamicLocatorForSearch(String defaultLocator, String containsPart, String searchTerm) {
//	private String locatorPathStart = "//div[@id='adBlock']";
//	private String locatorPathDynamicPartFinish = "//div[contains(@id, 'e')]";
//	private String combinedDynamicLocatorPath = locatorPathStart + locatorPathDynamicPartFinish;
//	private final By dynamicCombinedSearchResultLocatorBy = By.xpath(combinedDynamicLocatorPath);
		searchTerm = "selenium java";
		// dynamic locator with "contains", split
		// and iteration over the list
		final String splitRegex = "\\s";
		defaultLocator = "//div[contains (@id, 'adBlock')]";
		// private String containsPart = " and contains(., '%s')";
		containsPart = " and contains(@id, 'e')";				
		
		String partWithSearchTerm = "";
		String[] terms = searchTerm.split(splitRegex);
		for (String term : terms) {
			partWithSearchTerm += String.format(containsPart, term);
		}
		
		String locatorForSearch = String.format(defaultLocator, partWithSearchTerm);
		System.out.println("DEBUG: Final locator with search terms: " + locatorForSearch);
		return locatorForSearch;
	}

}
