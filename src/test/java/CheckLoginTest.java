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
    @DisplayName("Подготовка данных: создание пользователя")
    @Description("Создание пользователя перед каждым тестом. Предполагается, что пользователь успешно создан и получен его token.")
    public void createUser(){
        client = new BurgerServiceClient(PAGE_URL);
        user = User.allField();
        ValidatableResponse responseCreate = client.createUser(user);
        responseCreate.assertThat().body("success", equalTo(true));
        token = responseCreate.extract().header("authorization");
        credentials = Credentials.fromUser(user);
    }

    @Before
    @DisplayName("Инициализация драйвера и страниц перед тестом")
    @Description("Метод настраивает WebDriver для браузера и создает экземпляры страниц (HomePage и LoginPage) перед выполнением каждого теста.")
    public void setUp() {
        driver = WebDriverFactory.createForName(BROWSER_CHROME);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    @DisplayName("Проверка входа через кнопку \"Войти в аккаунт\"")
    @Description("Тест проверяет переход на форму входа со стартовой странице при нажатии на кнопку \"Войти в аккаунт\"")
    public void viaButtonLoginToAccountTest(){
        homePage.openingHomePage().clickButtonLoginToAccount();
    }

    @Test
    @DisplayName("Проверка входа через кнопку \"Личный кабинет\"")
    @Description("Тест проверяет переход на форму входа при нажатии на кнопку \"Личный кабинет\" в шапке сайта")
    public void viaButtonPersonalAccountTest(){
        homePage.openingHomePage();
        headerPage = new HeaderPage(driver);
        headerPage.clickButtonPersonalAccount();
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
    }

    @After
    @DisplayName("Проверка авторизации и закрытие браузера")
    @Description("Метод проверяет успешный вход и закрывает браузер")
    public void tearDown(){
        Assert.assertTrue(loginPage.openingLoginForm());
        loginPage.login(credentials.getEmail(), credentials.getPassword());
        Assert.assertTrue(homePage.homePageAfterAuthorization());
        driver.quit();
    }

    @After
    @DisplayName("Очистка данных: удаление пользователя")
    @Description("Удаление пользователя после выполнения каждого теста")
    public void dataCleaning(){
        ValidatableResponse responseDelete = client.deleteUser(token);
        responseDelete.assertThat().statusCode(SC_ACCEPTED).body("success", equalTo(true));
    }

}
