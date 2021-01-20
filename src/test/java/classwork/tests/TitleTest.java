package classwork.tests;

import Config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import Helpers.WebDriverFactory;

public class TitleTest {

    private static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    static String browserName = cfg.browser();
    protected static WebDriver driver;
    private static Logger logger = LogManager.getLogger(TitleTest.class);

    public TitleTest() {
    }

    @BeforeAll
    public static void setUp() {
        driver = new WebDriverFactory().createWebDriver(browserName);
        logger.info("Драйвер запущен.");
    }

    @Test
    public void checkTitle() {
        String expectedTitle = "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям";
        driver.get(cfg.url());
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