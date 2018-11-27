import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
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


    @Test
    public void successfulLoginTest() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("mr3y6p+test@gmail.com", "q0w9e8r7");

        HomePage homePage = new HomePage(webDriver);

        //Verify that Home page is loaded
        Assert.assertTrue(homePage.isPageLoaded(), "Home page is not loaded");
    }


    @Test
    public void negativeLeadsToLoginSubmitPage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("mr3y6p+test@gmail.com", "wrongPassword");

        LoginSubmitPage loginSubmitPage = new LoginSubmitPage(webDriver);

        //Verify that Login Submit page is loaded
        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "Login submit page is not loaded");
    }

}
