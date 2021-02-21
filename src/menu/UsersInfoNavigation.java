package menu;

import createdObjects.CreateNewUser;
import createdObjects.UsersData;
import createdObjects.UsersListReading;
import editAndRemove.EditUserDataElement;
import editAndRemove.RemoveUserDataElement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UsersInfoNavigation {
    ConstantButtonsInMenu constantButtonsInMenu = new ConstantButtonsInMenu();
    MainMenuConstantText mainMenuConstantText = new MainMenuConstantText();
    InputCodeParagraph inputCodeParagraph = new InputCodeParagraph();
    UsersListReading usersListReading = new UsersListReading();
    RemoveUserDataElement removeElement = new RemoveUserDataElement();
    ArrayList<UsersData> listUsers = new ArrayList<>();
    EditUserDataElement editUserDataElement = new EditUserDataElement();
    CreateNewUser createNewUser = new CreateNewUser();
    String usersLogin;
    String userName;
    String userStatus;
    BufferedWriter fileWriter;
    String usersListAfterAdd = "UsersList.txt";
    String addUsersList;
    User user = new User();

    public void selectUserMenu() {
        ChooseMenuDisplayAdminOrUser chooseMenuDisplayAdminOrUser = new ChooseMenuDisplayAdminOrUser();
        Navigation navigation = new Navigation();
       try {
            listUsers = usersListReading.readUsersListFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (UsersData resultList : listUsers) {
            System.out.println(listUsers.indexOf(resultList) + " " + resultList);
        }
        mainMenuConstantText.writeLine();
        System.out.println(constantButtonsInMenu.first + " - Добавить нового пользователя");
        System.out.println(constantButtonsInMenu.second + " - Редактировать данные");
        System.out.println(constantButtonsInMenu.third + " - Удалить пользователя");
        mainMenuConstantText.displayMenuConstantTextGoToMainMenu();
        mainMenuConstantText.displayMenuConstantText();
        inputCodeParagraph.inputNumber();
        switch (inputCodeParagraph.getInputCode()) {
            case ONE:
                addNewUser();
                selectUserMenu();
                break;
            case TWO:
                editUserDataElement.editUserBySelectedElement();
                break;
            case THREE:
                removeElement.removeUserBySelectedElement();
                break;
            case NINE:
                //chooseMenuDisplayAdminOrUser.chooseMenuDisplay();
                break;
            case ZERO:
                navigation.tostartworkslike();
                break;
            default:
                break;
        }
    }

    private void addNewUser() {
        System.out.println("Укажите логин нового сотрудника: ");
        usersLogin = createNewUser.createUsersLogin();
        for (UsersData resultList : listUsers) {
            while (resultList.getUserLogin().compareTo(usersLogin) == 0) {
                System.err.println("Логин пользователя не уникален. Введите новое значение: ");
                usersLogin = createNewUser.createUsersLogin();
            }
        }
        System.out.println("Укажите имя нового сотрудника: ");
        userName = createNewUser.createUsersName();
        System.out.println("Укажите статус нового сотрудника (user/admin): ");
        userStatus = createNewUser.createUsersStatus();
        while (userStatus.compareTo(user.getWorkerStatus()) != 0 & userStatus.compareTo(user.getAdminStatus()) != 0) {
            System.err.println("Статус должен быть либо user либо admin. Укажите статус пользователя: ");
            userStatus = createNewUser.createUsersStatus();
        }
        listUsers.add(new UsersData(usersLogin, userName, userStatus));
        System.out.println("\n" + "Новый пользователь " + usersLogin + " успешно создан." + "\n");
        try {
            fileWriter = new BufferedWriter(new FileWriter((usersListAfterAdd), true));
            addUsersList = "{userLogin = " + listUsers.get(listUsers.size() - 1).getUserLogin() + "; userName = " + listUsers.get(listUsers.size() - 1).getUserName() + "; status = " + listUsers.get(listUsers.size() - 1).getStatus() + ";}";
            fileWriter.newLine();
            fileWriter.write(addUsersList);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // return listTours;
        }
    }
}