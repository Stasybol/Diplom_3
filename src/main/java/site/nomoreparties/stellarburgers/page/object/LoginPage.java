package site.nomoreparties.stellarburgers.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static site.nomoreparties.stellarburgers.constant.UrlAndDuration.DEFAULT_TIMEOUT;

public class LoginPage {
    //локатор текста "Вход"
    private static final By TEXT_LOGIN_FORM = By.xpath("//div[@class='Auth_login__3hAey']//h2[text()='Вход']");
    //локатор кнопки "Зарегистрироваться"
    private static final By BUTTON_REGISTER = By.className("Auth_link__1fOlj");
    //локатор поля "Email"
    private static final By FIELD_EMAIL = By.xpath(".//input[@name='name']");
    //локатор поля "Пароль"
    private static final By FIELD_PASSWORD = By.xpath(".//input[@name='Пароль']");
    //локатор кнопки "Войти"
    private static final By BUTTON_LOGIN = By.cssSelector(".button_button__33qZ0.button_button_type_primary__1O7Bx.button_button_size_medium__3zxIa");
    //локатор кнопки "Восстановить пароль"
    private static final By BUTTON_RECOVER_PASSWORD = By.xpath(".//a[@class='Auth_link__1fOlj' and text()='Восстановить пароль']");

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //метод ожидания загрузки страницы и возвращающий true, если осуществился переход на страницу авторизации
    @Step("Переход на страницу авторизации")
    public boolean openingLoginForm() {
        new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(TEXT_LOGIN_FORM));
        return driver.findElement(TEXT_LOGIN_FORM).isDisplayed();
    }

    //метод клика на кнопку "Зарегистрироваться"
    @Step("Клик на кнопку \"Зарегистрироваться\"")
    public LoginPage clickRegister() {
        driver.findElement(BUTTON_REGISTER).click();
        return this;
    }

    //метод заполняет поле "Email"
    @Step("Заполнение поля email")
    public LoginPage setEmail(String email) {
        driver.findElement(FIELD_EMAIL).sendKeys(email);
        return this;
    }

    //метод заполняет поле "Пароль"
    @Step("Заполнение поля пароль")
    public LoginPage setPassword(String password) {
        driver.findElement(FIELD_PASSWORD).sendKeys(password);
        return this;
    }

    //метод кликает по кнопке "Войти"
    @Step("Клик по кнопке \"Войти\"")
    public LoginPage clickLoginButton() {
        driver.findElement(BUTTON_LOGIN).click();
        return this;
    }

    //метод регистрации: объединяет ввод email и пароля и клик по кнопке "Войти"
    @Step("Заполнение полей email и пароль и клик по кнопке \"Войти\"")
    public LoginPage login(String email, String password){
        setEmail(email);
        setPassword(password);
        clickLoginButton();
        return this;
    }

    //метод клика по кнопке "«"Восстановить пароль"
    @Step("Клик на кнопку \"Восстановить пароль\"")
    public LoginPage clickButtonRecoverPassword() {
        driver.findElement(BUTTON_RECOVER_PASSWORD).click();
        return this;
    }
}
