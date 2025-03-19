import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.client.BurgerServiceClient;
import site.nomoreparties.stellarburgers.factory.WebDriverFactory;
import site.nomoreparties.stellarburgers.model.Credentials;
import site.nomoreparties.stellarburgers.model.User;
import site.nomoreparties.stellarburgers.page.object.*;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.hamcrest.CoreMatchers.equalTo;
import static site.nomoreparties.stellarburgers.constant.Browser.BROWSER_CHROME;
import static site.nomoreparties.stellarburgers.constant.UrlAndDuration.*;


public class CheckLoginTest {
    private User user;
    private BurgerServiceClient client;
    private String token;
    private Credentials credentials;
    private WebDriver driver;
    private HomePage homePage;
    private HeaderPage headerPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private RecoverPasswordPage recoverPasswordPage;

    @Before
    @DisplayName("Подготовка данных: создание пользователя, инициализация драйвера и страниц перед тестом")
    @Description("Создание пользователя перед каждым тестом и получение его токена. Настройка WebDriver для браузера и создание экземпляров страниц (HomePage и LoginPage) перед выполнением каждого теста.")
    public void setUp(){
        client = new BurgerServiceClient(PAGE_URL);
        user = User.allField();
        ValidatableResponse responseCreate = client.createUser(user);
        responseCreate.assertThat().body("success", equalTo(true));
        token = responseCreate.extract().header("authorization");
        credentials = Credentials.fromUser(user);

        driver = WebDriverFactory.createForName(BROWSER_CHROME);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    @DisplayName("Проверка входа через кнопку \"Войти в аккаунт\"")
    @Description("Тест проверяет переход на форму входа со стартовой странице при нажатии на кнопку \"Войти в аккаунт\"")
    public void viaButtonLoginToAccountTest(){
        homePage.openingHomePage().clickButtonLoginToAccount();
        Assert.assertTrue(loginPage.openingLoginForm());
        loginPage.login(credentials.getEmail(), credentials.getPassword());
        Assert.assertTrue(homePage.homePageAfterAuthorization());
    }

    @Test
    @DisplayName("Проверка входа через кнопку \"Личный кабинет\"")
    @Description("Тест проверяет переход на форму входа при нажатии на кнопку \"Личный кабинет\" в шапке сайта")
    public void viaButtonPersonalAccountTest(){
        homePage.openingHomePage();
        headerPage = new HeaderPage(driver);
        headerPage.clickButtonPersonalAccount();
        Assert.assertTrue(loginPage.openingLoginForm());
        loginPage.login(credentials.getEmail(), credentials.getPassword());
        Assert.assertTrue(homePage.homePageAfterAuthorization());
    }

    @Test
    @DisplayName("Проверка входа через кнопку \"Войти\" на странице регистрации")
    @Description("Тест проверяет переход на форму входа при нажатии на кнопку \"Войти\" на странице регистрации")
    public void viaButtonInRegistrationFormTest(){
        homePage.openingHomePage();
        headerPage = new HeaderPage(driver);
        headerPage.clickButtonPersonalAccount();
        Assert.assertTrue(loginPage.openingLoginForm());
        loginPage.clickRegister();
        registrationPage = new RegistrationPage(driver);
        Assert.assertTrue(registrationPage.openingRegistrationPage());
        registrationPage.clickButtonLogin();
        Assert.assertTrue(loginPage.openingLoginForm());
        loginPage.login(credentials.getEmail(), credentials.getPassword());
        Assert.assertTrue(homePage.homePageAfterAuthorization());
    }

    @Test
    @DisplayName("Проверка входа через кнопку \"Войти\" на странице восстановления пароля")
    @Description("Тест проверяет переход на форму входа при нажатии на кнопку \"Войти\" на странице восстановления пароля")
    public void viaButtonInPasswordRecoveryFormTest(){
        homePage.openingHomePage().clickButtonLoginToAccount();
        Assert.assertTrue(loginPage.openingLoginForm());
        loginPage.clickButtonRecoverPassword();
        recoverPasswordPage = new RecoverPasswordPage(driver);
        Assert.assertTrue(recoverPasswordPage.openingPageRecoverPassword());
        recoverPasswordPage.clickButtonLogin();
        Assert.assertTrue(loginPage.openingLoginForm());
        loginPage.login(credentials.getEmail(), credentials.getPassword());
        Assert.assertTrue(homePage.homePageAfterAuthorization());
    }

    @After
    @DisplayName("Закрытие браузера и удаление пользователя")
    @Description("Метод закрывает браузер и удаляет пользователя, созданого перед тестом")
    public void tearDown(){
        driver.quit();
        ValidatableResponse responseDelete = client.deleteUser(token);
        responseDelete.assertThat().statusCode(SC_ACCEPTED).body("success", equalTo(true));
    }

}
