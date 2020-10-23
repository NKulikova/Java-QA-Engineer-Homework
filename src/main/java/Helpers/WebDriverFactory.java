package Helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class WebDriverFactory {

    public enum Browser {
        CHROME,
        FIREFOX,
        OPERA,
        EDGE,
        IE,
        SELENIUM_SERVER_STANDALONE,
        SAFARI;
    };

    public WebDriver createWebDriver(Browser webDriverName) {
        return createWebDriver(webDriverName, null);
    }

    public WebDriver createWebDriver(Browser webDriverName, MutableCapabilities options) {
        switch (webDriverName) {
            case CHROME: {
                WebDriverManager.chromedriver().setup();
                if (options instanceof ChromeOptions) { return new ChromeDriver((ChromeOptions)options); }
                return new ChromeDriver();
            }
            case FIREFOX: {
                WebDriverManager.firefoxdriver().setup();
                if (options instanceof FirefoxOptions) { return new FirefoxDriver((FirefoxOptions)options); }
                return new FirefoxDriver();
            }
            case EDGE: {
                WebDriverManager.edgedriver().setup();
                if (options instanceof EdgeOptions) { return new EdgeDriver((EdgeOptions)options); }
                return new EdgeDriver();
            }
            case IE:
            default: {
                WebDriverManager.iedriver().setup();
                if (options instanceof InternetExplorerOptions) { return new InternetExplorerDriver((InternetExplorerOptions)options); }
                return new InternetExplorerDriver();
            }
        }
    }

}