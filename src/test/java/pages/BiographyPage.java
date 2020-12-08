package pages;

import Config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;

public class BiographyPage extends AbstractPage {

    private static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private String url = cfg.url().concat("lk/biography/cv/");

    public BiographyPage(WebDriver driver) { super(driver); }

    public BiographyPage open() {
        driver.get(url);
        return this;
    }
}
