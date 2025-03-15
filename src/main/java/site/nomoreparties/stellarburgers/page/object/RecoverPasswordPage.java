package site.nomoreparties.stellarburgers.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static site.nomoreparties.stellarburgers.constant.UrlAndDuration.DEFAULT_TIMEOUT;

public class RecoverPasswordPage {
    //локатор текста "Восстановление пароля"
    private static final By TEXT_RECOVER_PASSWORD = By.xpath(".//h2[text()='Восстановление пароля']");
    //локатор кнопки "Войти"
    private static final By BUTTON_LOGIN = By.xpath(".//a[@class='Auth_link__1fOlj' and text()='Войти']");

    private WebDriver driver;

    public RecoverPasswordPage(WebDriver driver){
        this.driver = driver;
    }

    //метод ожидания загрузки страницы и возвращающий true, если осуществился переход на страницу восстановления пароля
    @Step("Переход на страницу восстановления пароля")
    public boolean openingPageRecoverPassword() {
        new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(TEXT_RECOVER_PASSWORD));
        return driver.findElement(TEXT_RECOVER_PASSWORD).isDisplayed();
    }

    //метод нажатия на кнопку "Войти"
    @Step("Клик на кнопку \"Войти\"")
    public RecoverPasswordPage clickButtonLogin() {
        driver.findElement(BUTTON_LOGIN).click();
        return this;
    }
}
