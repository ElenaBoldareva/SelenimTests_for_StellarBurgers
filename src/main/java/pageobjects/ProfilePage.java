package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    private final WebDriver driver;

    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    private final By logoButton = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']/a");
    private final By signOutButton = By.xpath(".//button[text() = 'Выход']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }
    public void clickConstructorButton() {
       driver.findElement(constructorButton).click();
    }

    public void clickLogo() {
        driver.findElement(logoButton).click();
    }

    public void clickSignOutButton() {
        Actions actions = new Actions(driver);
        WebElement button = driver.findElement(signOutButton);
        actions.moveToElement(button).click().build().perform();
    }

    public void waitForLoad(int seconds) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.visibilityOfElementLocated(signOutButton));
    }
}
