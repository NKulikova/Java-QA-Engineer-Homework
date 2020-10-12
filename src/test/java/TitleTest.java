import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TitleTest {
    protected static WebDriver driver;
    private Logger logger = LogManager.getLogger(TitleTest.class);

    public TitleTest() {
    }

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        this.logger.info("Драйвер запущен.");
    }

    @Test
    public void checkTitle() {
        String expectedTitle = "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям";
        driver.get("https://otus.ru/");
        Assertions.assertEquals(expectedTitle, driver.getTitle());
        this.logger.info("Заголовок страницы корректный");
    }

    @AfterEach
    public void setDown() {
        if (driver != null) {
            driver.quit();
        }

        this.logger.info("Драйвер остановлен.");
    }
}