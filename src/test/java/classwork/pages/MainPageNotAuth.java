package classwork.pages;

import Config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPageNotAuth extends AbstractPage {

    private static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private String url = cfg.url();
    private By btnLogReg = By.cssSelector("button[data-modal-id=\"new-log-reg\"]");

    public MainPageNotAuth(WebDriver driver) { super(driver); }

    public MainPageNotAuth open() {
        driver.get(url);
        return this;
    }

    public LoginPage openLoginPage() {
        driver.findElement(btnLogReg).click();
        return new LoginPage(driver);
    }
}
