/*
    основные аннатации @Execution(concurrent) - параллельно запускаются
        resourceLock - не запускать последовательные, пока не пройдут параллельные
        запуск хаба >java -jar selenium-server-standalone-3.141.59.jar -role hub
        запуск ноды >java -jar selenium-server-standalone-3.141.59.jar -role node -hub http://192.168.1.173:4444/grid/register/ -port 4448
*/

package cases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class HomeWork07Test {

    protected static RemoteWebDriver driver;
    private static Logger logger = LogManager.getLogger(HomeWork07Test.class);

    @BeforeAll
    public static void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platform", "WINDOWS");
        capabilities.setCapability("browser", "internet explorer");
        driver = new RemoteWebDriver(new URL("http://192.168.88.232:4444/wd/hub"), new InternetExplorerOptions());
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
        driver.get("https://otus.ru/");
        driver.findElement(By.cssSelector("button.header2__auth")).click();
    }

}