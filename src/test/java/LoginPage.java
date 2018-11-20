import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver webDriver;

    WebElement emailField;
    WebElement passwordField;
    WebElement signInButton;
    WebElement forgotPasswordLink;
    WebElement submitRegistrationButton;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        initElements();
    }

    public void initElements() {
        emailField = webDriver.findElement(By.xpath("//*[@id='login-email']"));
        passwordField = webDriver.findElement(By.xpath("//*[@id='login-password']"));
        signInButton = webDriver.findElement(By.xpath("//*[@id='login-submit']"));
        forgotPasswordLink = webDriver.findElement(By.xpath("//*[@class='link-forgot-password']"));
        submitRegistrationButton = webDriver.findElement(By.xpath("//*[@id='registration-submit']"));
    }

    public void login(String userEmail, String userPass) {
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPass);
        signInButton.click();
    }


}
