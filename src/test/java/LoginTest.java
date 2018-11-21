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

        //Verify that page url is "https://www.linkedin.com/feed/"
        Assert.assertEquals(webDriver.getCurrentUrl(), url + "/feed/", "Home page url is wrong");
        //Verify that page title is "LinkedIn"
        Assert.assertTrue(webDriver.getTitle().contains("LinkedIn"), "Home page title is wrong");
        //Verify that "Profile" button exist
        Assert.assertTrue(homePage.profileButton.isDisplayed(), "Home page profile button is absent");
    }

    @Test
    public void forgotPasswordTest() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.forgotPasswordLink.click();

        WebElement resetPasswordButton = webDriver.findElement(By.xpath("//*[@id='reset-password-submit-button']"));
        WebElement joinNowButton = webDriver.findElement(By.xpath("//*[@class='nav__button--joinnow']"));
        //Verify that "Reset Password" button exist
        Assert.assertTrue(resetPasswordButton.isDisplayed(), "Reset password button is absent");
        //Verify that "Join Now" button exist
        Assert.assertTrue(joinNowButton.isDisplayed(), "Join Now button is absent");

    }

    @Test
    public void wrongRegistrationTest() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.submitRegistrationButton.click();

        WebElement wrongRegistrationAlert = webDriver.findElement(By.xpath("//*[@class='reg-alert']"));
        //Verify that "Wrong Registration" alert exist
        Assert.assertTrue(wrongRegistrationAlert.isDisplayed(), "Wrong registration alert is absent");
    }

    @Test
    public void negativeLeadsToLoginSubmitPage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("mr3y6p+test@gmail.com", "wrongPassword");

        WebElement loginForm = webDriver.findElement(By.xpath("//form[@class='login__form']"));
        Assert.assertTrue(loginForm.isDisplayed(), "Login Submit page is not loaded");

        //Verify that error message about wrong password appears
        WebElement userPasswordError = webDriver.findElement(By.xpath("//*[@id='error-for-password']"));
        String wrongPasswordMessage = "Hmm, that's not the right password. Please try again or request a new one.";
        Assert.assertEquals(userPasswordError.getText(), wrongPasswordMessage, "Alert message is wrong");

        //Verify that error message about wrong email does not appear
        WebElement userEmailError = webDriver.findElement(By.xpath("//*[@id='error-for-username']"));
        Assert.assertEquals(userEmailError.getText(), "", "Alert message is wrong");
    }

}
