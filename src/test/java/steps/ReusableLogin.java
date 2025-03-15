package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ReusableLogin {

    public WebDriver driver;

    public ReusableLogin(WebDriver driver) {
        this.driver = driver;  // Gunakan driver yang sudah ada
    }

    public void loginWithValidCredentials() {
        driver.get("https://www.saucedemo.com");  // Arahkan ke halaman login
        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        username.sendKeys("standard_user");  // Masukkan username yang valid
        password.sendKeys("secret_sauce");  // Masukkan password yang valid
        loginButton.click();  // Klik tombol login
    }
}
