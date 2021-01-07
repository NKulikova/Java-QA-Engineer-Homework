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
    private String checkboxSamsung = "li:nth-child(8) > div > a > label > div";
    private String checkboxXiaomi = "li:nth-child(11) > div > a > label > div";
    private String priceSort = "button[data-autotest-id=\"dprice\"]";
    private String searchResults = "div[data-zone-name=\"SearchResults\"]";
    private String articles = "article";
    private String addToCompare = "div:nth-child(2) > div:nth-child(2)";

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
        WebElement filterCompany = waitElement(By.cssSelector(filter));
        logger.info("Фильт по производителям отображается");
        filterCompany.findElement(By.cssSelector(checkboxSamsung)).click();  // не смогла подобрать адекватный селектор, прошу помочь разобраться, как его получить
        logger.info("Установлен фильтр \"Samsung\"");
        filterCompany.findElement(By.cssSelector(checkboxXiaomi)).click();
        logger.info("Установлен фильтр \"Xiaomi\"");

        // сортировка по цене
        driver.findElement(By.cssSelector(priceSort)).click();
        logger.info("Отсортировали по цене по убыванию");

        // нахожу панель результатов поиска, чтобы в ней выбрать модель для сравнени
//        Thread.sleep(3000);
        WebElement searchResult = waitElement(By.cssSelector(searchResults));
        List<WebElement> searchResultsList = searchResult.findElements(By.cssSelector(articles));
        List<WebElement> searchResultsSamsung = getProductList(searchResultsList, "Samsung");
        List<WebElement> searchResultsXiaomi = getProductList(searchResultsList, "Xiaomi");

        if (searchResultsSamsung.size() != 0 && searchResultsXiaomi.size() != 0) {
            WebElement samsungItem = searchResultsSamsung.get(0);
            WebElement xiaomiItem = searchResultsXiaomi.get(0);
            logger.info("Выбраны самые дешёвые Samsung и Xiaomi");

            // На следующем шаге тест падает с ошибкой:
            // org.openqa.selenium.StaleElementReferenceException: The element reference of <article class="_1_IxNTwqll cia-vs cia-cs"> is stale;
            // either the element is no longer attached to the DOM, it is not in the current frame context, or the document has been refreshed
            // Но страница не обновлялась, элементы не менялись.
            samsungItem.findElement(By.cssSelector(addToCompare));
            logger.info("Дбавляем к сравнению Samsung");
            xiaomiItem.findElement(By.cssSelector(addToCompare));
            logger.info("Дбавляем к сравнению Xiaomi");
            WebElement compare = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText("Сравнить"))));
            compare.click();
            logger.info("Открываем сравнение");
            // проверить, что в сравнеии два элемента
            List<WebElement> compareItems = driver.findElements(By.cssSelector("img ~ a"));
            Assertions.assertEquals(compareItems.size(), 2);
        }
        else logger.info("Нет результатов для сравнения");
    }

    private WebElement waitElement(By by) {
        return wait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
    }

    private List<WebElement> waitElements(By by) {
        return wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(by)));
    }

    private List<WebElement> getProductList(List<WebElement> commonList, String name) {
        List<WebElement> resultList = new ArrayList<WebElement>();
        if (commonList.size() != 0) {
            for (int i = 0; resultList.size() < 1; i++) {
                if (commonList.get(i).findElement(By.cssSelector("h3 > a > span")).getText().contains(name)) {
                    resultList.add(commonList.get(i));
                    continue;
                }
            }
        }
        return resultList;
    }
}