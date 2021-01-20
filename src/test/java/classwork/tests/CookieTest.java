package classwork.tests;//*добавить куки авторизации на Otus.ru (без использования getCookies)

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CookieTest {

    protected static WebDriver driver;
    private static Logger logger = LogManager.getLogger(TitleTest.class);

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер запущен.");
    }

    @Test
    public void cookieTest() {
        driver.get("https://otus.ru/");
        Cookie myCookie = new Cookie("Otus1", "Value1");
        driver.manage().addCookie(myCookie);
        driver.manage().addCookie(new Cookie("Otus2", "Value2"));
        driver.manage().addCookie(new Cookie("Otus3", "Value3"));
        driver.manage().addCookie(new Cookie("Otus4", "Value4"));
        System.out.println(driver.manage().getCookies());
        System.out.println(driver.manage().getCookieNamed("Otus1"));
        driver.manage().deleteCookieNamed("Otus2");
        driver.manage().deleteCookie(myCookie);
        driver.manage().deleteAllCookies();
        assert driver.manage().getCookies().isEmpty();
    }

    @AfterAll
    public static void setDown() {
        if (driver != null) {
            driver.quit();
        }

        logger.info("Драйвер остановлен.");
    }

}
