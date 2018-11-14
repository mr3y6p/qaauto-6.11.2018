import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class BadCodeExample {
    public static void main(String[] args) {
        System.out.println("Hello World");
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().window().fullscreen();
        webDriver.get("https://google.com.ua/");
        WebElement searchField = webDriver.findElement(By.name("q"));
        String searchTerm = "Selenium";
        searchField.sendKeys(searchTerm);
        // searchField.submit(); is not user behaviour
        searchField.sendKeys(Keys.ENTER);

        List<WebElement> resultList = webDriver.findElements(By.xpath("//div[@class='srg']/div[@class='g']"));
        System.out.println(resultList.size());

        // resolve by "for" loop

        for (int i=0; i<resultList.size(); i++){
            if (resultList.get(i).getText().contains(searchTerm)) {
                System.out.println(i + ") " + resultList.get(i).getText() + "\n" + searchTerm + " found\n");
            }
            else {
                System.out.println(i + ") " + resultList.get(i).getText() + "\n" + searchTerm + "not found\n");
            }
        }

        // resolve by "for each"  loop
        // for each WebElement 'result' in List of WebElements 'resultList' print Text.

        for (WebElement result : resultList) {
            String resultText = result.getText();

            if (resultText.toLowerCase().contains(searchTerm.toLowerCase())) {
                System.out.println("searchTerm " + searchTerm + " found in block:\n" + resultText);
            }
            else {
                System.out.println("searchTerm " + searchTerm + "not found in block:\n" + resultText);
            }
        }

        webDriver.close();
    }

}