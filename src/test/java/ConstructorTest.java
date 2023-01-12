import helper.UserAPIHelper;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobjects.MainPage;

import java.time.Duration;

@RunWith(Parameterized.class)
public class ConstructorTest {

    private static final String HOST = "https://stellarburgers.nomoreparties.site/";
    private static final int DEFAULT_TIMEOUT = 60;
    private final String browser;
    private WebDriver driver;

    public ConstructorTest(String browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {"chrome"},
                {"yandex"}
        };
    }

    @Before
    public void startUp() {
        RestAssured.baseURI = HOST;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        if ("chrome".equals(browser)) {
            System.setProperty("webdriver.chrome.driver", "C:/WebDriver/bin/chromedriver.exe");
        } else if ("yandex".equals(browser)) {
            System.setProperty("webdriver.chrome.driver", "C:/WebDriver/bin/yandex/chromedriver.exe");
            options.setBinary("C:/Users/pitry/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");
        }
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULT_TIMEOUT));
        driver.get(HOST);
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    public void checkConstructor() {

        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoad(DEFAULT_TIMEOUT);

        mainPage.clickSauceButton();
        Assert.assertTrue(mainPage.isSauceSelect());

        mainPage.clickFillingButton();
        Assert.assertTrue(mainPage.isFillingSelect());

        mainPage.clickBunButton();
        Assert.assertTrue(mainPage.isBunSelect());
    }
}
