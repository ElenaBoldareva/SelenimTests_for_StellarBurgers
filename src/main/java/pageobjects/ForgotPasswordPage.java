package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage {
    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    private final WebDriver driver;
    private final By loginInLink = By.linkText("Войти");

    public void waitForLoad(int seconds) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.visibilityOfElementLocated(loginInLink));
    }

    public void clickLoginLink() {
        driver.findElement(loginInLink).click();
    }
}
