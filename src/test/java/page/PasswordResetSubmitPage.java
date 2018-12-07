package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PasswordResetSubmitPage extends BasePage {

    @FindBy(xpath = "//form[@id='reset-password-confirm-form']")
    private WebElement resetPasswordConfirmForm;

    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement openHomepageButton;

    public PasswordResetSubmitPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        return resetPasswordConfirmForm.isDisplayed()
                && webDriver.getCurrentUrl().contains("https://www.linkedin.com/checkpoint/rp/password-reset-submit")
                && webDriver.getTitle().contains("You've successfully reset your password. | LinkedIn");
    }

    public HomePage open() {
        openHomepageButton.click();
        return new HomePage(webDriver);
    }
}
