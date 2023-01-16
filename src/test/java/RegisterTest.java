import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageobjects.LoginPage;
import pageobjects.MainPage;
import pageobjects.RegisterPage;
import pojo.User;
import util.UserUtils;

@RunWith(Parameterized.class)
public class RegisterTest extends BaseTest {

    public RegisterTest(String browser) {
        super(browser);
    }

    @Test
    @DisplayName("Check Successful Registration")
    public void checkRegistration() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        User user = UserUtils.getRandomUser();

        mainPage.waitForLoad(DEFAULT_TIMEOUT);
        mainPage.clickAccountLink();
        loginPage.waitForLoad(DEFAULT_TIMEOUT);
        loginPage.clickRegisterLink();
        registerPage.waitForLoad(DEFAULT_TIMEOUT);
        registerPage.setName(user.getName());
        registerPage.setEmail(user.getEmail());
        registerPage.setPassword(user.getPassword());
        registerPage.clickRegisterButton();
        loginPage.waitForLoad(DEFAULT_TIMEOUT);

        String expectedUrl = HOST + "login";
        Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Check Error for invalid password")
    public void checkRegistrationWrongPassword() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);

        User user = new User(UserUtils.getRandomEmail(11), "12345", "Tom");

        mainPage.waitForLoad(DEFAULT_TIMEOUT);
        mainPage.clickAccountLink();
        loginPage.waitForLoad(DEFAULT_TIMEOUT);
        loginPage.clickRegisterLink();
        registerPage.waitForLoad(DEFAULT_TIMEOUT);
        registerPage.setName(user.getName());
        registerPage.setEmail(user.getEmail());
        registerPage.setPassword(user.getPassword());
        registerPage.clickRegisterButton();

        Assert.assertTrue(registerPage.isWrongPassword());
    }
}

