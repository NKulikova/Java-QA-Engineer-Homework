import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TitleTest {
    protected static WebDriver driver;
    private static Logger logger = LogManager.getLogger(TitleTest.class);

    public TitleTest() {
    }

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
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