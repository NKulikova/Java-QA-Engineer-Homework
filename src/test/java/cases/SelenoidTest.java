package cases;

import Config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.MainPageNotAuth;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Execution(ExecutionMode.CONCURRENT)
public class SelenoidTest {

    private static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private static Logger logger = LogManager.getLogger(SelenoidTest.class);
    private static final String LOGIN = cfg.login();
    private static final String PASSWORD = cfg.password();
    protected WebDriver driver;

    @BeforeEach
    public void initDriver() throws MalformedURLException {
        String port = System.getProperty("port");
        String slenoidURL = "http://192.168.88.232:" + port + "/wd/hub/";
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName(System.getProperty("browser_name", "chrome"));
        caps.setVersion(System.getProperty("browser_version", "88.0"));
        caps.setCapability("enableVNC", true);
        caps.setCapability("screenResolution", "1280x1024");
        caps.setCapability("enableVideo", true);
        caps.setCapability("enableLog", true);

        driver = new RemoteWebDriver(new URL(slenoidURL), caps);

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @AfterEach
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void open() {
        MainPageNotAuth mainPage = new MainPageNotAuth(driver);
        mainPage
                .open()
                .openLoginPage()
                .enterLoginAndPassword(LOGIN, PASSWORD)
                .openBiographyPersonalPage();
    }

    @Test
    public void openMainPage() {
        MainPageNotAuth mainPage = new MainPageNotAuth(driver);
        mainPage.open();
    }

}