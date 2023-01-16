package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;

    private final By emailField = By.name("name");
    private final By passwordField = By.name("Пароль");
    private final By signInButton = By.xpath(".//button[text() = 'Войти']");
    private final By registerLink = By.linkText("Зарегистрироваться");
    private final By forgotPasswordLink = By.linkText("Восстановить пароль");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoad(int seconds) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.visibilityOfElementLocated(signInButton));
    }

    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickSignInButton() {
        Actions actions = new Actions(driver);
        WebElement button = driver.findElement(signInButton);
        actions.moveToElement(button).click().build().perform();
    }

    public void clickRegisterLink() {
        driver.findElement(registerLink).click();
    }

    public void clickForgotPasswordLink() {
        driver.findElement(forgotPasswordLink).click();
    }

}
