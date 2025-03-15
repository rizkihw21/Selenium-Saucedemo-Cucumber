package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.NoSuchElementException;
import steps.LoginStepDefinitions;

public class AddtoCartStepDefinitions {

    WebDriver driver;

    @Before
    public void setup() {
        driver = WebDriverSingleton.getDriver();  // Mendapatkan instance WebDriver
    }

    @Given("I open the browser and login with valid credentials")
    public void openBrowserAndLogin()throws InterruptedException { // Pastikan URL dibuka sebelum login
        new ReusableLogin(driver).loginWithValidCredentials();  // Login hanya sekali, sebelum tes dimulai
        Thread.sleep(2000);
    }

    @When("I add a product to the cart")
    public void addProductToCart() {
        WebElement itemName = driver.findElement(By.id("item_4_title_link"));
        itemName.click();

        WebElement addToCartButton = driver.findElement(By.id("add-to-cart"));
        addToCartButton.click();
    }

    @When("I remove a product from the cart")
    public void removeProductfromCart()throws InterruptedException {
        WebElement cartLink = driver.findElement(By.cssSelector("[data-test='shopping-cart-link']"));
        cartLink.click();
        
        WebElement removebutton = driver.findElement(By.id("remove-sauce-labs-backpack"));
        removebutton.click();  // Klik tombol
        Thread.sleep(2000);
    }

    @When("I proceed to checkout")
    public void proceedToCheckout() {
        WebElement cartLink = driver.findElement(By.cssSelector("[data-test='shopping-cart-link']"));
        cartLink.click();
        WebElement checkoutButton = driver.findElement(By.id("checkout"));
        checkoutButton.click();
    }

    @When("I fill in the checkout information")
    public void fillCheckoutInfo() {
        WebElement firstName = driver.findElement(By.id("first-name"));
        firstName.sendKeys("John");  // Masukkan nama depan

        WebElement lastName = driver.findElement(By.id("last-name"));
        lastName.sendKeys("Doe");  // Masukkan nama belakang

        WebElement postalCode = driver.findElement(By.id("postal-code"));
        postalCode.sendKeys("12345");  // Masukkan kode postal

        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();

        WebElement finishButton = driver.findElement(By.id("finish"));
        finishButton.click();
    }

    @When("I fill in the checkout information without first name")
    public void fillCheckoutInfoWithoutFirstName() {
        WebElement lastName = driver.findElement(By.id("last-name"));
        lastName.sendKeys("Doe");  // Masukkan nama belakang

        WebElement postalCode = driver.findElement(By.id("postal-code"));
        postalCode.sendKeys("12345");  // Masukkan kode postal

        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();

    }

    @When("I fill in the checkout information without last name")
    public void fillCheckoutInfoWithoutLastName() {
        WebElement firstName = driver.findElement(By.id("first-name"));
        firstName.sendKeys("John");  // Masukkan nama depan

        WebElement postalCode = driver.findElement(By.id("postal-code"));
        postalCode.sendKeys("12345");  // Masukkan kode postal

        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();
    }

    @When("I fill in the checkout information without Postal Code")
    public void fillCheckoutInfoWithoutPostalCode(){
        WebElement firstName = driver.findElement(By.id("first-name"));
        firstName.sendKeys("John");  // Masukkan nama depan

        WebElement lastName = driver.findElement(By.id("last-name"));
        lastName.sendKeys("Doe");  // Masukkan nama belakang

        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();
    }

    @Then("The product should be added to the cart successfully")
    public void checkCart() {
        WebElement cartLink = driver.findElement(By.cssSelector("[data-test='shopping-cart-link']"));
        cartLink.click();

        WebElement cartItem = driver.findElement(By.cssSelector(".cart_item[data-test='inventory-item']"));
        Assert.assertTrue(cartItem.isDisplayed());
    }

    @Then("The product should be removed from the cart successfully")
    public void checkRemoveCart()throws InterruptedException {
        try {
            WebElement cartItem = driver.findElement(By.cssSelector(".cart_item[data-test='inventory-item']"));
            Assert.assertFalse(cartItem.isDisplayed());  // Memastikan item tidak tampil
            Thread.sleep(2000);
        } catch (NoSuchElementException e) {
            // Jika elemen tidak ditemukan, berarti item sudah hilang
            System.out.println("Item sudah berhasil dihapus dari cart.");
        }
    }

    @Then("The product should be checked out successfully")
    public void checkCheckout() {
        WebElement checkoutComplete = driver.findElement(By.id("checkout_complete_container"));
        Assert.assertTrue(checkoutComplete.isDisplayed());
    }

    @Then("I should see an error message for blank first name")
    public void seeErrorMessageForBlankFirstName() {
        WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
        Assert.assertTrue(errorMessage.getText().contains("Error: First Name is required"));
    }

    @Then("I should see an error message for blank last name")
        public void seeErrorMessageForBlankLastName() {
            WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
            Assert.assertTrue(errorMessage.getText().contains("Error: Last Name is required"));
    }

    @Then("I should see an error message for blank Postal Code")
    public void seeErrorMessageForBlankPostalCode() {
        WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
        Assert.assertTrue(errorMessage.getText().contains("Error: Postal Code is required"));
    }

    @After
    public void tearDown() {
        WebDriverSingleton.closeDriver();  // Menutup WebDriver yang sama setelah tes selesai
    }
}