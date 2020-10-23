import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import Helpers.WebDriverFactory;
import org.openqa.selenium.firefox.FirefoxOptions;

public class TitleTest {

    static String browserName = System.getProperty("browser").toUpperCase();
    protected static WebDriver driver;
    private static Logger logger = LogManager.getLogger(TitleTest.class);

    public TitleTest() {
    }

    @BeforeAll
    public static void setUp() {
        FirefoxOptions options = new FirefoxOptions();
        driver = new WebDriverFactory().createWebDriver(WebDriverFactory.Browser.valueOf(browserName.toUpperCase()), options);
        logger.info("Драйвер запущен.");
    }

    @Test
    public void checkTitle() {
        String expectedTitle = "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям";
        driver.get("https://otus.ru/");
        Assertions.assertEquals(expectedTitle, driver.getTitle());
        this.logger.info("Заголовок страницы корректный");
    }

    @AfterAll
    public static void setDown() {
        if (driver != null) {
            driver.quit();
        }

        logger.info("Драйвер остановлен.");
    }
}