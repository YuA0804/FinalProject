package editAndRemove;

import createdObjects.UsersListReading;
import menu.MainMenuConstantText;
import createdObjects.UsersData;
import menu.UsersInfoNavigation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class RemoveUserDataElement {
    MainMenuConstantText mainMenuConstantText = new MainMenuConstantText();
    ArrayList<UsersData> listUsers = new ArrayList<>();
    UsersListReading usersListReading = new UsersListReading();
    public int indexForRemove;
    String usersListAfterRemove = "UsersList.txt";
    BufferedWriter fileWriter;
    String editUsersList;
    LocalDate localDate = LocalDate.now();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String dateOfEdit = "Список был изменён: ";

    public void removeUserBySelectedElement() {
        UsersInfoNavigation usersInfoNavigation = new UsersInfoNavigation();
        try {
            listUsers = usersListReading.readUsersListFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (listUsers.isEmpty()) {
            System.err.println("Список пользователей пуст.");
        } else {
            mainMenuConstantText.replaceElementByNumber();
            Scanner in = new Scanner(System.in);
            indexForRemove = in.nextInt();

            while (indexForRemove >= listUsers.size() | indexForRemove < 0) {
                mainMenuConstantText.ifEnteredWrongValue();
                mainMenuConstantText.replaceElementByNumber();
                indexForRemove = in.nextInt();
            }
            while (listUsers.get(indexForRemove).getStatus().compareTo("admin") == 0) {
                System.err.println("Пользователь со статусом admin не может быть удалён!");
                mainMenuConstantText.replaceElementByNumber();
                indexForRemove = in.nextInt();
            }
            System.out.println("Пользователь " + listUsers.remove(indexForRemove).getUserName() + " успешно удалён.");
            mainMenuConstantText.writeLine();
            StringBuilder stringBuilder = new StringBuilder(dateOfEdit);
            try {
                fileWriter = new BufferedWriter(new FileWriter(usersListAfterRemove));
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

