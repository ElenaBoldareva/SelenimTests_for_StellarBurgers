import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class EnterToAccountTest extends BaseTest {

    public EnterToAccountTest(String browser) {
        super(browser);
    }

    @Test
    @DisplayName("Enter to the Account Page by clicking on Personal account button")
    public void checkClickAccount() {
        mainPage.clickSingInButton();
        loginPage.waitForLoad(DEFAULT_TIMEOUT);
        loginPage.setEmail(user.getEmail());
        loginPage.setPassword(user.getPassword());
        loginPage.clickSignInButton();
        mainPage.waitForLoad(DEFAULT_TIMEOUT);
        mainPage.clickAccountLink();
        profilePage.waitForLoad(DEFAULT_TIMEOUT);

        String expectedUrl = HOST + "account/profile";
        Assert.assertEquals(expectedUrl, driver.getCurrentUrl());
    }
}
