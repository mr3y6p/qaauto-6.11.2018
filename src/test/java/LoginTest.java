import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest {
    WebDriver webDriver;
    String url;

    @BeforeMethod
    public void beforeMethod() {
        url = "https://www.linkedin.com";
        webDriver = new ChromeDriver();
        webDriver.manage().window().fullscreen();
        webDriver.get(url);
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.quit();
    }

    @DataProvider
    public Object[][] emptyFieldDataProvider() {
        return new Object[][]{
                { "mr3y6p+test@gmail.com", "" },
                { "", "q0w9e8r7" }
        };
    }

    @Test(dataProvider = "emptyFieldDataProvider")
    public void emptyPasswordTest(String userEmail, String userPass) {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(userEmail, userPass);

        //Verify that Login page is still loaded
        Assert.assertTrue(loginPage.isPageLoaded(), "LogIn page is not loaded");
    }

    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                { "mr3y6p+test@gmail.com", "q0w9e8r7" },
                { "mr3y6p+test@Gmail.COM", "q0w9e8r7" },
                { " mr3y6p+test@gmail.com ", "q0w9e8r7" }
        };
    }


    @Test(dataProvider = "validDataProvider")
    public void successfulLoginTest(String userEmail, String userPass) {
        LoginPage loginPage = new LoginPage(webDriver);
        HomePage homePage = loginPage.login(userEmail, userPass);

        //Verify that Home page is loaded
        Assert.assertTrue(homePage.isPageLoaded(), "Home page is not loaded");
    }


    @DataProvider
    public Object[][] incorrectFieldDataProvider() {
        return new Object[][]{
                { "mr3y6p+test@gmail.comq", "q0w9e8r7", "We don't recognize that email. Did you mean: @gmail.com?", ""},
                { "mr3y6p+test@gmail.com", "testtest", "", "Hmm, that's not the right password. Please try again or request a new one."},
                { "mr3y6p+test@amazos.com", "q0w9e8r7", "We don't recognize that email. Did you mean: @amazon.com?", ""},
                { "mr3y6p+test@linkedinn.com", "q0w9e8r7", "We don't recognize that email. Did you mean: @linkedin.com?", ""},
                { "mr3y6p+test1@gmail.com", "q0w9e8r7", "Hmm, we don't recognize that email. Please try again.", ""}
        };
    }


    @Test(dataProvider = "incorrectFieldDataProvider")
    public void negativeLeadsToLoginSubmitPage(String userEmail, String userPass, String emailError, String passError) {
        LoginPage loginPage = new LoginPage(webDriver);

        LoginSubmitPage loginSubmitPage = loginPage.login(
                userEmail, userPass);

        Assert.assertTrue(loginSubmitPage.isPageLoaded(),
                "Login Submit page is not loaded.");

        Assert.assertEquals(loginSubmitPage.getUserEmailError(),
                emailError,
                "userEmail Validation message is wrong.");

        Assert.assertEquals(loginSubmitPage.getUserPassError(),
                passError,
                "userPass Validation message is wrong.");
    }

}
