package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GMailService;

/**
 * PageObject class for base abstract page
 */
public abstract class BasePage {
    protected static GMailService gMailService;

    /**
     * Class which describes browser opening
     */
    protected WebDriver webDriver;

    protected void waitUntilElementIsVisible(WebElement elementToWaitFor) {
        waitUntilElementIsVisible(elementToWaitFor, 5);
    }

    protected void waitUntilElementIsVisible(WebElement elementToWaitFor, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeOutInSeconds);
        wait.until(ExpectedConditions.visibilityOf(elementToWaitFor));
    }

    /**
     * It is required class for each Page class
     * @return true/false
     */
    public abstract boolean isPageLoaded();

}
