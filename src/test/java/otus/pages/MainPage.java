package otus.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class MainPage {

    @Autowired
    @Qualifier(value = "firefox")
    private WebDriver driver;

//    @Autowired
//    LoginPage loginPage;

    @FindBy(partialLinkText = "Больше курсов")
    WebElement btnMoreCourses;
    @FindBy(css = "button[data-modal-id=\"new-log-reg\"]")
    WebElement btnLogReg;
    @FindBy(className = "cookies__button")
    WebElement btnCookies;
    @FindBy(className = "sticky-banner__close")
    WebElement btnCloseStickyBanner;
    @FindBy(css = "button.header2__auth")
    WebElement btnLoginAndRegistration;

    @PostConstruct
    public void init() { PageFactory.initElements(driver,this);}

    public void open(String url) {
        driver.get(url);
    }

    public void openMoreCourses() {
        btnMoreCourses.click();
    }

    public void closeCookiesPopup() { if (btnCookies != null && btnCookies.isEnabled()) btnCookies.click(); }

    public void closeBanner() { if (btnCloseStickyBanner != null) btnCloseStickyBanner.click(); }

    public void openLoginForm() {
        btnLoginAndRegistration.click();
//        return loginPage;
    }
}
