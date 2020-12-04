package pages;

import Config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends AbstractPage {

    private static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private String url = cfg.url().concat("login/");

    private By inputLogin = By.cssSelector("input[name=\"email\"]");
    private By inputPassword = By.cssSelector("input[name=\"password\"]");
    private By submit = By.linkText("Войти");

    public LoginPage(WebDriver driver) { super(driver); }

    public LoginPage open() {
        driver.get(url);
        return this;
    }

    public MainPageAuth enterLoginAndPassword(String login, String password){
        driver.findElement(inputLogin).sendKeys(login);
        driver.findElement(inputPassword).sendKeys(password);
        driver.findElement(submit).click();
        return new MainPageAuth(driver);
    }
}
