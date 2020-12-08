package pages;

import Config.ServerConfig;
import TestData.PersonalInfo;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BiographySkillsPage extends AbstractPage {

    private static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private String url = cfg.url().concat("lk/biography/skills/");

    public BiographySkillsPage(WebDriver driver) { super(driver); }

    public BiographySkillsPage open() {
        driver.get(url);
        return this;
    }
}
