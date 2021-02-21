package createdObjects;

import menu.MainMenuConstantText;

import java.util.Scanner;

public class CreateNewUser {
    MainMenuConstantText mainMenuConstantText = new MainMenuConstantText();
    private String usersLogin;
    private String usersName;
    private String status;

    public String getUsersLogin() {
        return usersLogin;
    }

    public void setUsersLogin(String usersLogin) {
        this.usersLogin = usersLogin;
    }

    public String getUsersName() {
        return usersName;
    }

    public void setUsersName(String usersName) {
        this.usersName = usersName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String createUsersLogin() {
        Scanner userNewLogin = new Scanner(System.in);
        usersLogin = userNewLogin.nextLine();

        while (usersLogin.length()==0) {
            mainMenuConstantText.ifEnteredEmptyString();
            usersLogin = userNewLogin.next();
        }
        return usersLogin;
    }

    public String createUsersName() {
        Scanner userNewName = new Scanner(System.in);
        usersName = userNewName.nextLine();
        while (usersName.isEmpty()) {
            mainMenuConstantText.ifEnteredEmptyString();
            usersName = userNewName.nextLine();
        }
        return usersName;
    }

    public String createUsersStatus() {
        Scanner userNewStatus = new Scanner(System.in);
        status = userNewStatus.nextLine();
        while (status.isEmpty()) {
            mainMenuConstantText.ifEnteredEmptyString();
            status = userNewStatus.nextLine();
        }
        return status;
    }
}
