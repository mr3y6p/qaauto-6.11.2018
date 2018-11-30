import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class HomePage extends BasePage {

    @FindBy(xpath = "//*[@id='profile-nav-item']")
    private WebElement profileButton;
    @FindBy(xpath = "//a[@data-control-name='identity_welcome_message']")
    private WebElement welcomeMessage;
    @FindBy(xpath = "//div[@class='nav-search-bar']//input")
    private WebElement searchField;


    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }


    public boolean isPageLoaded() {
        return profileButton.isDisplayed()
                && webDriver.getTitle().contains("LinkedIn")
                && webDriver.getCurrentUrl().equals("https://www.linkedin.com/feed/")
                && welcomeMessage.isDisplayed();
    }

    public SearchResultsPage search(String searchTerm) {
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.RETURN);
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new SearchResultsPage(webDriver);
    }
}