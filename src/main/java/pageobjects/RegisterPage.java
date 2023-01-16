package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private final WebDriver driver;

    private final By nameField = By.name("name");
    private final By passwordField = By.name("Пароль");
    private final By registerButton = By.xpath(".//button[text() = 'Зарегистрироваться']");
    private final By wrongPasswordText = By.xpath(".//p[text() = 'Некорректный пароль']");
    private final By loginLink = By.linkText("Войти");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoad(int seconds) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.visibilityOfElementLocated(registerButton));
    }

    public void setName(String name) {
        driver.findElements(nameField).get(0).sendKeys(name);
    }

    public void setEmail(String email) {
        driver.findElements(nameField).get(1).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickRegisterButton() {
        Actions actions = new Actions(driver);
        WebElement button = driver.findElement(registerButton);
        actions.moveToElement(button).click().build().perform();
    }

    public boolean isWrongPassword() {
        return !driver.findElements(wrongPasswordText).isEmpty();
    }
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
}
