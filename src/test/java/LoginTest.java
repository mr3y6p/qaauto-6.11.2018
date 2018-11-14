import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {

    @Test
    public void negativeLoginTest() {
        String url = "https://www.linkedin.com/";
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().fullscreen();
        webDriver.get(url);

        WebElement emailField = webDriver.findElement(By.xpath("//*[@id='login-email']"));
        WebElement passwordField = webDriver.findElement(By.xpath("//*[@id='login-password']"));
        WebElement signInButton = webDriver.findElement(By.xpath("//*[@id='login-submit']"));

        emailField.sendKeys("a@b.c");
        passwordField.sendKeys("");
        signInButton.click();

        //Verify that page title is "LinkedIn: Log In or Sign Up"
        Assert.assertEquals(webDriver.getTitle(),"LinkedIn: Log In or Sign Up");

        webDriver.close();
    }

    @Test
    public void wrongPasswordTest() {
        String url = "https://www.linkedin.com";
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().fullscreen();
        webDriver.get(url);

        WebElement emailField = webDriver.findElement(By.xpath("//*[@id='login-email']"));
        WebElement passwordField = webDriver.findElement(By.xpath("//*[@id='login-password']"));
        WebElement signInButton = webDriver.findElement(By.xpath("//*[@id='login-submit']"));
        emailField.sendKeys("mr3y6p+test@gmail.com");
        passwordField.sendKeys("WrongPassword");
        signInButton.click();

        WebElement wrongPasswordAlert = webDriver.findElement(By.xpath("//*[@id='error-for-password']"));
        String wrongPasswordMessage = "Hmm, that's not the right password. Please try again or request a new one.";
        //Verify that page url is "https://www.linkedin.com/uas/login-submit?loginSubmitSource=GUEST_HOME"
        Assert.assertEquals(webDriver.getCurrentUrl(), url+"/uas/login-submit?loginSubmitSource=GUEST_HOME");
        //Verify that error message about wrong password appears
        Assert.assertEquals(wrongPasswordAlert.getText(), wrongPasswordMessage);

        webDriver.close();
    }

    @Test
    public void successfulLoginTest() {
        String url = "https://www.linkedin.com";
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().fullscreen();
        webDriver.get(url);

        WebElement emailField = webDriver.findElement(By.xpath("//*[@id='login-email']"));
        WebElement passwordField = webDriver.findElement(By.xpath("//*[@id='login-password']"));
        WebElement signInButton = webDriver.findElement(By.xpath("//*[@id='login-submit']"));
        emailField.sendKeys("mr3y6p+test@gmail.com");
        passwordField.sendKeys("q0w9e8r7");
        signInButton.click();

        WebElement profileButton = webDriver.findElement(By.xpath("//*[@id='profile-nav-item']"));
        //Verify that page url is "https://www.linkedin.com/feed/"
        Assert.assertEquals(webDriver.getCurrentUrl(), url+"/feed/");
        //Verify that page title is "LinkedIn"
        Assert.assertEquals(webDriver.getTitle(), "LinkedIn");
        //Verify that "Profile" button exist
        Assert.assertEquals(profileButton.isDisplayed(),true);

        webDriver.close();
    }
}
