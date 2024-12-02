import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class LogOutTest extends BaseTest {

    public LogOutTest(String browser) {
        super(browser);
    }

    @Test
    @DisplayName("Check the exit by clicking the Exit button in your account.")
    public void checkClickAccount() {
        mainPage.clickSingInButton();
        loginPage.waitForLoad(DEFAULT_TIMEOUT);
        loginPage.setEmail(user.getEmail());
        loginPage.setPassword(user.getPassword());
        loginPage.clickSignInButton();
        mainPage.waitForLoad(DEFAULT_TIMEOUT);
        mainPage.clickAccountLink();
        profilePage.waitForLoad(DEFAULT_TIMEOUT);
        profilePage.clickSignOutButton();
        loginPage.waitForLoad(DEFAULT_TIMEOUT);

        String expectedUrl = HOST + "login";
        Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
    }
}
