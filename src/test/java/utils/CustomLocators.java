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

	public static String buildDynamicLocatorPath(String locatorPathStart, String... locatorPathDynamicParts) {
		String concatenatedDynamicLocator = locatorPathStart;

		for (String locatorPathDynamicPart : locatorPathDynamicParts) {
			concatenatedDynamicLocator += String.format("%s", locatorPathDynamicPart);

			System.out.println("locatorPathDynamicPart " + locatorPathDynamicPart);
			// System.out.println("DEBUG: concatenatedPathPart in loop: " +
			// concatenatedDynamicLocator);
		}

		System.out.println("concatenatedPathPart outside loop: " + concatenatedDynamicLocator);
		System.out.println("concatenatedPathPart " + concatenatedDynamicLocator);
		return concatenatedDynamicLocator;
	}
	
	public static By ByDynamicXpath(String locatorPathStart, String... locatorPathDynamicParts) {
		String concatenatedDynamicLocator = locatorPathStart;

		for (String locatorPathDynamicPart : locatorPathDynamicParts) {
			concatenatedDynamicLocator += String.format("%s", locatorPathDynamicPart);
			//System.out.println("locatorPathDynamicPart " + locatorPathDynamicPart);
		}
		//System.out.println("concatenatedPathPart outside loop: " + concatenatedDynamicLocator);
		//System.out.println("concatenatedPathPart " + concatenatedDynamicLocator);
		return By.xpath(concatenatedDynamicLocator);
	}
	
	public static WebElement elemntByDynamicXpath(WebDriver driver, String locatorPathStart, String... locatorPathDynamicParts) {
		String concatenatedDynamicLocator = locatorPathStart;

		for (String locatorPathDynamicPart : locatorPathDynamicParts) {
			concatenatedDynamicLocator += String.format("%s", locatorPathDynamicPart);
			//System.out.println("locatorPathDynamicPart " + locatorPathDynamicPart);
		}
		//System.out.println("concatenatedPathPart outside loop: " + concatenatedDynamicLocator);
		//System.out.println("concatenatedPathPart " + concatenatedDynamicLocator);
		return driver.findElement(By.xpath(concatenatedDynamicLocator));
	}

	public static String addCustomStringToDynamicLocatorPath(String defaultLocator, String stringToAddToPath) {
		// dynamic locator with "contains", split
		// and iteration over the list
		
		final String containsPart = "  (., '%s')";
		final String splitRegex = "\\s";		
		
		String partWithAddedString = "";
		String[] terms = stringToAddToPath.split(splitRegex);
		
		for (String term : terms) {
			partWithAddedString += String.format(containsPart, term);
			System.out.println("partWithAddedString in loop: " + partWithAddedString);
			System.out.println("term is " + term);
		}

		String dynamicLocatorWithCustomString = String.format(defaultLocator + "%s", partWithAddedString);
		System.out.println("partWithAddedString outside loop: " + partWithAddedString);
		System.out.println("Final dynamicLocatorWithCustomString: " + dynamicLocatorWithCustomString);
		return dynamicLocatorWithCustomString;
	}

}
