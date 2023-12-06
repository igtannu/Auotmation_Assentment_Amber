package stepDefinations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.browseHandle.BrowserHandle;
import com.core.CoreFunctions;
import com.logs.Logs;
import com.pom.taskPOM;
import com.utils.EmailReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TaskStepDefs {
	taskPOM pom = new taskPOM();

	@Given("Open Browser and visit URL")
	public void open_browser() throws IOException {
		Logs.logger.info(new Object() {
		}.getClass().getEnclosingMethod().getName());
		BrowserHandle.getDriver().get(CoreFunctions.readConfig("qaSignInurl"));

	}

	@Given("User is on Company Site")
	public void user_is_on_company_site() {
		BrowserHandle.wait.until(ExpectedConditions.visibilityOf(pom.homePageText()));
		Assert.assertTrue(pom.homePageText().isDisplayed());

	}

	@When("User Search for accommodation in {string}")
	public void user_search_for_accommodation_in_london(String city) {
		BrowserHandle.wait.until(ExpectedConditions.elementToBeClickable(pom.searchBox()));
		CoreFunctions.click(pom.chooseOptionFromText("Search by"), "Search Box");
		CoreFunctions.setText(pom.searchBox(), "London");
		CoreFunctions.click(pom.ChooseCityFromOptions(city), city);
	}

	@When("Filter search results for Room Type {string} and Sharing {string}")
	public void filter_search_results_for_room_type_and_sharing(String filter1, String filter2) {
		BrowserHandle.wait.until(ExpectedConditions.elementToBeClickable(pom.moreFilters()));
		CoreFunctions.click(pom.moreFilters(), "More Filters");

		BrowserHandle.wait.until(ExpectedConditions.elementToBeClickable(pom.chooseOptionFromText(filter1)));
		CoreFunctions.click(pom.chooseOptionFromText(filter1), "More Filters");

		BrowserHandle.wait.until(ExpectedConditions.elementToBeClickable(pom.chooseOptionFromText(filter2)));
		CoreFunctions.click(pom.chooseOptionFromText(filter2), "More Filters");

		BrowserHandle.wait.until(ExpectedConditions.elementToBeClickable(pom.showResults()));
		CoreFunctions.click(pom.showResults(), "More Filters");

	}

	@When("Open the second search result in a new tab")
	public void open_the_second_search_result_in_a_new_tab() {

		BrowserHandle.wait.until(ExpectedConditions.elementToBeClickable(pom.secondResult()));
		CoreFunctions.click(pom.secondResult(), "More Filters");

	}

	@When("Switch to the tab")
	public void switch_to_the_tab() {
		ArrayList<String> tabs = new ArrayList<String>(BrowserHandle.getDriver().getWindowHandles());
		BrowserHandle.getDriver().switchTo().window(tabs.get(1));

	}

	@Then("Assert the title and location of the property")
	public void assert_the_title_and_location_of_the_property() {

		Assert.assertEquals(BrowserHandle.getDriver().getTitle(), "North Lodge Student Accommodation London | Amber");
		Assert.assertTrue(pom.chooseOptionFromText("Lebus St, London, N17 9FQ, United Kingdom").isDisplayed());

	}

	@When("Click on Search bar")
	public void click_on_search_bar() {
		BrowserHandle.wait.until(ExpectedConditions.elementToBeClickable(pom.chooseOptionFromText("Search by")));
		CoreFunctions.click(pom.chooseOptionFromText("Search by"), "Search Box");

	}

	@Then("Verify list of popular cities is different for all countries and no city is repeated or mentioned in more than one country tab by Iterate over the countries tab")
	public void verify_list_of_popular_cities_is_different_for_all_countries_and_no_city_is_repeated_or_mentioned_in_more_than_country_tab() {
		int countryListSize = pom.countriesList().size();
		HashSet<String> set = new HashSet<String>();
		for (int i = 2; i <= countryListSize; i++) {
			CoreFunctions.click(pom.countryChoose(i), null);
			int citiesSize = pom.citiesList(i).size();
			for (int j = 1; j <= citiesSize; j++) {
				String cityName = CoreFunctions.getElementText(pom.cityName(i, j));
				if (set.contains(cityName))
					Assert.assertTrue(false);
				else
					set.add(cityName);

			}
		}

	}

	@When("Login using Gmail")
	public void login_using_gmail() throws InterruptedException {
		BrowserHandle.wait.until(ExpectedConditions.elementToBeClickable(pom.Btns("Login")));
		CoreFunctions.click(pom.Btns("Login"), "Login");

		BrowserHandle.wait.until(ExpectedConditions.elementToBeClickable(pom.enterEmail()));
		CoreFunctions.click(pom.enterEmail(), "Enter Email");
		CoreFunctions.setText(pom.enterEmail(), "igtanu848@gmail.com");

		BrowserHandle.wait.until(ExpectedConditions.elementToBeClickable(pom.Btns("Continue")));
		CoreFunctions.click(pom.Btns("Continue"), "Continue");

		// Wait till we get otp and extract it
		Thread.sleep(5000);

		String otp = EmailReader.readOTP("igtanu848@gmail.com", "qkbk qysj mqsh mqby");

		System.out.println(otp);
		BrowserHandle.wait.until(ExpectedConditions.elementToBeClickable(pom.enterOtp()));
		CoreFunctions.click(pom.enterOtp(), "Enter Email");
		CoreFunctions.setText(pom.enterOtp(), otp);

		BrowserHandle.wait.until(ExpectedConditions.elementToBeClickable(pom.Btns("Continue")));
		CoreFunctions.click(pom.Btns("Continue"), "Continue");

	}

	@When("Shortlist the second search result")
	public void shortlist_the_second_search_result() {
		BrowserHandle.wait.until(ExpectedConditions.elementToBeClickable(pom.iconShorlist()));
		CoreFunctions.click(pom.iconShorlist(), "icon shorlist");

	}

	@Then("Verify the property is shortlisted")
	public void verify_the_property_is_shortlisted() {
		BrowserHandle.wait.until(ExpectedConditions.visibilityOf(pom.shrolistNo()));
		Assert.assertEquals(CoreFunctions.getElementText(pom.shrolistNo()), "1");

		CoreFunctions.click(pom.iconShorlist(), "icon shorlist");

	}

	@Then("Verify Logout sucessfully")
	public void verifyLogout() {
		BrowserHandle.wait.until(ExpectedConditions.visibilityOf(pom.Btns("Login")));
		Assert.assertFalse(pom.Btns("Login").isDisplayed());
	}

	@When("Log out of your account")
	public void log_out_of_your_account() {

		BrowserHandle.wait.until(ExpectedConditions.visibilityOf(pom.iconeMEnu()));
		CoreFunctions.click(pom.iconeMEnu(), "icon shorlist");

		BrowserHandle.wait.until(ExpectedConditions.visibilityOf(pom.chooseOptionFromText("Logout")));
		CoreFunctions.click(pom.chooseOptionFromText("Logout"), "Logout");

	}

}
