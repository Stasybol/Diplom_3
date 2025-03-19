package site.nomoreparties.stellarburgers.model;

import com.github.javafaker.Faker;


public class User {
    private String email;
    private String password;
    private String name;

    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static User allField() {
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(8, 16, true, true, true);
        String name = faker.name().firstName();
        return new User(email, password, name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
