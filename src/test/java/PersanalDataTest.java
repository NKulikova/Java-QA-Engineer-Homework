import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PersanalDataTest {

    protected static WebDriver driver;
    private static Logger logger = LogManager.getLogger(TitleTest.class);

    protected void auth() {
        String login = "f_19_natali@mail.ru";
        String password = "QWE123rty";
        driver.findElement(By.cssSelector("button.header2__auth")).click();
        driver.findElement(By.cssSelector("input[type=\"text\"]")).clear();
        driver.findElement(By.cssSelector("input[type=\"text\"]")).sendKeys(login);
        driver.findElement(By.cssSelector("input[type=\"password\"]")).clear();
        driver.findElement(By.cssSelector("input[type=\"password\"]")).sendKeys(password);
        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
    }

    protected void fillInfo() {
        driver.findElement(By.id("id_fname")).clear();
        driver.findElement(By.id("id_lname")).clear();
        driver.findElement(By.id("id_blog_name")).clear();
        driver.findElement(By.id("id_fname")).clear();
        driver.findElement(By.id("id_fname")).clear();
        driver.findElement(By.id("id_fname")).clear();
        driver.findElement(By.id("id_fname")).clear();

    }

    @BeforeAll
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер запущен.");
    }

    @Test
    public void persanalDataTest() {
        driver.get("https://otus.ru/");
        auth();
        driver.get("https://otus.ru/lk/biography/personal/");

    }

    @AfterAll
    public static void setDown() {
        if (driver != null) {
            driver.quit();
        }

        logger.info("Драйвер остановлен.");
    }
}
