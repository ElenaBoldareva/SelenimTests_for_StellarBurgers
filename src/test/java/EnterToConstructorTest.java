import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class EnterToConstructorTest extends BaseTest {

    public EnterToConstructorTest(String browser) {
        super(browser);
    }

    @Test
    @DisplayName("Enter to the Constructor page from the Account Page by clicking on the Constructor button")
    public void checkClickConstructor() {
        mainPage.clickSingInButton();
        loginPage.waitForLoad(DEFAULT_TIMEOUT);
        loginPage.setEmail(user.getEmail());
        loginPage.setPassword(user.getPassword());
        loginPage.clickSignInButton();
        mainPage.waitForLoad(DEFAULT_TIMEOUT);
        mainPage.clickAccountLink();
        profilePage.waitForLoad(DEFAULT_TIMEOUT);
        profilePage.clickConstructorButton();
        Assert.assertEquals(HOST, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Enter to the Constructor page from the Account Page by clicking on the Logo button")
    public void checkClickLogo() {
        mainPage.clickSingInButton();
        loginPage.waitForLoad(DEFAULT_TIMEOUT);
        loginPage.setEmail(user.getEmail());
        loginPage.setPassword(user.getPassword());
        loginPage.clickSignInButton();
        mainPage.waitForLoad(DEFAULT_TIMEOUT);
        mainPage.clickAccountLink();
        profilePage.waitForLoad(DEFAULT_TIMEOUT);
        profilePage.clickLogo();

        Assert.assertEquals(HOST, driver.getCurrentUrl());
    }
}
