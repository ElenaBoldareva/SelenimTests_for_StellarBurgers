import helper.UserAPIHelper;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageobjects.*;
import pojo.User;
import util.UserUtils;

import java.time.Duration;

public class BaseTest {

    protected static final String HOST = "https://stellarburgers.nomoreparties.site/";
    protected static final int DEFAULT_TIMEOUT = 60;
    protected final String browser;
    protected WebDriver driver;
    protected UserAPIHelper userAPIHelper;
    protected User user;
    protected String accessToken;
    protected MainPage mainPage;
    protected LoginPage loginPage;
    protected RegisterPage registerPage;
    protected ProfilePage profilePage;
    protected ForgotPasswordPage forgotPasswordPage;

    public BaseTest(String browser) {
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

        userAPIHelper = new UserAPIHelper();
        user = UserUtils.getRandomUser();
        accessToken = userAPIHelper.createUser(user);

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        profilePage = new ProfilePage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        mainPage.waitForLoad(DEFAULT_TIMEOUT);
    }

    @After
    public void teardown() {
        driver.quit();
        userAPIHelper.deleteUser(accessToken);
    }
}
