package pages;

import Config.ServerConfig;
import TestData.PersonalInfo;
import net.bytebuddy.asm.Advice;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
    private By contactForm = By.cssSelector("div[data-prefix=\"contact\"]");
    private By addContactButton = By.cssSelector("button");
    private By deleteContactButton = By.linkText("Удалить");
    private By selectContactType = By.linkText("Способ связи");
    private By enterContactNumber = By.cssSelector("input[type=\"text\"]");

    public BiographyPersonalPage(WebDriver driver) { super(driver); }

    public BiographyPersonalPage open() {
        driver.get(url);
        return this;
    }

    // заполняем форму значениями
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

    public BiographyPersonalPage addContactData(String type, String value) {
        WebElement form = driver.findElement(contactForm);
        WebElement contactButton = form.findElement(addContactButton);
        contactButton.click();
        WebElement contactType = form.findElement(selectContactType);
        contactType.click();
        form.findElement(By.linkText("type")).click();
        form.findElement(enterContactNumber).sendKeys(value);
        return this;
    }

    // сохраняем ввыеденные данные, и переходим в раздел со скиллами
    public BiographySkillsPage saveDataAndContinue() {
        driver.findElement(saveAndContinue).click();
        return new BiographySkillsPage(driver);
    }

    // сохраняем ввыеденные данные
    public BiographyPage saveData() {
        driver.findElement(saveAnfFillLater).click();
        return new BiographyPage(driver);
    }

    // считываем занчение полей на форме
    public PersonalInfo getPersonalData() {
        return new PersonalInfo(
                driver.findElement(fName).getAttribute("value"),
                driver.findElement(lName).getAttribute("value"),
                driver.findElement(fNameLat).getAttribute("value"),
                driver.findElement(lNameLat).getAttribute("value"),
                driver.findElement(bName).getAttribute("value"),
                driver.findElement(dob).getAttribute("value")
        );
    }
}
