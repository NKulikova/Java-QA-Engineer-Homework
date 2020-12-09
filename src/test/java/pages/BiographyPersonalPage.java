package pages;

import Config.ServerConfig;
import TestData.PersonalInfo;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;

public class BiographyPersonalPage extends AbstractPage {

    private static ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private String url = cfg.url().concat("lk/biography/personal/");

    private By fName = By.id("id_fname");
    private By fNameLat = By.id("id_fname_latin");
    private By lName = By.id("id_lname");
    private By lNameLat = By.id("id_lname_latin");
    private By bName = By.id("id_blog_name");
    private By dob = By.cssSelector("input[name=\"date_of_birth\"]");
    private By saveAndContinue = By.cssSelector("button[title=\"Сохранить и продолжить\"]");
    private By saveAnfFillLater = By.cssSelector("button[title=\"Сохранить и заполнить позже\"]");
    private By addContactButton = By.cssSelector("div[data-prefix=\"contact\"] > button");
    private By contactItem = By.cssSelector("div[data-prefix=\"contact\"] > div > div:last-of-type");
    private By selectContactType = By.cssSelector("div.select");
    private By enterContactNumber = By.cssSelector("input[type=\"text\"]");

    public BiographyPersonalPage(WebDriver driver) { super(driver); }

    public BiographyPersonalPage open() {
        driver.get(url);
        return this;
    }

    // заполняем форму значениями
    public BiographyPersonalPage addPersonalData(PersonalInfo profile) {
        driver.findElement(fName).clear();
        driver.findElement(fName).sendKeys(profile.getProfileInfoByName("firstName"));
        driver.findElement(fNameLat).clear();
        driver.findElement(fNameLat).sendKeys(profile.getProfileInfoByName("firstNameLatin"));
        driver.findElement(lName).clear();
        driver.findElement(lName).sendKeys(profile.getProfileInfoByName("lastName"));
        driver.findElement(lNameLat).clear();
        driver.findElement(lNameLat).sendKeys(profile.getProfileInfoByName("lastnameLatin"));
        driver.findElement(bName).clear();
        driver.findElement(bName).sendKeys(profile.getProfileInfoByName("nickName"));
        driver.findElement(dob).clear();
        driver.findElement(dob).sendKeys(profile.getProfileInfoByName("dayOfBirth"));
        return this;
    }

    public BiographyPersonalPage addContactData(String type, String value) {
        WebElement contactButton = driver.findElement(addContactButton);
        contactButton.click();
        WebElement block = driver.findElement(contactItem);
        block.findElement(selectContactType).click();
        block.findElement(By.cssSelector("button[data-value=\"" + type.toLowerCase() + "\"]")).click();
        block.findElement(enterContactNumber).sendKeys(value);
        return this;
    }

    // сохраняем ввыеденные данные, и переходим в раздел со скиллами
    public BiographySkillsPage saveDataAndContinue() {
        driver.findElement(saveAndContinue).click();
        return new BiographySkillsPage(driver);
    }

    // сохраняем ввыеденные данные
    public BiographyCvPage saveData() {
        driver.findElement(saveAnfFillLater).click();
        return new BiographyCvPage(driver);
    }

    // считываем занчение полей на форме
    public HashMap getProfileData() {
        HashMap info = new HashMap<String, String>();
        info.put("firstName", driver.findElement(fName).getAttribute("value"));
        info.put("lastName", driver.findElement(lName).getAttribute("value"));
        info.put("firstNameLatin", driver.findElement(fNameLat).getAttribute("value"));
        info.put("lastnameLatin", driver.findElement(lNameLat).getAttribute("value"));
        info.put("nickName", driver.findElement(bName).getAttribute("value"));
        info.put("dayOfBirth", driver.findElement(dob).getAttribute("value"));
        return info;
    }

    public HashMap getContactData() {
        HashMap info = new HashMap<String, String>();
        List<WebElement> list = driver.findElements(By.cssSelector("div[data-prefix=\"contact\"] > div > div"));
        list.forEach(e -> {
            info.put(
                    e.findElement(By.cssSelector("label > input")).getAttribute("value"),
                    e.findElement(enterContactNumber).getAttribute("value"));
        });
        return info;
    }
}
