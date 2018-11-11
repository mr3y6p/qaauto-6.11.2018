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
        searchField.sendKeys("Selenium");
//        searchField.submit();
        searchField.sendKeys(Keys.ENTER);

        List<WebElement> resultList = webDriver.findElements(By.xpath("//div[@class='srg']/div[@class='g']"));
        System.out.println(resultList.size());
        String newLine = System.getProperty("line.separator");
        for (int i=0; i<resultList.size(); i++){
            if (resultList.get(i).getText().contains("Selenium")) {
                System.out.println(i + ") " + resultList.get(i).getText() + newLine + "searchTerm found" + newLine);
            }
            else {
                System.out.println(i + ") " + resultList.get(i).getText()+ newLine + "searchTerm not found" + newLine);
            }
        }

        webDriver.quit();
    }

}
