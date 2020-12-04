package pages;

import Config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPageAuth extends AbstractPage {

    private static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private String url = cfg.url();
    private By btnLogReg = By.cssSelector("#new-log-reg");

    public MainPageAuth(WebDriver driver) { super(driver); }

    public MainPageAuth open() {
        driver.get(url);
        return this;
    }

    public BiographyPersonalPage openBiographyPersonalPage() {
        driver.findElement(btnLogReg).click();
        return new BiographyPersonalPage(driver);
    }
}
