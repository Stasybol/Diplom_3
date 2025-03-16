package site.nomoreparties.stellarburgers.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static site.nomoreparties.stellarburgers.constant.UrlAndDuration.DEFAULT_TIMEOUT;
import static site.nomoreparties.stellarburgers.constant.UrlAndDuration.PAGE_URL;

public class HomePage {
    //локатор кнопки "Войти в аккаунт"
    private static final By BUTTON_LOGIN_TO_ACCOUNT = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg' and text()='Войти в аккаунт']");
    //локатор кнопки "Оформить заказ"
    private static final By CREATE_ORDER = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg' and text()='Оформить заказ']");
    //локатор текста "Соберите бургер"
    private static final By TEXT_ASSEMBLE_BURGER = By.xpath(".//h1[@class='text text_type_main-large mb-5 mt-10' and text()='Соберите бургер']");
    //локатор раздела "Булки"
    private static final By SECTION_BUNS = By.xpath(".//span[@class='text text_type_main-default' and text()='Булки']");
    //локатор раздела "Соусы"
    private static final By SECTION_SAUCES = By.xpath(".//span[@class='text text_type_main-default' and text()='Соусы']");
    //локатор раздела "Начинки"
    private static final By SECTION_FILLINGS = By.xpath(".//span[@class='text text_type_main-default' and text()='Начинки']");
    //локатор текста "Булки"
    private static final By TEXT_BUNS = By.xpath("//div[@class='BurgerIngredients_ingredients__menuContainer__Xu3Mo']//h2[text()='Булки']");
    //локатор текста "Соусы"
    private static final By TEXT_SAUCES = By.xpath("//div[@class='BurgerIngredients_ingredients__menuContainer__Xu3Mo']//h2[text()='Соусы']");
    //локатор текста "Начинки"
    private static final By TEXT_FILLINGS = By.xpath("//div[@class='BurgerIngredients_ingredients__menuContainer__Xu3Mo']//h2[text()='Начинки']");

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //метод открытия стартовой страницы
    @Step("Открытие стартовой страницы")
    public HomePage openingHomePage() {
        driver.get(PAGE_URL);
        return this;
    }

    //метод клика на кнопку "Войти в аккаунт"
    @Step("Клик на кнопку \"Войти в аккаунт\"")
    public HomePage clickButtonLoginToAccount() {
        driver.findElement(BUTTON_LOGIN_TO_ACCOUNT).click();
        return this;
    }

    //метод ожидания загрузки страницы и возвращающий true, если осуществился переход на стартовую страницу после авторизации
    @Step("Переход на стартовую страницу после авторизации")
    public boolean homePageAfterAuthorization() {
        new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(CREATE_ORDER));
        return driver.findElement(CREATE_ORDER).isDisplayed();
    }

    //метод ожидания загрузки страницы и возвращающий true, если осуществился переход на страницу с текстом "Соберите бургер"
    @Step("Переход на стартовую страницу и нахождение текста \"Соберите бургер\"")
    public boolean isDisplayedTextAssembleBurger() {
        new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(TEXT_ASSEMBLE_BURGER));
        return driver.findElement(TEXT_ASSEMBLE_BURGER).isDisplayed();
    }

    //метод клика на раздел "Булки"
    @Step("Клик на раздел \"Булки\"")
    public HomePage clickSectionBuns() {
        driver.findElement(SECTION_BUNS).click();
        return this;
    }

    //метод клика на раздел "Соусы"
    @Step("Клик на раздел \"Соусы\"")
    public HomePage clickSectionSauces() {
        driver.findElement(SECTION_SAUCES).click();
        return this;
    }

    //метод клика на раздел "Начинки"
    @Step("Клик на раздел \"Начинки\"")
    public HomePage clickSectionFillings() {
        driver.findElement(SECTION_FILLINGS).click();
        return this;
    }

    //метод возвращающий элемент с текстом "Булки"
    @Step("Поиск элемента с текстом \"Булки\"")
    public WebElement elementTextBuns() {
        return driver.findElement(TEXT_BUNS);
    }

    //метод возвращающий элемент с текстом "Соусы"
    @Step("Поиск элемента с текстом \"Соусы\"")
    public WebElement elementTextSauces() {
        return driver.findElement(TEXT_SAUCES);
    }

    //метод возвращающий элемент с текстом "Начинки"
    @Step("Поиск элемента с текстом \"Начинки\"")
    public WebElement elementTextFillings() {
        return driver.findElement(TEXT_FILLINGS);
    }
}
