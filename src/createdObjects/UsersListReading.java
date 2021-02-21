package createdObjects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsersListReading {
    ArrayList<UsersData> listUsers = new ArrayList<>();
    String regExLogin = ("(?<=userLogin\\s\\=\\s)[A-Za-z0-9-_]+");
    String regExUserName = ("(?<=userName\\s\\=\\s)[A-Za-zА-Яа-я0-9-'\\s]+");
    String regExStatus = ("(?<=status\\s\\=\\s)[A-Za-z]+");
    Pattern patternLogin = Pattern.compile(regExLogin);
    Pattern patternUserName = Pattern.compile(regExUserName);
    Pattern patternStatus = Pattern.compile(regExStatus);
    String filename = "UsersList.txt";
    String line = "";
    String log;
    String usName;
    String st;

    public ArrayList<UsersData> readUsersListFile() throws IOException {
        listUsers.clear();
        BufferedReader readText = new BufferedReader(new FileReader(filename));
        try {
            while ((line = readText.readLine()) != null) {
                if (line.startsWith("{")) {
                    //UsersData usersData = new UsersData();
                    Matcher matcher00 = patternLogin.matcher(line);
                    Matcher matcher01 = patternUserName.matcher(line);
                    Matcher matcher02 = patternStatus.matcher(line);
                    while (matcher00.find()) {
                        log = matcher00.group();
                    }
                    while (matcher01.find()) {
                        usName = matcher01.group();
                    }
                    while (matcher02.find()) {
                        st = matcher02.group();
                        // usersData.setStatus(st);
                    }
                    //listUsers.add(usersData);
                    listUsers.add(new UsersData(log, usName, st));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            readText.close();
        }
        return listUsers;
    }
}
