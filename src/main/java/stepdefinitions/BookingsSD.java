package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.SearchResult;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;

import static stepdefinitions.SharedSD.getDriver;

public class BookingsSD {

    SearchResult searchResult = new SearchResult();

    @Given("I am on default locations search result screen")
    public void i_am_on_default_locations_search_result_screen() {

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        try {
            searchResult.clickClosePopUp();
        }
        catch (Exception e)
        {

        }

        getDriver().navigate().refresh();

    }

    @Then("I verify {string} is within the search result")
    public void i_verify_is_within_the_search_result(String expectedHotelName) {

        ArrayList<String> nameList = searchResult.getHotelNames();
        boolean result = false;
        for (String hotelName:nameList)
        {
            System.out.println(hotelName);
            if (hotelName.contains(expectedHotelName))
                result = true;
        }

        Assert.assertTrue(expectedHotelName+": this hotel is not in the list",result);

    }

    @Then("I verify system displays all hotels within {string} amount")
    public void iVerifySystemDisplaysAllHotelsWithinAmount(String expectedAmountStr) {

        int expectedAmount = Integer.parseInt(expectedAmountStr);
        ArrayList<Integer> priceList = searchResult.getPriceList();
        System.out.println(priceList);
        boolean result = true;
        ArrayList<Integer> greaterPriceList = new ArrayList<>();
        for (int price:priceList)
        {
            if (price>expectedAmount) {
                result = false;
                greaterPriceList.add(price);
            }
        }
        Assert.assertTrue("Some prices are greater than "+expectedAmountStr
                +"\n Greater price list as below\n"+greaterPriceList,result);

    }

    @When("I select option for stars as {}")
    public void iSelectOptionForStarsAs(String star) //5 stars
    {

        searchResult.clickRating(star.split(" ")[0]);
    }

    @Then("I verify system displays only {} hotels on search result")
    public void iVerifySystemDisplaysOnlyHotelsOnSearchResult(String star) // 5 star
    {

        ArrayList<String> starList = searchResult.getStarList();
        System.out.println(starList);

        int size = starList.size();

        int occurrence = Collections.frequency(starList,star.split(" ")[0]);

        boolean result = (size == occurrence);
        Assert.assertTrue("all the stars are not "+star,result);
    }
}
