import mypackage.MainPage;
import mypackage.PasswordRecoveryPage;
import mypackage.PersonalPage;
import mypackage.RegistrationPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static utills.PropertyManager.getProperty;

public class MainPageTests {
    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver_");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.tut.by/");
        mainPage = new MainPage(driver);
    }
    @Test
    public void positiveLoginTestWithEmptyLoginAndPass() {
        mainPage = new MainPage(driver);
        mainPage.clickEnter();
        Boolean result = mainPage.isEnterSubmissionButtonDisabled();
        System.out.println(result);
        Assert.assertFalse(result);
    }

    @Test
    public void positiveRecoveryPasswordTest(){
        mainPage = new MainPage(driver);
        mainPage.recoveryPassword();
        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);
        String heading1 = passwordRecoveryPage.getHeadingOnRecoveryPage();
        System.out.println(heading1);
        Assert.assertEquals("Восстановление пароля",heading1);
    }


    @Test
    public void positiveRegistrationTest() {
        mainPage = new MainPage(driver);
        mainPage.clickEnter();
        mainPage.registrateUser();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registrationSubmission(getProperty("correct_email"), getProperty("correct_password"), getProperty("correct_phone"), getProperty("correct_name_surname"));
        String heading = registrationPage.getHeadinText();
        System.out.println(heading);
        Assert.assertEquals("Подтверждение номера", heading);
    }

    @Test
    public void negativeTestIncorrectPhone() {
        mainPage = new MainPage(driver);
        mainPage.clickEnter();
        mainPage.registrateUser();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registrationSubmission(getProperty("correct_email"), getProperty("correct_password"), getProperty("incorrect_phone"), getProperty("correct_name_surname"));
        String errorMessPhone = registrationPage.getErrorMessIncorrectPhone();
        System.out.println(errorMessPhone);
        Assert.assertEquals("Неправильный телефон", errorMessPhone);
    }

    @Test
    public void negativeTestIncorrectNameAndSurname() {
        mainPage = new MainPage(driver);
        mainPage.clickEnter();
        mainPage.registrateUser();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registrationSubmission(getProperty("correct_email"), getProperty("correct_password"), getProperty("incorrect_phone"), getProperty("incorrect_name_surname"));
        String errorMessNameAndSurname = registrationPage.getErrorMessIncorrectNameSurname();
        System.out.println(errorMessNameAndSurname);
        Assert.assertEquals("Допустимы только буквы, дефис и пробел", errorMessNameAndSurname);
    }
    @Test
    public void negativeTestIncorrectEmailExist() {
        mainPage = new MainPage(driver);
        mainPage.clickEnter();
        mainPage.registrateUser();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.registrationSubmission(getProperty("existing_email"), getProperty("correct_password"), getProperty("incorrect_phone"), getProperty("correct_name_surname"));
        String errorMessEmailExist = registrationPage.getErrorMessageEmailExist();
        System.out.println(errorMessEmailExist);
        Assert.assertEquals("Пользователь с таким логином уже существует", errorMessEmailExist);
    }

    @Test
    public void positiveLoginTest(){
        mainPage = new MainPage(driver);
        mainPage.clickEnter();
        mainPage.login(getProperty("correct_login"),getProperty("correct_password"));
        PersonalPage personalPage = new PersonalPage(driver);
        Boolean result = personalPage.isPersonalLabelDispalyed();
        Assert.assertTrue(result);

    }




    @After
    public void tearDown() {
        driver.quit();
    }
}
