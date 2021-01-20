package classwork.config;

import Helpers.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import classwork.pages.LoginPage;
import classwork.pages.MainPageAuth;
import classwork.pages.MainPageNotAuth;

@Configuration
@ComponentScan(basePackages = "classwork/config")
public class SpringConfig {

    private static Logger logger = LogManager.getLogger(SpringConfig.class);

    @Bean
    WebDriver driver(){
        logger.info("Драйвер запущен");
        return new WebDriverFactory().createWebDriver("firefox"); // можно переписать Browsers.value(v).create()
    }

    @Bean
    MainPageNotAuth mainPageNotAuth() {
        return new MainPageNotAuth(driver());
    }

    @Bean
    LoginPage loginPage() {
        return new LoginPage(driver());
    }

    @Bean
    MainPageAuth mainPageAuth() {
        return new MainPageAuth(driver());
    }
}
