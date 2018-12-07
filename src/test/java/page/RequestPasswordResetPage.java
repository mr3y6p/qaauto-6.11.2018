package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class RequestPasswordResetPage extends BasePage {

    @FindBy(xpath = "//*[@id='reset-password-form']")
    private WebElement resetPasswordForm;
    @FindBy(xpath = "//input[@id='username']")
    private WebElement emailField;
    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement findAccountButton;

    public RequestPasswordResetPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        return resetPasswordForm.isDisplayed()
                && webDriver.getCurrentUrl().contains("https://www.linkedin.com/uas/request-password-reset")
                && webDriver.getTitle().contains("Reset Password | LinkedIn");
    }

    public RequestPasswordResetSubmitPage requestResetPassword(String userEmail) {
        emailField.sendKeys(userEmail);
        findAccountButton.click();
        return new RequestPasswordResetSubmitPage(webDriver);
    }
}
