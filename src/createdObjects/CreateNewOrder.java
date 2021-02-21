package createdObjects;

import menu.MainMenuConstantText;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CreateNewOrder {
    MainMenuConstantText mainMenuConstantText = new MainMenuConstantText();
    private String usersNameToFeedBack;
    private String usersTelNumberToFeedback;
    private String usersMailToFeedback;
    private String usersNoteInFeedback;

    public String getUsersNameToFeedBack() {
        return usersNameToFeedBack;
    }

    public void setUsersNameToFeedBack(String usersNameToFeedBack) {
        this.usersNameToFeedBack = usersNameToFeedBack;
    }

    public String getUsersTelNumberToFeedback() {
        return usersTelNumberToFeedback;
    }

    public void setUsersTelNumberToFeedback(String usersTelNumberToFeedback) {
        this.usersTelNumberToFeedback = usersTelNumberToFeedback;
    }

    public String getUsersMailToFeedback() {
        return usersMailToFeedback;
    }

    public void setUsersMailToFeedback(String usersMailToFeedback) {
        this.usersMailToFeedback = usersMailToFeedback;
    }

    public String getUsersNoteInFeedback() {
        return usersNoteInFeedback;
    }

    public void setUsersNoteInFeedback(String usersNoteInFeedback) {
        this.usersNoteInFeedback = usersNoteInFeedback;
    }

    public String createNewUsersOrder() {
        Scanner usersName = new Scanner(System.in);
        System.out.println("Укажите Ваше имя:");
        usersNameToFeedBack = usersName.nextLine();
        while (usersNameToFeedBack.isEmpty()) {
            mainMenuConstantText.ifEnteredEmptyString();
            usersNameToFeedBack = usersName.nextLine();
        }
        Scanner telNumber = new Scanner(System.in);
        System.out.println("Укажите Ваш контактный телефон:");
        usersTelNumberToFeedback = telNumber.nextLine();
        while (usersTelNumberToFeedback.isEmpty()) {
            mainMenuConstantText.ifEnteredEmptyString();
            usersTelNumberToFeedback = telNumber.nextLine();
        }
        Scanner usersMail = new Scanner(System.in);
        System.out.println("Укажите адрес электронной почты:");
        usersMailToFeedback = usersMail.nextLine();
        while (usersMailToFeedback.isEmpty()) {
            mainMenuConstantText.ifEnteredEmptyString();
            usersMailToFeedback = usersMail.nextLine();
        }
        Scanner usersNote = new Scanner(System.in);
        System.out.println("Примечание:");
        usersNoteInFeedback = usersNote.nextLine();
        while (usersNoteInFeedback.isEmpty()) {
            mainMenuConstantText.ifEnteredEmptyString();
            usersNoteInFeedback = usersNote.nextLine();
        }
        return usersNameToFeedBack + usersNoteInFeedback + usersMailToFeedback + usersTelNumberToFeedback;
    }

    @Override
    public String toString() {
        return "Пользователь: " + usersNameToFeedBack +
                ", Контактный телефон: " + usersTelNumberToFeedback +
                ", Адрес эл.почты: " + usersMailToFeedback +
                ", Примечание: " + usersNoteInFeedback;
    }

    public void writeUsersOrderInFile() {
        BufferedWriter fileWriter = null;
        String editOrdersList = "OrdersList.txt";
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            fileWriter = new BufferedWriter(new FileWriter((editOrdersList), true));
            fileWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String dateOfEdit = "{" + toString() + ", новая заявка создана: " + dateTimeFormatter.format(localDate) + ";}";
        try {
            fileWriter.write(dateOfEdit);
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
}
