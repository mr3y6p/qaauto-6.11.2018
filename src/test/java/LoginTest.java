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

        //Verify that page title is "LinkedIn: Log In or Sign Up"
        Assert.assertEquals(webDriver.getTitle(),"LinkedIn: Log In or Sign Up", "Login page title is wrong.");
        //Verify that "LogIn" button exist
        Assert.assertTrue(loginPage.signInButton.isDisplayed(), "LogIn button is absent");
    }

    @Test
    public void wrongPasswordTest() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("mr3y6p+test@gmail.com", "wrongPassword");

        WebElement wrongPasswordAlert = webDriver.findElement(By.xpath("//*[@id='error-for-password']"));
        String wrongPasswordMessage = "Hmm, that's not the right password. Please try again or request a new one.";
        //Verify that page url is "https://www.linkedin.com/uas/login-submit?loginSubmitSource=GUEST_HOME"
        Assert.assertEquals(webDriver.getCurrentUrl(), url+"/uas/login-submit?loginSubmitSource=GUEST_HOME", "Forgot password page url is wrong");
        //Verify that error message about wrong password appears
        Assert.assertEquals(wrongPasswordAlert.getText(), wrongPasswordMessage, "Alert message is wrong");
    }

    @Test
    public void successfulLoginTest() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("mr3y6p+test@gmail.com", "q0w9e8r7");

        WebElement profileButton = webDriver.findElement(By.xpath("//*[@id='profile-nav-item']"));
        //Verify that page url is "https://www.linkedin.com/feed/"
        Assert.assertEquals(webDriver.getCurrentUrl(), url+"/feed/", "Home page url is wrong");
        //Verify that page title is "LinkedIn"
        Assert.assertTrue(webDriver.getTitle().contains("LinkedIn"), "Home page title is wrong");
        //Verify that "Profile" button exist
        Assert.assertTrue(profileButton.isDisplayed(), "Home page profile button is absent");
    }
}
