import Config.ServerConfig;
import Helpers.WebDriverFactory;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class HomeWork03Test {

    private static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    static String browserName = cfg.browser();
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    private static Logger logger = LogManager.getLogger(HomeWork03Test.class);
    private String yandexMarketUrl = cfg.url();

    //локаторы
    private String filter = "fieldset[data-autotest-id=\"7893318\"]";
    private String checkboxSamsung = "li:nth-child(9) > div > a > label > div";
    private String checkboxXiaomi = "li:nth-child(11) > div > a > label > div";
    private String priceSort = "button[data-autotest-id=\"dprice\"]";
    private String searchResults = "div[data-zone-name=\"SearchResults\"]";
    private String articles = "article";
    private String addToCompare = "div:nth-child(2) > div:nth-child(2)";
    private String addToCompareButton = "//article/div[4]/div[1]/h3/a/span[text()[contains(.,'%s')]]/ancestor::article/div[2]/div[2]";

    @BeforeAll
    public static void setUp() {
        driver = new WebDriverFactory().createWebDriver(browserName);
        wait = new WebDriverWait(driver, 10, 125);
        logger.info("Драйвер запущен");
    }

    @AfterAll
    public static void setDown() {
        if (driver != null) {
            driver.quit();
        }
        logger.info("Драйвер остановлен");
    }

    @Test
    public void yandexMarketTest() throws NoSuchElementException, InterruptedException {
        driver.get(yandexMarketUrl);
        logger.info("Открываем Яндекс.Маркет");
        waitElement(By.xpath("//button/span[text()[contains(.,'Понятно')]]")).click();

//        Тут поставила так, потому что для ExpectedConditions.visibilityOf не срабатывает
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText("Электроника")))).click();
        logger.info("Переход в раздел \"Эелектроника\"");
        waitElement(By.linkText("Смартфоны")).click();
        logger.info("Переход в раздел \"Смартфоны\"");

        // нахожу панель с фильтрами, чтобы на ней выбрать производителей
        WebElement filterCompany = waitElement(By.cssSelector(filter));
        logger.info("Фильт по производителям отображается");
        filterCompany.findElement(By.cssSelector(checkboxSamsung)).click();  // не смогла подобрать адекватный селектор, прошу помочь разобраться, как его получить
        logger.info("Установлен фильтр \"Samsung\"");
        filterCompany.findElement(By.cssSelector(checkboxXiaomi)).click();
        logger.info("Установлен фильтр \"Xiaomi\"");

        // сортировка по цене
        driver.findElement(By.cssSelector(priceSort)).click();
        logger.info("Отсортировали по цене по убыванию");

        // добавление в сравнение
        waitElement(By.xpath("//button/span[text()[contains(.,'Понятно')]]")).click();

//        Тут поставила так, потому что для ExpectedConditions.visibilityOf не срабатывает
        Thread.sleep(1000);
        driver.findElement(By.xpath(String.format(addToCompareButton, "Samsung"))).click();
        driver.findElement(By.xpath(String.format(addToCompareButton, "Xiaomi"))).click();

        WebElement compare = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText("Сравнить"))));
        compare.click();
        logger.info("Открываем сравнение");
        // проверить, что в сравнеии два элемента
        List<WebElement> compareItems = driver.findElements(By.cssSelector("img ~ a"));
        Assertions.assertEquals(compareItems.size(), 2);
    }

    private WebElement waitElement(By by) {
        return wait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
    }
}