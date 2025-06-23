package tests;

import config.BaseUrlConfig;
import config.UserConfig;
import config.ErrorMessages;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("SauceDemo Login Tests")
public class SauceDemoLoginTest {
    private WebDriver driver;
    private static final Logger logger = Logger.getLogger(SauceDemoLoginTest.class.getName());
    private LoginPage loginPage;

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
        driver.get(BaseUrlConfig.BASE_URL);
        loginPage = new LoginPage(driver);
        logger.info("LOG: TARAYICI BAŞLATILDI VE SİTEYE GİDİLDİ: " + BaseUrlConfig.BASE_URL);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("LOG: TARAYICI KAPATILDI.");
        }
    }

    @Test
    @DisplayName("Standard user ile başarılı giriş")
    void testLoginWithStandardUser() {
        logger.info("LOG: STANDARD USER İLE GİRİŞ DENENİYOR.");
        loginPage.login(UserConfig.STANDARD_USER);
        assertTrue(loginPage.isLoginSuccessful(), "Login başarısız!");
        logger.info("LOG: STANDARD USER İLE BAŞARILI GİRİŞ YAPILDI.");
    }

    @Test
    @DisplayName("Problem user ile başarılı giriş")
    void testLoginWithProblemUser() {
        logger.info("LOG: PROBLEM USER İLE GİRİŞ DENENİYOR.");
        loginPage.login(UserConfig.PROBLEM_USER);
        assertTrue(loginPage.isLoginSuccessful(), "Login başarısız!");
        logger.info("LOG: PROBLEM USER İLE BAŞARILI GİRİŞ YAPILDI.");
    }

    @Test
    @DisplayName("Locked out user ile hatalı giriş ve mesaj doğrulama")
    void testLoginWithLockedOutUser() {
        logger.info("LOG: LOCKED OUT USER İLE GİRİŞ DENENİYOR.");
        loginPage.login(UserConfig.LOCKED_OUT_USER);
        assertTrue(loginPage.isErrorDisplayed());
        String actual = loginPage.getErrorMessage();
        if (!ErrorMessages.LOCKED_OUT_USER.equals(actual)) {
            attachScreenshot("locked_out_user_fail");
            logger.severe("LOG: BEKLENEN HATA MESAJI: '" + ErrorMessages.LOCKED_OUT_USER + "', EKRANDAKİ: '" + actual + "'");
        }
        assertEquals(ErrorMessages.LOCKED_OUT_USER, actual);
        logger.info("LOG: LOCKED OUT USER İLE GİRİŞTE HATA MESAJI DOĞRULANDI.");
    }

    @Test
    @DisplayName("Geçersiz kullanıcı ile hatalı giriş ve mesaj doğrulama")
    void testLoginWithInvalidUser() {
        logger.info("LOG: GEÇERSİZ KULLANICI İLE GİRİŞ DENENİYOR.");
        loginPage.login(UserConfig.INVALID_USER);
        assertTrue(loginPage.isErrorDisplayed());
        String actual = loginPage.getErrorMessage();
        if (!ErrorMessages.INVALID_USER.equals(actual)) {
            attachScreenshot("invalid_user_fail");
            logger.severe("LOG: BEKLENEN HATA MESAJI: '" + ErrorMessages.INVALID_USER + "', EKRANDAKİ: '" + actual + "'");
        }
        assertEquals(ErrorMessages.INVALID_USER, actual);
        logger.info("LOG: GEÇERSİZ KULLANICI İLE GİRİŞTE HATA MESAJI DOĞRULANDI.");
    }

    @Attachment(value = "Ekran Görüntüsü", type = "image/png")
    public byte[] attachScreenshot(String name) {
        try {
            String path = loginPage.takeScreenshot(name);
            if (path != null) {
                return Files.readAllBytes(Paths.get(path));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
} 