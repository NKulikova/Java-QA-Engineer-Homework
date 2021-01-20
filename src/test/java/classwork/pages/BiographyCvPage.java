package classwork.pages;

import Config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;

public class BiographyCvPage extends AbstractPage {

    private static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private String url = cfg.url().concat("lk/biography/cv/");

    public BiographyCvPage(WebDriver driver) { super(driver); }

    public BiographyCvPage open() {
        driver.get(url);
        return this;
    }
}
