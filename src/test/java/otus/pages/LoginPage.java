package otus.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

@ComponentScan
public class LoginPage {

    @Autowired
    @Qualifier(value = "firefox")
    private WebDriver driver;

    @FindBy(css = ".js-login input[name=\"email\"]")
    WebElement inputLogin;
    @FindBy(css = ".js-login input[name=\"password\"]")
    WebElement inputPassword;
    @FindBy(css = ".js-login button[type=\"submit\"]")
    WebElement submit;
    @FindBy(css = ".new-input-error.new-input-error_top.new-input-error_form")
    WebElement errorMessage;

    @PostConstruct
    public void init() { PageFactory.initElements(driver,this);}

    public void enterLogin(String login) {
        inputLogin.sendKeys(login);
    }

    public void enterPassword(String password) { inputPassword.sendKeys(password);}

    public void clickSubmit() { submit.click(); }

    public String getErrorMessage() {
        return errorMessage.getText();
    }

}
