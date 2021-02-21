package editAndRemove;

import createdObjects.UsersListReading;
import menu.MainMenuConstantText;
import menu.User;
import createdObjects.UsersData;
import menu.UsersInfoNavigation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class EditUserDataElement {
    MainMenuConstantText mainMenuConstantText = new MainMenuConstantText();
    ArrayList<UsersData> listUsers = new ArrayList<>();
    UsersListReading usersListReading = new UsersListReading();
    public int indexForEdit;
    User user = new User();
    String usersListAfterEdit = "UsersList.txt";
    BufferedWriter fileWriter;
    String editUsersList;
    LocalDate localDate = LocalDate.now();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String dateOfEdit = "Список был изменён: ";

    public void editUserBySelectedElement() {
        UsersInfoNavigation usersInfoNavigation = new UsersInfoNavigation();
        try {
            listUsers = usersListReading.readUsersListFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (listUsers.isEmpty()) {
            System.err.println("Список пользователей пуст.");
        } else {
            mainMenuConstantText.editElementByNumber();
            Scanner in = new Scanner(System.in);
            indexForEdit = in.nextInt();
            while (indexForEdit >= listUsers.size() | indexForEdit < 0) {
                mainMenuConstantText.ifEnteredWrongValue();
                mainMenuConstantText.editElementByNumber();
                indexForEdit = in.nextInt();
            }
            System.out.println("Введите новое имя пользователя:");
            Scanner newValue = new Scanner(System.in);
            String usersNewName = newValue.nextLine();
            while (usersNewName.isEmpty()) {
                mainMenuConstantText.ifEnteredEmptyString();
                usersNewName = newValue.nextLine();
            }
            System.out.println("Укажите новый статус пользователя (user/admin):");
            String usersNewStatus = newValue.nextLine();
            while ((usersNewStatus.compareTo(user.getAdminStatus()) != 0) & (usersNewStatus.compareTo(user.getWorkerStatus()) != 0)) {
                mainMenuConstantText.ifEnteredWrongValue();
                usersNewStatus = newValue.nextLine();
            }
            listUsers.get(indexForEdit).setUserName(usersNewName);
            listUsers.get(indexForEdit).setStatus(usersNewStatus);
            System.out.println("Пользователь " + listUsers.get(indexForEdit).getUserLogin() + " был изменён.");
            mainMenuConstantText.writeLine();
            StringBuilder stringBuilder = new StringBuilder(dateOfEdit);

            try {
                fileWriter = new BufferedWriter(new FileWriter(usersListAfterEdit));
                fileWriter.write(String.valueOf(stringBuilder.append(dateTimeFormatter.format(localDate))));
                fileWriter.newLine();
                for (UsersData resultList : listUsers) {
                    editUsersList = "{userLogin = " + resultList.getUserLogin() + "; userName = " + resultList.getUserName() + "; status = " + resultList.getStatus() + ";}";
                    fileWriter.write(editUsersList);
                    fileWriter.newLine();
                }
            } catch (IOException e) {

                e.printStackTrace();
            } finally {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        usersInfoNavigation.selectUserMenu();
    }
}
