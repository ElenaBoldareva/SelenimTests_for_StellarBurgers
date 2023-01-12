package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    private final WebDriver driver;
    private final By loginInLink = By.linkText("Войти");

    public void clickLoginLink() {
        driver.findElement(loginInLink).click();
    }
}
