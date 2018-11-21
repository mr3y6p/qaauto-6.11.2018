import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private WebDriver webDriver;

    private WebElement emailField;
    private WebElement passwordField;
    private WebElement signInButton;
    private String url;
    WebElement forgotPasswordLink;
    WebElement submitRegistrationButton;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        initElements();
    }

    private void initElements() {
        emailField = webDriver.findElement(By.xpath("//*[@id='login-email']"));
        passwordField = webDriver.findElement(By.xpath("//*[@id='login-password']"));
        signInButton = webDriver.findElement(By.xpath("//*[@id='login-submit']"));
        forgotPasswordLink = webDriver.findElement(By.xpath("//*[@class='link-forgot-password']"));
        submitRegistrationButton = webDriver.findElement(By.xpath("//*[@id='registration-submit']"));
        url = "https://www.linkedin.com/";
    }

    public void login(String userEmail, String userPass) {
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPass);
        signInButton.click();
    }

    public boolean isPageLoaded() {
        return signInButton.isDisplayed()
                && webDriver.getTitle().equals("LinkedIn: Log In or Sign Up")
                && webDriver.getCurrentUrl().equals(url);
    }


}
