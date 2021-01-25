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

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomeWork04Test {

    private static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    static String browser = cfg.browser();
    protected static WebDriver driver;
    private static Logger logger = LogManager.getLogger(HomeWork04Test.class);

    // тут указываем какми данными будем заполнять поля на форме профиля
    // все данные заданы в файле TestData.PersonalInfo в виде хешкарты
    private String profileName = "Afanaseva";
//    private String profileName = "Kulikova";


    String login = cfg.login();
    String password = cfg.password();

    @BeforeAll
    public static void setUp() {
        driver = new WebDriverFactory().createWebDriver(browser);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
        PersonalInfo profile = new PersonalInfo(profileName);
        MainPageNotAuth mainPage = new MainPageNotAuth(driver);
        mainPage
                .open()
                .openLoginPage()
                .enterLoginAndPassword(login, password)
                .openBiographyPersonalPage()
                .addPersonalData(profile)
                .saveData();
    }

    @Test
    public void FillAndSaveContactData() throws NoSuchElementException {
        PersonalInfo profile = new PersonalInfo(profileName);
        MainPageNotAuth mainPage = new MainPageNotAuth(driver);
        mainPage
                .open()
                .openLoginPage()
                .enterLoginAndPassword(login, password)
                .openBiographyPersonalPage()
                .addContactData("Skype", "my_skype_number")
                .addContactData("Viber", "my_viber_number")
                .saveData();
    }
}