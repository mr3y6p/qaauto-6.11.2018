package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * PageObject class for login page
 */
public class LoginPage extends BasePage{

    @FindBy(xpath = "//*[@id='login-email']")
    private WebElement emailField;

    @FindBy(xpath = "//*[@id='login-password']")
    private WebElement passwordField;

    @FindBy(xpath = "//*[@id='login-submit']")
    private WebElement signInButton;

    @FindBy(xpath = "//a[@class='link-forgot-password']")
    private WebElement forgotPasswordLink;


    private String url = "https://www.linkedin.com/";

    /**
     * Constructor of LoginPage class.
     * @param webDriver - webDriver instance from Test.
     */
    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }


    /**
     * @param userEmail - email of user account
     * @param userPass - password of user account
     * @param <T> - parameter which describe multi type
     * @return HomePage or LoginSubmitPage or LoginPage objects. It depends on the userEmail and userPass params
     */
    public <T> T login(String userEmail, String userPass) {
        emailField.sendKeys(userEmail);
        passwordField.sendKeys(userPass);
        signInButton.click();
        if (webDriver.getCurrentUrl().contains("/feed")) {
            return (T) new HomePage(webDriver);
        }
        if (webDriver.getCurrentUrl().contains("/login-submit")) {
            return (T) new LoginSubmitPage(webDriver);
        }
        else {
            return (T) new LoginPage(webDriver);
        }
    }

    /**
     * Method to check if page is loaded
     * @return true/false
     */
    public boolean isPageLoaded() {
        return signInButton.isDisplayed()
                && webDriver.getTitle().equals("LinkedIn: Log In or Sign Up")
                && webDriver.getCurrentUrl().equals(url);
    }

    /**
     * Method that click on 'Forgot Password' link
     * @return RequestPasswordResetPage object.
     */
    public RequestPasswordResetPage clickOnForgotPasswordLink() {
        forgotPasswordLink.click();
        return new RequestPasswordResetPage(webDriver);
    }


}
