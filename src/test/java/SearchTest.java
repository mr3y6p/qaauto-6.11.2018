import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTest {
    WebDriver webDriver;
    HomePage homePage;

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new ChromeDriver();
        webDriver.manage().window().fullscreen();
        webDriver.get("https://www.linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);
        homePage = loginPage.login("mr3y6p+test@gmail.com", "q0w9e8r7");
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        webDriver.quit();
    }


    /**
     * Preconditions:
     * - Open browser
     * - Navigate to linkedin.com
     * - Login with valid credentials
     *
     * Scenario:
     * - Verify Home page is loaded
     * - Enter "HR" into searchField
     * - Press Return button on keyboard
     * - Verify SearchResult page is loaded
     * - Verify results list contains 10 items
     * - Verify each item contains searchTerm
     *
     * Postcondition:
     * - Close browser
     */
    @Test
    public void basicSearchTest() {
        String searchTerm = "HR";

        Assert.assertTrue(homePage.isPageLoaded(), "Home page is not loaded");

        SearchResultsPage searchResultsPage = homePage.search(searchTerm);
        Assert.assertTrue(searchResultsPage.isPageLoaded(), "SearchResult page is not loaded");

    }
}
