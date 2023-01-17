import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class ConstructorTest extends BaseTest {

    public ConstructorTest(String browser) {
        super(browser);
    }

    @Test
    @DisplayName("Checking the choice of ingredients")
    public void checkConstructor() {
        mainPage.clickSauceButton();
        Assert.assertTrue(mainPage.isSauceSelect());

        mainPage.clickFillingButton();
        Assert.assertTrue(mainPage.isFillingSelect());

        mainPage.clickBunButton();
        Assert.assertTrue(mainPage.isBunSelect());
    }
}
