package menu;

import createdObjects.ReviewListReading;
import password.PasswordInput;
import password.PasswordLoginExtract;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Navigation {
    final int Admin = 1, Worker = 2, Client = 3;
    PasswordInput passwordInput = new PasswordInput();
    InputCodeParagraph inputCodeParagraph = new InputCodeParagraph();
    ClientStartMenu clientStartMenu = new ClientStartMenu();
    AdminStartMenu adminStartMenu = new AdminStartMenu();
    WorkerStartMenu workerStartMenu = new WorkerStartMenu();
    PasswordLoginExtract passwordLoginExtract = new PasswordLoginExtract();
    MainMenuConstantText mainMenuConstantText = new MainMenuConstantText();
    User user = new User();
    ReviewListReading reviewListReading = new ReviewListReading();

    public String getRoleInApp() {
        return roleInApp;
    }

    private String roleInApp;

    public void tostartworkslike() {
        System.out.println("Вход в систему:");
        System.out.println(Admin + " - Администратор");
        System.out.println(Worker + " - Сотрудник");
        System.out.println(Client + " - Пользователь");
        System.out.println("Для выбора режима работы, введите код (1-3)");
        inputCodeParagraph.inputNumber();

        switch (inputCodeParagraph.getInputCode()) {
            case ONE:
                enterAsAdmin();
                break;
            case TWO:
                enterAsStaff();
                break;
            case THREE:
                clientStartMenu.chooseNumber();
                break;
            default:
                mainMenuConstantText.ifEnteredWrongValue();
                tostartworkslike();
                break;
        }
    }

    private void enterAsAdmin() {
        passwordInput.inputLogin();
        passwordInput.inputPassword();
        try {
            passwordLoginExtract.checkAdminLogin();
            passwordLoginExtract.checkAdminPassword();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (passwordInput.getLogin().compareTo(passwordLoginExtract.getLoginInProperties()) == 0 &&
                passwordInput.getPassword().compareTo(passwordLoginExtract.getPasswordInProperties()) == 0) {
            mainMenuConstantText.successfulVerificationMessage();
            roleInApp = "admin";
            roleLogWriter();
            reviewListReading.countNewReview();
            adminStartMenu.selectAdminMenu();
        } else {
            mainMenuConstantText.failedVerificationMessage();
            tostartworkslike();
        }
    }

    private void enterAsStaff() {
        passwordInput.inputLogin();
        passwordInput.inputPassword();
        try {
            passwordLoginExtract.checkUserLogin();
            passwordLoginExtract.checkUserPassword();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (passwordInput.getLogin().compareTo(passwordLoginExtract.getUserLoginInProperties()) == 0 &&
                passwordInput.getPassword().compareTo(passwordLoginExtract.getUserPasswordInProperties()) == 0) {
            mainMenuConstantText.successfulVerificationMessage();
            roleInApp = "user";
            roleLogWriter();
            reviewListReading.countNewReview();
            workerStartMenu.selectWorkerMenu();
        } else {
            mainMenuConstantText.failedVerificationMessage();
            tostartworkslike();
        }
    }
    private void roleLogWriter() {
        Navigation navigation = new Navigation();
        String registeredRoleList = "RegisteredRole.txt";
        BufferedWriter fileWriter = null;
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String lineReadyForWriting = "Дата входа в систему: " + dateTimeFormatter.format(localDate) + "; Статус: " + roleInApp + ";";
        try {
            fileWriter = new BufferedWriter(new FileWriter(registeredRoleList));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileWriter.write(lineReadyForWriting);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}