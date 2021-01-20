package classwork.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class WaitTest {
    protected static WebDriver driver;
    private static Logger logger = LogManager.getLogger(TitleTest.class);
    String button = "//button[contains(text(),'Change message')]";
    String url = "https://ng-bootstrap.github.io/#/components/alert/examples";

    private String getAlertTest(WebElement element) {
        String alert = "//ngb-alert[contains(text(), 'Message successfully changed')]";
        element.click();
        WebElement alertBox = driver.findElement(By.xpath(alert));
        new WebDriverWait(driver, 4).until(visibilityOf(alertBox));
        return alertBox.getText();
    }

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер запущен.");
    }

    @Test
    public void checkTitle() throws InterruptedException {
        String textBefore;
        String textAfter;
        String expectedTitle = "Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям";
        driver.get(url);
        WebElement element = driver.findElement(By.xpath(button));
        textBefore = getAlertTest(element);
        logger.info(String.format("Alert text %s", textBefore));
        Thread.sleep(1500);
        logger.info("Wait for 1.5 seconds");
        textAfter = getAlertTest(element);
        logger.info(String.format("Alert text %s", textAfter));
        Assertions.assertNotEquals(textBefore, textAfter);
    }

    @AfterAll
    public static void setDown() {
        if (driver != null) {
            driver.quit();
        }

        logger.info("Драйвер остановлен.");
    }
}