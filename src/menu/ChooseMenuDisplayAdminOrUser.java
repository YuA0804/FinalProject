package menu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChooseMenuDisplayAdminOrUser {
    AdminStartMenu adminStartMenu = new AdminStartMenu();
    WorkerStartMenu workerStartMenu = new WorkerStartMenu();
    User user = new User();
    String readStatus = "";

    private String readStatusInApp() throws IOException {
        String regExStatusInApp = ("(?<=Статус\\:\\s)[A-Za-z]+(?=\\;)");
        Pattern patternStatusInApp = Pattern.compile(regExStatusInApp);
        String file = "RegisteredRole.txt";
        String line = "";

        BufferedReader readText = null;
        readText = new BufferedReader(new FileReader(file));
        while ((line = readText.readLine()) != null) {
            Matcher matcher01 = patternStatusInApp.matcher(line);
            while (matcher01.find()) {
                readStatus = matcher01.group();
            }
                   }
        readText.close();
        return readStatus;
    }

    public void chooseMenuDisplay() {
        try {
            readStatusInApp();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (readStatus.compareTo("admin") == 0) {
            adminStartMenu.selectAdminMenu();
        }
        if (readStatus.compareTo("user") == 0) {
            workerStartMenu.selectWorkerMenu();
        } else {
            System.out.println("Что-то идёт не так. (т.т)");
        }
    }

}
