package cases;

import Config.ServerConfig;
import Helpers.WebDriverFactory;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BiographyPersonalPage;
import pages.MainPageNotAuth;

public class HomeWork04Test {

    private static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    static String BROWSER = cfg.browser();
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    private static Logger logger = LogManager.getLogger(HomeWork04Test.class);

    String login = cfg.login();
    String password = cfg.password();

    @BeforeEach
    public static void setUp() {
        driver = new WebDriverFactory().createWebDriver(BROWSER);
        wait = new WebDriverWait(driver, 10, 125);
        logger.info("Драйвер запущен");
    }

    @AfterEach
    public static void setDown() {
        if (driver != null) {
            driver.quit();
        }
        logger.info("Драйвер остановлен");
    }

    @Test
    public void FillAndSavePersonalData() throws NoSuchElementException, InterruptedException {
        MainPageNotAuth mainPage = new MainPageNotAuth(driver);
        mainPage
                .open()
                .openLoginPage()
                .enterLoginAndPassword(login, password)
                .openBiographyPersonalPage()
                .addPersonalData("Afanaseva")
                .saveData();
    }

    @Test
    public void checkPersonalData() {
        MainPageNotAuth mainPage = new MainPageNotAuth(driver);
        mainPage
                .open()
                .openLoginPage()
                .enterLoginAndPassword(login, password)
                .openBiographyPersonalPage();
        Assertions.assertTrue(new BiographyPersonalPage(driver).checkPersonalData("Afanaseva"), "Введённые данные сохранены");
    }

    private WebElement waitElement(By by) {
        return wait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
    }
}