package password;

import java.util.Scanner;

public class PasswordInput {
    private String login;
    private String password;

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String inputLogin() {
        Scanner login1 = new Scanner(System.in);
        System.out.println("Введите логин: ");
        return login=login1.next();
    }

    public String inputPassword() {
        Scanner String1 = new Scanner(System.in);
        System.out.println("Введите пароль: ");
        return password=String1.next();
    }
}
