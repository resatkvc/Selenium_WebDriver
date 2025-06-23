package tests;

import config.UserConfig;
import org.openqa.selenium.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class LoginPage {
    private final WebDriver driver;
    private final By usernameInput = By.id("user-name");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(UserConfig.User user) {
        driver.findElement(usernameInput).clear();
        driver.findElement(passwordInput).clear();
        driver.findElement(usernameInput).sendKeys(user.username);
        driver.findElement(passwordInput).sendKeys(user.password);
        driver.findElement(loginButton).click();
    }

    public boolean isLoginSuccessful() {
        return driver.getCurrentUrl().contains("inventory.html");
    }

    public boolean isErrorDisplayed() {
        try {
            WebElement error = driver.findElement(errorMessage);
            return error.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        try {
            return driver.findElement(errorMessage).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String takeScreenshot(String name) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String dest = "target/screenshots/" + name + ".png";
        try {
            File destFile = new File(dest);
            destFile.getParentFile().mkdirs();
            Files.copy(src.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return dest;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
} 