package cases;

import Config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPageNotAuth;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SelenoidTest {

    private static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    static String browser = cfg.browser();
    protected static RemoteWebDriver driver;
    protected static WebDriverWait wait;
    private static Logger logger = LogManager.getLogger(SelenoidTest.class);

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
        capabilities.setCapability("platform", "Windows 10");
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("version","86.0");
        driver = new RemoteWebDriver(new URL("http://192.168.88.232:4444/wd/hub"), new FirefoxOptions());
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

/*https://github.com/aerokube/cm/releases/tag/1.7.2
        $ ./cm selenoid start --vnc
        $ ./cm selenoid-ui start

*/
