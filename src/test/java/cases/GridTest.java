package cases;

import Config.ServerConfig;
import TestData.PersonalInfo;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BiographyPersonalPage;
import pages.MainPageNotAuth;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class GridTest {

    private static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    static String browser = cfg.browser();
    protected static RemoteWebDriver driver;
    protected static WebDriverWait wait;
    private static Logger logger = LogManager.getLogger(GridTest.class);

    // тут указываем какми данными будем заполнять поля на форме профиля
    // все данные заданы в файле TestData.PersonalInfo в виде хешкарты
    private String profileName = "Afanaseva";
//    private String profileName = "Kulikova";


    String login = cfg.login();
    String password = cfg.password();

    @BeforeAll
    public static void setUp() throws MalformedURLException {
        ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("build", "your build name");
//        capabilities.setCapability("name", "your test name");
//        capabilities.setCapability("platform", "Windows 10");
//        capabilities.setCapability("browserName", "Chrome");
//        capabilities.setCapability("version","86.0");
        driver = new RemoteWebDriver(new URL("http://192.168.88.232:5555/wd/hub"), new FirefoxOptions());
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
    public void open() {
        MainPageNotAuth mainPage = new MainPageNotAuth(driver);
        mainPage
                .open()
                .openLoginPage()
                .enterLoginAndPassword(login, password)
                .openBiographyPersonalPage();
    }

}

//    основные аннатации @Execution(concurrent) - параллельно запускаются
//        resourceLock - не запускать последовательные, пока не пройдут параллельные
//        запуск хаба >java -jar selenium-server-standalone-3.141.59.jar -role hub
//        запуск ноды >java -jar selenium-server-standalone-3.141.59.jar -role node -hub http://192.168.1.173:4444/grid/register/ -port 4448