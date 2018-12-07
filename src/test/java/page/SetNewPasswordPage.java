package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SetNewPasswordPage extends BasePage{

    @FindBy(xpath = "//form[@id='confirm-password-reset-form']")
    private WebElement confirmPasswordResetForm;

    @FindBy(xpath = "//input[@id='newPassword']")
    private WebElement newPasswordField;

    @FindBy(xpath = "//input[@id='confirmPassword']")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement submitResetPasswordButton;

    public SetNewPasswordPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        return confirmPasswordResetForm.isDisplayed()
                && webDriver.getCurrentUrl().contains("https://www.linkedin.com/checkpoint/rp/password-reset")
                && webDriver.getTitle().contains("Reset Your Password | LinkedIn");
    }

    public PasswordResetSubmitPage resetPasswordSubmit(String newUserPass) {
        newPasswordField.sendKeys(newUserPass);
        confirmPasswordField.sendKeys(newUserPass);
        submitResetPasswordButton.click();
        return new PasswordResetSubmitPage(webDriver);
    }
}
