package cases;

import Config.ServerConfig;
import Helpers.WebDriverFactory;
import io.qameta.allure.*;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Epic("Тестирование Otus")
public class HomeWork08Test {

    private static final ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    static String browserName = cfg.browser();
    protected static WebDriver driver;
    protected static WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(HomeWork08Test.class);
    private static final String URL = cfg.url();

    @BeforeEach
    public static void setUp() throws MalformedURLException {
//        driver = new WebDriverFactory().createWebDriver(browserName);
//        wait = new WebDriverWait(driver, 10, 125);
//        logger.info("Драйвер запущен");
        String slenoidURL = "http://192.168.88.232:4444/wd/hub/";
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName(System.getProperty("browser_name", "firefox"));
        caps.setVersion(System.getProperty("browser_version", "84.0"));
        caps.setCapability("enableVNC", true);
        caps.setCapability("screenResolution", "1280x1024");
        caps.setCapability("enableVideo", false);
        caps.setCapability("enableLog", true);

        driver = new RemoteWebDriver(new URL(slenoidURL), caps);
        logger.info("Драйвер запущен");

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @AfterEach
    public static void setDown() {
        if (driver != null) {
            driver.quit();
        }
        logger.info("Драйвер остановлен");
    }

    @Test
    @Story(value = "Открытие формы авторизации")
    @Description(value = "Открытие формы авторизации")
    void openLoginPage() {
        openMainPage();
        loginButton();
        Allure.addAttachment("LoginPageForm", new ByteArrayInputStream(((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES)));
    }

    @Test
    @Story(value = "Открытие списка курсов")
    void openJavaDevCourse() {
        openMainPage();
        openCourse("Java Developer. Professional");
        Allure.addAttachment("JavaDevCoursePage", new ByteArrayInputStream(((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES)));
    }

    @Test
    @Story(value = "Открытие списка курсов")
    void openCSharpCourse() {
        openMainPage();
        openCourse("Разработчик C#");
        Allure.addAttachment("OneCDevCoursePage", new ByteArrayInputStream(((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES)));
    }

    @Step(value = "Открытие главной страницы")
    void openMainPage() {
        driver.get(URL);
        driver.manage().window().maximize();
        logger.info("Открыта страница Otus");
    }

    @Step(value = "Нажатие кнопки \"Вход и регистрация\"")
    void loginButton() {
        driver.findElement(By.cssSelector("button.header2__auth")).click();
        Assertions.assertTrue(driver.findElement(By.cssSelector("div.new-log-reg-container")).isDisplayed());
        logger.info("Открыта форма ввода логина и пароля");
    }

    @Feature(value = "Открытие списка курсов")
    void openCourse(String course) {
        driver.findElement(By.cssSelector("a[title=\"Больше курсов\"]")).click();
        logger.info("Открыт список всех курсов");
        driver.findElement(By.partialLinkText(course)).click();
        Assertions.assertEquals(course, driver.findElement(By.cssSelector(".course-header2__title")).getText());
        logger.info("Курс \"{}\" открыт", course);
    }

}