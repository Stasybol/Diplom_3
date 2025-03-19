package site.nomoreparties.stellarburgers.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static site.nomoreparties.stellarburgers.constant.UrlAndDuration.DEFAULT_TIMEOUT;

public class PersonalAccountPage {
    //локатор кнопки "Выход"
    private static final By BUTTON_EXIT = By.xpath(".//button[@class='Account_button__14Yp3 text text_type_main-medium text_color_inactive' and text()='Выход']");

    WebDriver driver;

    public PersonalAccountPage(WebDriver driver){
        this.driver = driver;
    }

    //метод ожидания загрузки страницы и возвращающий true, если осуществился переход на страницу личного кабинета
    @Step("Переход на страницу личного кабинета")
    public boolean openingPersonalAccount() {
        new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(BUTTON_EXIT));
        return driver.findElement(BUTTON_EXIT).isDisplayed();
    }

    //метод клика по кнопке "Выход"
    @Step("Клик на кнопку \"Выход\"")
    public PersonalAccountPage clickButtonExit() {
        driver.findElement(BUTTON_EXIT).click();
        return this;
    }
}
