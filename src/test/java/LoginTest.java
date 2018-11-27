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

    @Test
    public void emptyPasswordTest() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("mr3y6p+test@gmail.com", "");

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


    @Test
    public void negativeLeadsToLoginSubmitPage() {
        LoginPage loginPage = new LoginPage(webDriver);

        LoginSubmitPage loginSubmitPage = loginPage.login(
                "mr3y6p+test@gmail.com", "wrong");

        Assert.assertTrue(loginSubmitPage.isPageLoaded(),
                "Login Submit page is not loaded.");

        Assert.assertEquals(loginSubmitPage.getUserEmailError(),
                "",
                "userEmail Validation message is wrong.");

        Assert.assertEquals(loginSubmitPage.getUserPassError(),
                "Hmm, that's not the right password. Please try again or request a new one.",
                "userPass Validation message is wrong.");
    }

}
