package pom_pagefactory_with_abstractclass.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Base;
import pom_pagefactory_with_abstractclass.page.SeleniumHQHomePagePFWithAbstractClass;

public class WebdriverSeleniumHQwithPFTestWithAbstractClass extends Base {
	@Test(description = "SearchTermResultsAreNotEmptyTest, Jira binding can be here")
	public void SearchTermResultsAreNotEmptyTest() {

		int searchResultNumber = new SeleniumHQHomePagePFWithAbstractClass(driver)
				.openPage()
				.searchForTerms()
				.countGeneralNumberOfSearchResults();
		Assert.assertTrue(searchResultNumber > 0, "Search Term Results are empty");
	}

	@Test(description = "ResultsWithSearchTermAreNotEmptyTest, Jira binding can be here")
	public void ResultsWithSearchTermAreNotEmptyTest() {

		int resultsNumberWithSearchTerm = new SeleniumHQHomePagePFWithAbstractClass(driver)
				.openPage()
				.searchForTerms()
				.countResultsWithSearchTerm();
		Assert.assertTrue(resultsNumberWithSearchTerm > 0, "Search Term Results with Search Term are empty");
	}
	
	@Test(description = "ResultsWithSearchTermAreNotEmptyTest2, Jira binding can be here")
	public void ResultsWithSearchTermAreNotEmptyTest2() {

		int resultsNumberWithSearchTerm = new SeleniumHQHomePagePFWithAbstractClass(driver)
				.openPage()
				.searchForTerms()
				.countSearchTermOccurenceInResults();
		Assert.assertTrue(resultsNumberWithSearchTerm > 0, "Search Term Results with Search Term are empty");
	}

}
