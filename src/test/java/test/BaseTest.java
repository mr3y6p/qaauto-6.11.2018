package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import page.LoginPage;

public class BaseTest {
    protected WebDriver webDriver;
    protected LoginPage loginPage;

    @Parameters("browserName")
    @BeforeMethod
    public void beforeMethod(@Optional("chrome") String browserName) {
        if (browserName.toLowerCase().equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();
        }
        if (browserName.toLowerCase().equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
        } else {
            try {
                throw new Exception("browserName "+browserName+" is not supported.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        webDriver.manage().window().fullscreen();
        webDriver.get("https://www.linkedin.com");
        loginPage = new LoginPage(webDriver);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        webDriver.quit();
    }
}
