package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;

    public void clickSingInButton() {
        driver.findElement(signInButton).click();
    }
    private final By accountLink = By.xpath(".//p[text()='Личный Кабинет']");
    private final By collectBurgerText = By.xpath(".//h1[text() = 'Соберите бургер']");
    private final By signInButton = By.xpath(".//button[text() = 'Войти в аккаунт']");
    private final By bunButton = By.xpath(".//span[text() = 'Булки']/parent::div");
    private final By sauceButton = By.xpath(".//span[text() = 'Соусы']/parent::div");
    private final By fillingButton = By.xpath(".//span[text() = 'Начинки']/parent::div");
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoad(int seconds) {
        new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(ExpectedConditions.visibilityOfElementLocated(collectBurgerText));
    }

    public void clickAccountLink() {
        driver.findElement(accountLink).click();
    }

    public void clickBunButton() {
        driver.findElement(bunButton).click();
    }

    public void clickSauceButton() {
        driver.findElement(sauceButton).click();
    }

    public void clickFillingButton() {
        driver.findElement(fillingButton).click();
    }

    public boolean isBunSelect() {
        return driver.findElement(bunButton).getAttribute("class").contains("current");
    }

    public boolean isSauceSelect() {
        return driver.findElement(sauceButton).getAttribute("class").contains("current");
    }

    public boolean isFillingSelect() {
        return driver.findElement(fillingButton).getAttribute("class").contains("current");
    }
}

