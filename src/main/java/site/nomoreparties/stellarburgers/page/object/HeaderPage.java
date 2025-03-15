package site.nomoreparties.stellarburgers.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderPage {
    //локатор кнопки "Личный кабинет"
    private static final By BUTTON_PERSONAL_ACCOUNT = By.xpath(".//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()='Личный Кабинет']");
    //локатор кнопки "Конструктор"
    private static final By BUTTON_CONSTRUCTOR = By.xpath(".//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()='Конструктор']");
    //локатор логотипа
    private static final By BUTTON_LOGO = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");

    WebDriver driver;

    public HeaderPage(WebDriver driver){
        this.driver = driver;
    }

    //метод клика на кнопку "Личный кабинет"
    @Step("Клик на кнопку \"Личный кабинет\"")
    public HeaderPage clickButtonPersonalAccount() {
        driver.findElement(BUTTON_PERSONAL_ACCOUNT).click();
        return this;
    }

    //метод клика по кнопке "Конструктор"
    @Step("Клик на кнопку \"Конструктор\"")
    public HeaderPage clickButtonConstructor() {
        driver.findElement(BUTTON_CONSTRUCTOR).click();
        return this;
    }

    //метод клика по логотипу
    @Step("Клик по логопиту Stellar Burgers")
    public HeaderPage clickButtonLogo() {
        driver.findElement(BUTTON_LOGO).click();
        return this;
    }
}
