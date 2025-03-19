import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.factory.WebDriverFactory;
import site.nomoreparties.stellarburgers.page.object.HeaderPage;
import site.nomoreparties.stellarburgers.page.object.HomePage;
import site.nomoreparties.stellarburgers.page.object.LoginPage;
import static site.nomoreparties.stellarburgers.constant.Browser.BROWSER_CHROME;

public class TransitionToConstructorTest {
    private WebDriver driver;
    private HomePage homePage;
    private HeaderPage headerPage;
    private LoginPage loginPage;

    @Before
    @DisplayName("Инициализация драйвера и страниц перед тестом")
    @Description("Метод настраивает WebDriver для браузера, создает экземпляры страниц (HomePage, HeaderPage и LoginPage) и проверяет, что открылась страница авторизации")
    public void setUp(){
        driver = WebDriverFactory.createForName(BROWSER_CHROME);
        homePage = new HomePage(driver);
        headerPage = new HeaderPage(driver);
        loginPage = new LoginPage(driver);
        homePage.openingHomePage();
        headerPage.clickButtonPersonalAccount();
        Assert.assertTrue(loginPage.openingLoginForm());
    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета в конструктор")
    @Description("Тест проверяет переход из личного кабинета в конструктор при нажатии на кнопку \"Конструктор\" в шапке сайта")
    public void checkingTransitionClickingOnConstructor() {
        headerPage.clickButtonConstructor();
        Assert.assertTrue(homePage.isDisplayedTextAssembleBurger());
    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета в конструктор")
    @Description("Тест проверяет переход из личного кабинета в конструктор при нажатии на логотип Stellar Burgers в шапке сайта")
    public void checkingTransitionClickingOnLogo(){
        headerPage.clickButtonLogo();
        Assert.assertTrue(homePage.isDisplayedTextAssembleBurger());
    }

    @After
    @DisplayName("Закрытие браузера")
    @Description("Метод закрывает браузер")
    public void tearDown(){
        driver.quit();
    }
}
