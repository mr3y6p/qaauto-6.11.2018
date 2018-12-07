package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

public class RequestPasswordResetSubmitPage extends BasePage {

    @FindBy(xpath = "//*[contains(@class, 'form__action--sent-email')]")
    private WebElement sentEmilForm;

    public RequestPasswordResetSubmitPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public boolean isPageLoaded() {
        return sentEmilForm.isDisplayed()
                && webDriver.getCurrentUrl().contains("https://www.linkedin.com/checkpoint/rp/request-password-reset-submit")
                && webDriver.getTitle().contains("Please check your mail for reset password link. | LinkedIn");
    }


    public SetNewPasswordPage navigateToLinkFromEmail() {
        String messageSubject = "here's the link to reset your password";
        String messageTo = "mr3y6p+tet@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";

        String message = gMailService.waitMessage(
                messageSubject, messageTo, messageFrom, 60);
        System.out.println("Connect: " + message);

        return new SetNewPasswordPage(webDriver);
    };
}

