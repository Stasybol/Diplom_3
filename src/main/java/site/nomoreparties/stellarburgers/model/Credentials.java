package site.nomoreparties.stellarburgers.model;

public class Credentials {
    private String email;
    private String password;
    private String name;

    public Credentials(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public static Credentials fromUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("Пользователь не может быть null.");
        }
        return new Credentials(user.getEmail(), user.getPassword(), user.getName());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
