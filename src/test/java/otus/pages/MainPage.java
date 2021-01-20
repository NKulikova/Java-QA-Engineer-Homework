package otus.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MainPage extends AbstractPage {

    @FindBy(partialLinkText = "Больше курсов")
    public WebElement btnAllCourses;
    @FindBy(css = "button[data-modal-id=\"new-log-reg\"]")
    public WebElement btnLogReg;

    @Autowired
    @Qualifier(value = "firefox")
    private WebDriver driver;

    public MainPage(WebDriver driver) { super(driver); }

    public void open(String url) {
        driver.get(url);
    }

    public void openAllCourses() {
        btnAllCourses.click();
    }

    public void openLoginPage() {
        btnLogReg.click();
    }
}
