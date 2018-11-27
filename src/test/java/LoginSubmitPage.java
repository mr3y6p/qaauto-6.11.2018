import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginSubmitPage {

    private WebDriver webDriver;

    private WebElement loginForm;
    private WebElement userPasswordError;
    private WebElement userEmailError;
    private String wrongPasswordMessage = "Hmm, that's not the right password. Please try again or request a new one.";

    public LoginSubmitPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        initElements();
    }

    private void initElements() {
        loginForm = webDriver.findElement(By.xpath("//form[@class='login__form']"));
        userPasswordError = webDriver.findElement(By.xpath("//*[@id='error-for-password']"));
        userEmailError = webDriver.findElement(By.xpath("//*[@id='error-for-username']"));
    }

    public boolean isPageLoaded() {
        return loginForm.isDisplayed()
                && userPasswordError.equals(wrongPasswordMessage)
                && userEmailError.equals("");
    }


}
