package site.nomoreparties.stellarburgers.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static site.nomoreparties.stellarburgers.constant.UrlAndDuration.DEFAULT_TIMEOUT;


public class RegistrationPage {
    //локатор текста "Регистрация" на странице регистрации
    private static final By TEXT_REGISTRATION = By.xpath(".//h2[text()='Регистрация']");
    //локатор поля "имя"
    private static final By FIELD_NAME = By.xpath("//label[text()='Имя']/following-sibling::input");
    //локатор поля "email"
    private static final By FIELD_EMAIL = By.xpath("//label[text()='Email']/following-sibling::input");
    //локатор поля "пароль"
    private static final By FIELD_PASSWORD = By.xpath(".//input[@class='text input__textfield text_type_main-default' and @name='Пароль']");
    //локатор кнопки "Зарегистрироваться"
    private static final By BUTTON_REGISTER = By.cssSelector(".button_button__33qZ0.button_button_type_primary__1O7Bx.button_button_size_medium__3zxIa");
    //локатор ошибки "Некорректный пароль"
    private static final By ERROR_INCORRECT_PASSWORD = By.xpath(".//p[@class='input__error text_type_main-default']");
    //локатор кнопки "Войти"
    private static final By BUTTON_LOGIN = By.xpath(".//a[@class='Auth_link__1fOlj' and text()='Войти']");

    private WebDriver driver;

    public RegistrationPage(WebDriver driver){
        this.driver = driver;
    }

    //метод ожидания загрузки страницы регистрации и возврат true, если найден элемент на странице регистрации
    @Step("Переход на страницу регистрации")
    public boolean openingRegistrationPage(){
        new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(TEXT_REGISTRATION));
        return driver.findElement(TEXT_REGISTRATION).isDisplayed();
    }

    // метод заполняет поле «Имя»
    public RegistrationPage setName(String name) {
        driver.findElement(FIELD_NAME).sendKeys(name);
        return this;
    }

    // метод заполняет поле «Email»
    public RegistrationPage setEmail(String email) {
        driver.findElement(FIELD_EMAIL).sendKeys(email);
        return this;
    }

    // метод заполняет поле «Пароль»
    public RegistrationPage setPassword(String password) {
        driver.findElement(FIELD_PASSWORD).sendKeys(password);
        return this;
    }

    // метод кликает по кнопке «Регистрация»
    public RegistrationPage clickSignUpButton() {
        driver.findElement(BUTTON_REGISTER).click();
        return this;
    }

    // метод регистрации: объединяет ввод имя, email, пароля и клик по кнопке "Зарегистрироваться"
    @Step("Заполнение полей имя, email и пароль и клик по кнопке \"Зарегистрироваться\"")
    public RegistrationPage register(String name, String email, String password){
        setName(name);
        setEmail(email);
        setPassword(password);
        clickSignUpButton();
        return this;
    }

    //метод ожидания элемента и возврат true, если найдена ошибка "Некорректный пароль" на странице регистрации
    @Step("Ожидание появление ошибки \"Некорректный пароль\"")
    public boolean isDisplayedErrorIncorrectPassword(){
        new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.visibilityOf(driver.findElement(ERROR_INCORRECT_PASSWORD)));
            return driver.findElement(ERROR_INCORRECT_PASSWORD).isDisplayed();
    }

    //метод клика на кнопку "Войти"
    @Step("Клик на кнопку \"Войти\"")
    public RegistrationPage clickButtonLogin() {
        driver.findElement(BUTTON_LOGIN).click();
        return this;
    }
}
