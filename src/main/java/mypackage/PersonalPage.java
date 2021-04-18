package mypackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalPage {
    public final By PERSONAL_LABEL = By.xpath("//*[contains(text(), 'Anna Shersteva')]");
    private WebDriver driver;

    public PersonalPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isPersonalLabelDispalyed() {
        return driver.findElement(PERSONAL_LABEL).isDisplayed();

    }
}
