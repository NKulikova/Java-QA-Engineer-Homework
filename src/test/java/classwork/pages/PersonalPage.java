package classwork.pages;

import Config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalPage extends AbstractPage {

    private static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private String url = cfg.url().concat("learning/");

    private By biography = By.linkText("О себе");

    public PersonalPage(WebDriver driver) { super(driver); }

    public PersonalPage open() {
        driver.get(url);
        return this;
    }

    public BiographyPersonalPage enterBiographyPersonal(){
        driver.findElement(biography).click();
        return new BiographyPersonalPage(driver);
    }
}