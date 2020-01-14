package pom_without_pagefactory.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Base;
import pom_without_pagefactory.page.SeleniumHQHomePageNoPF;

public class WebDriverSeleniumHQNoPFTest extends Base {

	@Test(description = "SearchTermResultsAreNotEmptyTest, Jira binding can be here")
	public void SearchTermResultsAreNotEmptyTest() {

		int searchResultNumber = new SeleniumHQHomePageNoPF(driver)
				.openPage()
				.searchForTerms()
				.countGeneralNumberOfSearchResults();
		Assert.assertTrue(searchResultNumber > 0, "Search Term Results are empty");
	}

	@Test(description = "ResultsWithSearchTermAreNotEmptyTest, Jira binding can be here")
	public void ResultsWithSearchTermAreNotEmptyTest() {

		int resultsNumberWithSearchTerm = new SeleniumHQHomePageNoPF(driver)
				.openPage()
				.searchForTerms()
				.countResultsWithSearchTerm();
		Assert.assertTrue(resultsNumberWithSearchTerm > 0, "Search Term Results with Search Term are empty");
	}

}