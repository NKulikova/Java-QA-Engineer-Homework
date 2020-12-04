package pages;

import Config.ServerConfig;
import TestData.PersonalInfo;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BiographyPersonalPage extends AbstractPage {

    private static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private String url = cfg.url().concat("lk/biography/personal/");

    private By fName = By.className("#id_fname");
    private By fNameLat = By.className("#id_fname_latin");
    private By lName = By.className("#id_lname");
    private By lNameLat = By.className("#id_lname_latin");
    private By bName = By.className("#id_blog_name");
    private By dob = By.className("input[name=\"date_of_birth\"]");
    private By addContact = By.cssSelector("div[data-prefix=\"contact\"] > button");
    private By save = By.linkText("Сохранить и продолжить");

    public BiographyPersonalPage(WebDriver driver) { super(driver); }

    public BiographyPersonalPage open() {
        driver.get(url);
        return this;
    }

    public BiographyPersonalPage addContact(){
        driver.findElement(addContact).click();
        return this;
    }

    public BiographyPersonalPage addPersonalData(String name) {
        PersonalInfo info = new PersonalInfo(name);
        driver.findElement(fName).clear();
        driver.findElement(fName).sendKeys(info.getProfileInfo("firstName"));
        driver.findElement(fNameLat).clear();
        driver.findElement(fNameLat).sendKeys(info.getProfileInfo("firstNameLatin"));
        driver.findElement(lName).clear();
        driver.findElement(lName).sendKeys(info.getProfileInfo("lastName"));
        driver.findElement(lNameLat).clear();
        driver.findElement(lNameLat).sendKeys(info.getProfileInfo("lastnameLatin"));
        driver.findElement(bName).clear();
        driver.findElement(bName).sendKeys(info.getProfileInfo("nickName"));
        driver.findElement(dob).clear();
        driver.findElement(dob).sendKeys(info.getProfileInfo("dayOfBirth"));
        return this;
    }

    public void saveData() {
        driver.findElement(save).click();
    }

    public boolean checkPersonalData(String name) {
        PersonalInfo info =
                new PersonalInfo(
                        driver.findElement(fName).getAttribute("value"),
                        driver.findElement(fNameLat).getAttribute("value"),
                        driver.findElement(lName).getAttribute("value"),
                        driver.findElement(lNameLat).getAttribute("value"),
                        driver.findElement(bName).getAttribute("value"),
                        driver.findElement(dob).getAttribute("value")
                );
        return info.equals(new PersonalInfo(name));
    }
}
