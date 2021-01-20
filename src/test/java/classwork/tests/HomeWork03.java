package classwork.tests;

import Config.ServerConfig;
import Helpers.WebDriverFactory;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class HomeWork03 {

    private static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    static String browserName = cfg.browser();
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    private static Logger logger = LogManager.getLogger(HomeWork03.class);
    String yandexMarketUrl ="https://market.yandex.ru/";

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
        waitElement(By.linkText("Электроника")).click();
        logger.info("Переход в раздел \"Эелектроника\"");
        waitElement(By.linkText("Смартфоны")).click();
        logger.info("Переход в раздел \"Смартфоны\"");

        // нахожу панель с фильтрами, чтобы на ней выбрать производителей
        WebElement filterCompany = waitElement(By.cssSelector("fieldset[data-autotest-id=\"7893318\"]"));
        logger.info("Фильт по производителям отображается");
        filterCompany.findElement(By.cssSelector("li:nth-child(9) > div > a > label > div")).click();  // не смогла подобрать адекватный селектор, прошу помочь разобраться, как его получить
        logger.info("Установлен фильтр \"Samsung\"");
        filterCompany.findElement(By.cssSelector("li:nth-child(11) > div > a > label > div")).click();
        logger.info("Установлен фильтр \"Xiaomi\"");

        // сортировка по цене
        driver.findElement(By.cssSelector("button[data-autotest-id=\"dprice\"]")).click();
        logger.info("Отсортировали по цене по убыванию");

        // нахожу панель результатов поиска, чтобы в ней выбрать модель для сравнени
//        Thread.sleep(3000);
        List<WebElement> searchResultsList = waitElements(By.cssSelector("article"));
        List<WebElement> searchResultsSamsung = getProductList(searchResultsList, "Samsung");
        List<WebElement> searchResultsXiaomi = getProductList(searchResultsList, "Xiaomi");

        if (searchResultsSamsung.size() != 0 && searchResultsXiaomi.size() != 0) {
            WebElement samsungItem = searchResultsSamsung.get(0);
            WebElement xiaomiItem = searchResultsXiaomi.get(0);
            logger.info("Выбраны самые дешёвые Samsung и Xiaomi");
            samsungItem.findElement(By.cssSelector("div > div")).click();
            logger.info("Дбавляем к сравнению Samsung");
            xiaomiItem.findElement(By.cssSelector("div > div")).click();
            logger.info("Дбавляем к сравнению Xiaomi");
            WebElement popupInformer = waitElement(By.cssSelector("div[data-apiary-widget-name=\"@market/PopupInformer\"]"));
            popupInformer.findElement(By.linkText("Сравнить")).click();
            logger.info("Открываем сравнение");
        }
    }

    private WebElement waitElement(By by) {
        return wait.until(driver -> driver.findElement(by));
    }
    private List<WebElement> waitElements(By by) {
        return wait.until(driver -> driver.findElements(by));
    }
    private List<WebElement> getProductList(List<WebElement> commonList, String name) {
        List<WebElement> resultList = new ArrayList<WebElement>();
        if (commonList.size() != 0) {
            for (int i = 0; i < commonList.size(); i++) {
                if (commonList.get(i).findElement(By.cssSelector("h3 > a > span")).getText().contains(name)) {
                    resultList.add(commonList.get(i));
                    continue;
                }
            }
        }
        return resultList;
    }
}