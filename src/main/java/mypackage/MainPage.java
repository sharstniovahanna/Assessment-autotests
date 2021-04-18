package mypackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    public final By ENTER_BUTTON = By.xpath("//a[@class=\'enter\']");
    public final By REGISTRATION_BUTTON = By.xpath("//a[@class='button wide auth__reg']");
    public final By ENTER_SUBMISSION_BUTTON = By.xpath("//div[@class='b-auth-form__inner']//div[2]/input");
    public final By FORGET_PASSWORD_LINK = By.xpath("//*[contains(text(),'Забыли пароль')]");
    public final By LOGIN_FIELD = By.xpath("//*[@autocomplete='username']");
    public final By PASSWORD_FIELD = By.xpath("//*[@autocomplete='current-password']");
    public final By LOGIN_SUBMISSION_BUTTON = By.xpath("//input[@type='submit'  and @tabindex='6']");
    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickEnter() {
        driver.findElement(ENTER_BUTTON).click();
    }

    public RegistrationPage registrateUser() {
        driver.findElement(REGISTRATION_BUTTON).click();
        return new RegistrationPage(driver);
    }

    public boolean isEnterSubmissionButtonDisabled() {
        return driver.findElement(ENTER_SUBMISSION_BUTTON).isEnabled();
    }

    public PasswordRecoveryPage recoveryPassword() {
        driver.findElement(ENTER_BUTTON).click();
        driver.findElement(FORGET_PASSWORD_LINK).click();
        return new PasswordRecoveryPage(driver);
    }

    public PersonalPage login(String username, String password) {
        driver.findElement(LOGIN_FIELD).sendKeys(username);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_SUBMISSION_BUTTON).click();
        return new PersonalPage(driver);
    }


}
