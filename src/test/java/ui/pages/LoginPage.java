package ui.pages;

import config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By USERNAME_FIELD = By.id("Username");
    private final By PASSWORD_FIELD = By.id("Password");
    private final By LOGIN_BUTTON = By.cssSelector("button[type='submit']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login() {
        type(USERNAME_FIELD, Config.USERNAME);
        type(PASSWORD_FIELD, Config.PASSWORD);
        click(LOGIN_BUTTON);
    }
}