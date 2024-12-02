import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
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
        User user = UserUtils.getRandomUser();

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

        userAPIHelper.deleteUser(user);
    }

    @Test
    @DisplayName("Check Error for invalid password")
    public void checkRegistrationWrongPassword() {
        User wrongUser = new User(UserUtils.getRandomEmail(11), "12345", "Tom");

        mainPage.clickAccountLink();
        loginPage.waitForLoad(DEFAULT_TIMEOUT);
        loginPage.clickRegisterLink();
        registerPage.waitForLoad(DEFAULT_TIMEOUT);
        registerPage.setName(wrongUser.getName());
        registerPage.setEmail(wrongUser.getEmail());
        registerPage.setPassword(wrongUser.getPassword());
        registerPage.clickRegisterButton();

        Assert.assertTrue(registerPage.isWrongPassword());
    }
}

