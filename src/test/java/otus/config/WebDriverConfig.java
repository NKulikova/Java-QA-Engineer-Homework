package otus.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@ComponentScan("otus")
public class WebDriverConfig {

    private static WebDriver driver;

//    @Qualifier("chrome")
//    @Bean
//    public WebDriver chromeDriver() {
//        WebDriverManager.chromedriver().setup();
//        ChromeDriver driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
//        return driver;
//    }

    @Qualifier("firefox")
    @Bean
    public static WebDriver firefoxDriver() {
        if (driver == null) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            return driver;
        }
        return driver;
    }

}