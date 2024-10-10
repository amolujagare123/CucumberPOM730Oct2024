package pages;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static stepdefinitions.SharedSD.getDriver;

public class SearchResult extends Base {


    By hotelNames = By.xpath("//div[@data-testid='title']");

    public  ArrayList<String> getHotelNames()
    {
        return  getElementTextList(hotelNames);
    }

    By priceElement = By.xpath("//span[@data-testid='price-and-discounted-price']");
    
    public ArrayList<Integer> getPriceList()
    {
        ArrayList<Integer> priceList = new ArrayList<>();

        ArrayList<String> rawPriceList = getElementTextList(priceElement); // ₹ 5,700

        for (String rawPriceStr:rawPriceList)
        {
            String rawPriceStr2 = rawPriceStr.substring(2); //5,700
            rawPriceStr2 =  rawPriceStr2.replace(",",""); //5700
            int price = Integer.parseInt(rawPriceStr2);
            priceList.add(price);
        }

        return priceList;
    }


    public void clickRating(String star)
    {
        By ratingElement = By.xpath("//div[@data-filters-group='class']//input[@name='class="+star+"']");
        clickOn(ratingElement);

       /* JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click()",webAction(ratingElement));*/
    }

    By closePopUp = By.xpath("//button[contains(@aria-label,'Dismiss')]");

    public void clickClosePopUp()
    {
       clickOn(closePopUp);



    }

    By starElement = By.xpath("//div[contains(@aria-label,'out of 5')]");

    public ArrayList<String> getStarList()
    {
        List<WebElement> wb = getDriver().findElements(starElement);
        ArrayList<String> starList = new ArrayList<>();
        for(WebElement element : wb)
        {
            String attributeValue = element.getAttribute("aria-label"); // 5 out of 5
            String star = attributeValue.split(" ")[0];
            starList.add(star);
        }
        return  starList;
    }

}
