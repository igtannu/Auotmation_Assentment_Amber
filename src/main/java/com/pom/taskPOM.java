package com.pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.utils.PageUtil;

public class taskPOM {

	public WebElement homePageText() {
		return PageUtil.findBy(By.xpath("//h2[contains(text(),'Home away from home!')]"));
	}

	public WebElement searchBox() {
		return PageUtil.findBy(By.id("main-search"));
	}

	public WebElement moreFilters() {
		return PageUtil.findBy(By.xpath("//span[contains(text(),'More Filters')]"));
	}

	public WebElement chooseOptionFromText(String txt) {
		return PageUtil.findBy(By.xpath("//*[contains(text(),'" + txt + "')]"));
	}

	public WebElement showResults() {
		return PageUtil.findBy(By.xpath("//span[contains(text(),'Show')]"));
	}

	public WebElement secondResult() {
		return PageUtil.findBy(By.xpath("//div[@class='search-list-inner-container']/div[2]"));
	}

	public WebElement ChooseCityFromOptions(String txt) {
		return PageUtil.findBy(By.xpath("//li//div[contains(@class,'amber-Text-root') and (text()='" + txt + "')]"));
	}

	public List<WebElement> countriesList() {
		return PageUtil.findBys(By.xpath("//div[contains(@class,'amber-Tabs-tabsList')]//button"));
	}

	public WebElement countryChoose(int i) {
		return PageUtil.findBy(By.xpath("//div[contains(@class,'amber-Tabs-tabsList')]//button[" + i + "]"));
	}

	public List<WebElement> citiesList(int n) {
		return PageUtil.findBys(By.xpath("//div[contains(@class,'amber-Tabs-tabsList')]//following-sibling::div[" + n
				+ "]//section/div[2]//div"));
	}

	public WebElement cityName(int n, int m) {
		return PageUtil.findBy(By.xpath("//div[contains(@class,'amber-Tabs-tabsList')]//following-sibling::div[" + n
				+ "]//section/div[2]//div[" + m + "]"));
	}

	public WebElement Btns(String btn) {
		return PageUtil.findBy(By.xpath("//span[text()='" + btn + "']"));
	}

	public WebElement enterEmail() {
		return PageUtil.findBy(By.name("email"));
	}

	public WebElement enterOtp() {
		return PageUtil.findBy(By.name("otp"));
	}

	public WebElement iconShorlist() {
		return PageUtil.findBy(
				By.xpath("//div[contains(text(),'Starting from')]//parent::div//i[contains(@class,'icon-shortlist')]"));
	}

	public WebElement shrolistNo() {
		return PageUtil.findBy(By.xpath("//span[contains(text(),'Shortlist')]//span"));
	}

	public WebElement iconeMEnu() {
		return PageUtil.findBy(By.xpath("//button[(@aria-label='my profile')]//i[contains(@class,'icon-menu')]"));
	}

}
