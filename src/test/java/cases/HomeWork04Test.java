package cases;

import Config.ServerConfig;
import Helpers.WebDriverFactory;
import TestData.PersonalInfo;
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

import java.util.concurrent.TimeUnit;

public class HomeWork04Test {

    private static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    static String browser = cfg.browser();
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    private static Logger logger = LogManager.getLogger(HomeWork04Test.class);

    // тут указываем какми данными будем заполнять поля на форме профиля
    // все данные заданы в файле TestData.PersonalInfo в виде хешкарты
//    private String profileName = "Afanaseva";
    private String profileName = "Kulikova";


    String login = cfg.login();
    String password = cfg.password();

    @BeforeAll
    public static void setUp() {
        ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
        String browser = cfg.browser();
        driver = new WebDriverFactory().createWebDriver(browser);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        wait = new WebDriverWait(driver, 10, 125);
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
    public void FillAndSavePersonalData() throws NoSuchElementException {
        MainPageNotAuth mainPage = new MainPageNotAuth(driver);
        mainPage
                .open()
                .openLoginPage()
                .enterLoginAndPassword(login, password)
                .openBiographyPersonalPage()
                .addPersonalData(profileName)
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
        PersonalInfo info = new BiographyPersonalPage(driver).getPersonalData();
        PersonalInfo infoToCompare = new PersonalInfo(profileName);
        // проверяем, что данные на форме соответствуют введённым
        Assertions.assertEquals(
                info.getProfileInfo("firstName"),
                infoToCompare.getProfileInfo("firstName"),
                "Фамилия не совпадает");
        Assertions.assertEquals(
                info.getProfileInfo("lastName"),
                infoToCompare.getProfileInfo("lastName"),
                "Имя не совпадает");
        Assertions.assertEquals(
                info.getProfileInfo("firstNameLatin"),
                infoToCompare.getProfileInfo("firstNameLatin"),
                "Фамилия (латиницей) не совпадает");
        Assertions.assertEquals(
                info.getProfileInfo("lastnameLatin"),
                infoToCompare.getProfileInfo("lastnameLatin"),
                "Имя (латиницей) не совпадает");
        Assertions.assertEquals(
                info.getProfileInfo("nickName"),
                infoToCompare.getProfileInfo("nickName"),
                "Никнейм не совпадает");
        Assertions.assertEquals(
                info.getProfileInfo("dayOfBirth"),
                infoToCompare.getProfileInfo("dayOfBirth"),
                "Дата рождения не совпадает");
    }

    @Test
    public void FillAndSaveContactData() throws NoSuchElementException {
        MainPageNotAuth mainPage = new MainPageNotAuth(driver);
        mainPage
                .open()
                .openLoginPage()
                .enterLoginAndPassword(login, password)
                .openBiographyPersonalPage()
                .addContactData("Skype", "my_skype_login")
                .addContactData("Viber", "my_viber_login")
                .saveData();
    }

    private WebElement waitElement(By by) {
        return wait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
    }
}