import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import site.nomoreparties.stellarburgers.client.BurgerServiceClient;
import site.nomoreparties.stellarburgers.model.Credentials;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.factory.WebDriverFactory;
import site.nomoreparties.stellarburgers.page.object.HeaderPage;
import site.nomoreparties.stellarburgers.page.object.HomePage;
import site.nomoreparties.stellarburgers.page.object.LoginPage;
import site.nomoreparties.stellarburgers.page.object.RegistrationPage;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.hamcrest.CoreMatchers.equalTo;
import static site.nomoreparties.stellarburgers.constant.Browser.BROWSER_CHROME;
import static site.nomoreparties.stellarburgers.constant.UrlAndDuration.PAGE_URL;


public class RegistrationTest {
    private WebDriver driver;
    private HomePage homePage;
    private HeaderPage headerPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private BurgerServiceClient client;
    private Credentials credentials;
    private static Faker faker = new Faker();
    private String name;
    private String email;
    private String password;


    @Before
    @DisplayName("Инициализация драйвера и страниц перед тестом")
    @Description("Метод настраивает WebDriver для браузера, создает экземпляры страниц (HomePage, HeaderPage и LoginPage) и проверяет переход на страницу регистрации")
    public void setUp(){
        driver = WebDriverFactory.createForName(BROWSER_CHROME);
        homePage = new HomePage(driver);
        homePage.openingHomePage();

        headerPage = new HeaderPage(driver);
        headerPage.clickButtonPersonalAccount();

        loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.openingLoginForm());
        loginPage.clickRegister();

        registrationPage = new RegistrationPage(driver);
        Assert.assertTrue(registrationPage.openingRegistrationPage());
    }


    @Test
    @DisplayName("Успешная регистрации при длине пароля 7 символов")
    @Description("Тест проверяет успешную регистрацию при вводе в поле \"Пароль\" 7 символов")
    public void successRegistrationWithLengthPassword7Test() {
        name = faker.name().firstName();
        email = faker.internet().emailAddress();
        password = faker.lorem().characters(7);

        registrationPage.register(name, email, password);
        Assert.assertTrue(loginPage.openingLoginForm());

    }

    @Test
    @DisplayName("Успешная регистрации при длине пароля 6 символов")
    @Description("Тест проверяет успешную регистрацию при вводе в поле \"Пароль\" 6 символов")
    public void successRegistrationWithLengthPassword6Test() {
        name = faker.name().firstName();
        email = faker.internet().emailAddress();
        password = faker.lorem().characters(6);

        registrationPage.register(name, email, password);
        Assert.assertTrue(loginPage.openingLoginForm());
    }

    @Test
    @DisplayName("Ошибка при регистрации при длине пароля 5 символов")
    @Description("Тест проверяет ошибку \"Некоректрный пароль\" при регистрации, когда в поле \"Пароль\" введено 5 символов")
    public void errorWithLengthPassword5Test() {
        name = faker.name().firstName();
        email = faker.internet().emailAddress();
        password = faker.lorem().characters(5);

        registrationPage.register(name, email, password);
        Assert.assertTrue(registrationPage.isDisplayedErrorIncorrectPassword());

    }

    @Test
    @DisplayName("Ошибка при регистрации при длине пароля 4 символа")
    @Description("Тест проверяет ошибку \"Некоректрный пароль\" при регистрации, когда в поле \"Пароль\" введено 4 символа")
    public void errorWithLengthPassword4Test() {
        name = faker.name().firstName();
        email = faker.internet().emailAddress();
        password = faker.lorem().characters(4);

        registrationPage.register(name, email, password);
        Assert.assertTrue(registrationPage.isDisplayedErrorIncorrectPassword());
    }

    @After
    @DisplayName("Закрытие браузера и удаление пользователя")
    @Description("Метод закрывает браузер и удаляет пользователя после выполнения каждого теста, если пользователь был успешно создан")
    public void tearDown(){
        driver.quit();
        client = new BurgerServiceClient(PAGE_URL);
        credentials = new Credentials(email, password, name);
        ValidatableResponse responseLogin = client.loginUser(credentials);
        String token = responseLogin.extract().path("accessToken");
        if (token != null) {
            ValidatableResponse responseDelete = client.deleteUser(token);
            responseDelete.assertThat().statusCode(SC_ACCEPTED).body("success", equalTo(true));
        }
    }
}
