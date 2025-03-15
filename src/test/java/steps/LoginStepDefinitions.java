package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import io.cucumber.java.Before;
import io.cucumber.java.After;

public class LoginStepDefinitions {

    WebDriver driver;

    @Before  // Menjalankan browser sebelum tes dimulai
    public void setup() {
        driver = WebDriverSingleton.getDriver();  // Mendapatkan instance WebDriver
    }

    @Given("I open the browser")
    public void openBrowser() {
        // Tidak perlu membuka browser lagi, browser sudah terbuka di setUp()
    }

    @Given("I navigate to the login page")
    public void navigateToLoginPage() {
        driver.get("https://www.saucedemo.com");  
    }

    @When("I enter valid credentials")
    public void enterValidCredentials() {
        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        username.sendKeys("standard_user");  // Masukkan username yang valid
        password.sendKeys("secret_sauce");  // Masukkan password yang valid
        loginButton.click();  // Klik tombol login
    }

    @When("I enter invalid credentials")
    public void enterInvalidCredentials() {
        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        username.sendKeys("invalid_user");  // Masukkan username yang invalid
        password.sendKeys("invalid_password");  // Masukkan password yang invalid
        loginButton.click();  // Klik tombol login
    }

    @When("I enter blank username")
    public void enterBlankUsername() throws InterruptedException {
        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        username.sendKeys("");  // Masukkan username kosong
        password.sendKeys("secret_sauce");  // Masukkan password yang valid
        loginButton.click();  // Klik tombol login

        Thread.sleep(2000);
    }

    @When("I enter blank password")
    public void enterBlankPassword() throws InterruptedException {
        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        username.sendKeys("standard_user");  // Masukkan username yang valid
        password.sendKeys("");  // Masukkan password kosong
        loginButton.click();  // Klik tombol login
        Thread.sleep(2000);
    }

    @Then("I should be logged in successfully")
    public void loggedInSuccessfully() {
        WebElement productPage = driver.findElement(By.className("title"));
        Assert.assertTrue(productPage.getText().contains("Products"));  // Verifikasi halaman produk muncul setelah login
    }

    @Then("I should see an error message")
    public void seeErrorMessage() {
        WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
        Assert.assertTrue(errorMessage.getText().contains("Epic sadface: Username and password do not match any user in this service"));  // Verifikasi pesan error muncul
    }

    @Then("I should see an error message for blank username")
    public void seeErrorMessageForBlankUsername() {
        WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
        Assert.assertTrue(errorMessage.getText().contains("Epic sadface: Username is required"));  // Verifikasi pesan error muncul
    }

    @Then("I should see an error message for blank password")
    public void seeErrorMessageForBlankPassword() {
        WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
        Assert.assertTrue(errorMessage.getText().contains("Epic sadface: Password is required"));  // Verifikasi pesan error muncul
    }

    @After  // Menutup browser setelah semua tes selesai
    public void tearDown() {
        WebDriverSingleton.closeDriver();  // Menutup WebDriver yang sama setelah tes selesai
    }
}