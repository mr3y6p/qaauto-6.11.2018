import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResultsPage extends BasePage {

    @FindBy(xpath = "//div[contains(@class, 'search-filters-bar')]")
    private WebElement searchFilterBar;
    @FindBy(xpath = "//li[@class='search-result search-result__occluded-item ember-view']")
    private List<WebElement> resultList;


    public SearchResultsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    boolean isPageLoaded() {
        return searchFilterBar.isDisplayed()
                && webDriver.getTitle().contains("Search | LinkedIn")
                && webDriver.getCurrentUrl().contains("https://www.linkedin.com/search/results");
    }

    int getResultsNumber() {
        return resultList.size();
    }

    boolean isSearchTermExist(String searchTerm) {
        for (int i=0; i<resultList.size(); i++) {
            if (!resultList.get(i).getText().contains(searchTerm)) {
                return false;
            }
        }

        return true;
    }
}
