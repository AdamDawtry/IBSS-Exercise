package glue;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class GoogleSteps {

    @Given("url {string} is launched")
    public void url(String url) {
        W.get().driver.get(url);
        acceptCookiesIfWarned();
    }

    private static void acceptCookiesIfWarned() {
        try {
            W.get().driver.findElement(By.cssSelector("#L2AGLb")).click();
        } catch (NoSuchElementException ignored) {
        }
    }
    
    @When("About page is shown")
    public void aboutPageIsShown() {
    	WebDriver driver = W.get().driver;
    	WebElement about = driver.findElement(By.linkText("About"));
    	new Actions(driver).click(about).perform();
    }
    
    @Then("page displays {string}")
    public void pageDisplays(String info) {
    	WebDriver driver = W.get().driver;
    	String pageText = driver.findElement(By.tagName("body")).getText();
    	Assert.assertTrue("Text displayed on page", pageText.contains(info));
    }
    
    @When("searching for {string}")
    public void searchingFor(String query) {
    	WebDriver driver = W.get().driver;
    	WebElement search = driver.findElement(By.className("gLFyf"));
    	search.sendKeys(query);
    	search.sendKeys(Keys.ENTER);
    }
    
    @Then("results contain {string}")
    public void resultsContain(String result) {
    	// Second page of Google is not real and cannot hurt you (it is not checked)
    	WebDriver driver = W.get().driver;
    	// List<WebElement> matches = driver.findElements(By.linkText(result));
    	// Assert.assertTrue("Correct search result displayed", matches.size() > 0);
    	String pageText = driver.findElement(By.id("res")).getText();
    	Assert.assertTrue("Correct search result displayed", pageText.contains(result));
    }
    
    @And("result stats are displayed")
    public void resultStatsDisplayed() {
    	WebDriver driver = W.get().driver;
    	WebElement tools = driver.findElement(By.id("hdtb-tls"));
    	new Actions(driver).click(tools).perform();
    }
    
    @And("number of {string} is more than {int}")
    public void numOfStatGreaterThan(String stat, int num) throws ParseException {
    	WebDriver driver = W.get().driver;
    	String searchStats = driver.findElement(By.id("result-stats")).getText();
    	Pattern p = Pattern.compile(String.format("([0-9,.]+)(?= %s)",stat));
    	Matcher m = p.matcher(searchStats);
    	m.find();
    	String statResult = m.group();
    	Assert.assertNotNull(statResult);
		float statResultNum = NumberFormat.getNumberInstance(Locale.ENGLISH).parse(statResult).floatValue();
		Assert.assertTrue(statResultNum > 0);
    }

}
