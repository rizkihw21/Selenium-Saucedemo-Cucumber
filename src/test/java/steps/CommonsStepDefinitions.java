package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import steps.ReusableLogin;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class CommonsStepDefinitions {

    WebDriver driver;

    // Menggunakan WebDriverSingleton untuk memastikan hanya satu instance WebDriver
    @Before
    public void setup() {
        driver = WebDriverSingleton.getDriver();  // Mendapatkan instance WebDriver
    }

    // Langkah untuk membuka browser dan login dengan kredensial yang valid
    @Given("I open browser and login with valid credentials")
    public void Loginsucces() throws Exception {
        driver.get("https://www.saucedemo.com/"); // Pastikan URL dibuka sebelum login
        new ReusableLogin(driver).loginWithValidCredentials();  // Login hanya sekali, sebelum tes dimulai
    }

    // Langkah untuk menggunakan filter A to Z
    @When("I use the filter A to Z")
    public void useFilterAtoZ() throws InterruptedException {
        WebElement filterElement = driver.findElement(By.className("product_sort_container"));  // Ganti dengan ID atau selector yang sesuai
        filterElement.click();  // Klik elemen filter

        WebElement filterOption = driver.findElement(By.xpath("//select[@class='product_sort_container']//option[text()='Name (A to Z)']"));
        filterOption.click();

        Thread.sleep(2000);
    }

    @When("I use the filter Z to A")
    public void useFilterZtoA() throws InterruptedException {
        WebElement filterElement = driver.findElement(By.className("product_sort_container"));  // Ganti dengan ID atau selector yang sesuai
        filterElement.click();  // Klik elemen filter

        WebElement filterOption = driver.findElement(By.xpath("//select[@class='product_sort_container']//option[text()='Name (Z to A)']"));
        filterOption.click();

        Thread.sleep(2000);
    }

    // Langkah untuk memverifikasi produk terfilter A to Z
    @Then("Products are successfully filtered from A to Z")
    public void productsAreFilteredFromAtoZ() {
        List<WebElement> productNames = driver.findElements(By.cssSelector(".inventory_item_name"));

        List<String> productNamesText = new ArrayList<>();
        for (WebElement product : productNames) {
            productNamesText.add(product.getText());
        }

        List<String> sortedProductNames = new ArrayList<>(productNamesText);
        Collections.sort(sortedProductNames);

        Assert.assertEquals("Products are not sorted A to Z", sortedProductNames, productNamesText);
    }
    
    @Then("Products are successfully filtered from Z to A")
    public void productsAreFilteredFromZtoA() {
        List<WebElement> productNames = driver.findElements(By.cssSelector(".inventory_item_name"));

        List<String> productNamesText = new ArrayList<>();
        for (WebElement product : productNames) {
            productNamesText.add(product.getText());
        }

        List<String> sortedProductNames = new ArrayList<>(productNamesText);
        Collections.sort(sortedProductNames, Collections.reverseOrder());

        Assert.assertEquals("Products are not sorted Z to A", sortedProductNames, productNamesText);
    }

    // Pastikan browser ditutup setelah tes selesai
    @After
    public void tearDown() {
        WebDriverSingleton.closeDriver();  // Menutup WebDriver yang sama setelah tes selesai
    }
}