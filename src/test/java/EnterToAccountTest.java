import helper.UserAPIHelper;
import io.qameta.allure.junit4.DisplayName;
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
import pageobjects.LoginPage;
import pageobjects.MainPage;
import pageobjects.RegisterPage;
import pojo.User;
import util.UserUtils;

import java.time.Duration;

@RunWith(Parameterized.class)
public class EnterToAccountTest {
    private static final String HOST = "https://stellarburgers.nomoreparties.site/";
    private static final int DEFAULT_TIMEOUT = 60;
    private final String browser;
    private WebDriver driver;
    private UserAPIHelper userAPIHelper;

    public EnterToAccountTest(String browser) {
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
        userAPIHelper = new UserAPIHelper();

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
    @DisplayName("Enter to the Account Page by clicking on Personal account button")
    public void checkClickAccount() throws InterruptedException {

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        User user = UserUtils.getRandomUser();
        mainPage.clickAccountLink();
        loginPage.clickRegisterLink();
        registerPage.setName(user.getName());
        registerPage.setEmail(user.getEmail());
        registerPage.setPassword(user.getPassword());
        Thread.sleep(500);
        registerPage.clickRegisterButton();
        loginPage.waitForLoad(DEFAULT_TIMEOUT);
        loginPage.setEmail(user.getEmail());
        loginPage.setPassword(user.getPassword());
        Thread.sleep(500);
        loginPage.clickSignInButton();
        mainPage.waitForLoad(DEFAULT_TIMEOUT);
        mainPage.clickAccountLink();
        Thread.sleep(1000);

        String expectedUrl = HOST + "account/profile";
        Assert.assertEquals(expectedUrl, driver.getCurrentUrl());

        userAPIHelper.deleteUser(user);
    }
}
