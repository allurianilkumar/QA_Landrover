package Components;

import constants.ApplicationConstants;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BrowserFactory;
import utils.CommonUtil;


public class DriveMe {
    private static WebDriver driver;
    private static String quoteContentContextTitle;
    private static String name;
    private static String info;


    @Before("@rangeRoverSport")
    public static void setUp() throws Exception {
        driver = BrowserFactory.getDriver();
        CommonUtil.setWindowSize(driver, ApplicationConstants.DESKTOP);
        CommonUtil.setImplicitlyWaitTime(driver, 5);

    }

    @After("@rangeRoverSport")
    public static void tearDown() throws Exception {
        CommonUtil.quitBrowser(driver);
    }

    @Given("^I am on the landrover home page title \"(.*)\", \"(.*)\"$")
    public void openHomePage(String homePageTitle, String rangeRoverSportTitle) throws Throwable {
        driver.get("http://www.landrover.co.uk/index.html");
        // Checking the home page title
        Assert.assertEquals( homePageTitle, driver.getTitle().toString().trim());
        // navigate from home page to range-rover-sport page
        driver.navigate().to("http://www.landrover.co.uk/vehicles/range-rover-sport/index.html");
        Assert.assertEquals( rangeRoverSportTitle, driver.getTitle().toString().trim());

    }
    @When("^I view TwoColumnContainer in Landrover Vehicles Range-rover-sport page$")
    public void openRangeRoverSport(){
        WebElement quoteContent = driver.findElement(By.cssSelector("#\\32 95-80458 > div > div"));
        WebElement quoteContentContext = quoteContent.findElement(By.className("quoteContentContext"));

        quoteContentContextTitle = quoteContentContext.findElement(By.tagName("p")).getText();
        name = quoteContent.findElement(By.className("name")).getText();
        info = quoteContent.findElement(By.className("info")).getText();
    }

    @Then("^I will check drive me heading name is \"(.*)\", \"(.*)\" and \"(.*)\"$")
    public void checkRangeRoverSport(String dbDriveMeHeadingName, String dbName, String dbInfo){
        Assert.assertEquals(dbDriveMeHeadingName, quoteContentContextTitle);
        Assert.assertEquals(dbName, name);
        Assert.assertEquals(dbInfo, info);
    }
}
