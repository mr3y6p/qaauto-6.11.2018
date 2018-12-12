package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * PageObject class for login submit page
 */
public class LoginSubmitPage extends BasePage {

    @FindBy(xpath = "//form[@class='login__form']")
    private WebElement loginForm;

    @FindBy(xpath = "//*[@id='error-for-password']")
    private WebElement userPassError;

    @FindBy(xpath = "//*[@id='error-for-username']")
    private WebElement userEmailError;

    /**
     * Constructor of LoginSubmitPage class.
     * @param webDriver - webDriver instance from Test.
     */
    public LoginSubmitPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    /**
     * Method to check if page is loaded
     * @return true/false
     */
    public boolean isPageLoaded() {
        return loginForm.isDisplayed()
                && webDriver.getTitle().equals("Sign In to LinkedIn")
                && webDriver.getCurrentUrl().contains("uas/login-submit");
    }

    public String getUserEmailError() {
        return userEmailError.getText();
    }

    public String getUserPassError() {
        return userPassError.getText();
    }


}
