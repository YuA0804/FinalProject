package createdObjects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrdersListReading {
    ArrayList<CreateNewOrder> listOrders = new ArrayList<>();
    String regExUser = ("(?<=\\{Пользователь\\:\\s).+(?=\\,\\sКонтактный телефон)");
    String regExUserTelNumber = ("(?<=\\Контактный телефон\\:\\s).+(?=\\,\\sАдрес)");
    String regExUserMail = ("(?<=\\Адрес эл.почты\\:\\s).+(?=\\,\\sПримечание)");
    String regExUserNote = ("(?<=\\Примечание\\:\\s).+(?=\\;\\})");
    String regExSymbolExchange = ("ё");
    Pattern patternUser = Pattern.compile(regExUser);
    Pattern patternTelNumber = Pattern.compile(regExUserTelNumber);
    Pattern patternMail = Pattern.compile(regExUserMail);
    Pattern patternNote = Pattern.compile(regExUserNote);
    Pattern patternSymbolExchange = Pattern.compile(regExSymbolExchange);
    String file = "OrdersList.txt";
    String replaceString;
    String line = "";
    String ordersAuthor, authorsTelNumber, authorsMail, authorsNote;
    BufferedReader readText = null;

    public ArrayList<CreateNewOrder> readOrdersListFile() throws Exception {
        listOrders.clear();
        readText = new BufferedReader(new FileReader(file));
        try {
            while ((line = readText.readLine()) != null) {
                Matcher matcher01 = patternSymbolExchange.matcher(line);
                replaceString = matcher01.replaceAll("е");
                Matcher matcher02 = patternUser.matcher(replaceString);
                Matcher matcher03 = patternTelNumber.matcher(replaceString);
                Matcher matcher04 = patternMail.matcher(replaceString);
                Matcher matcher05 = patternNote.matcher(replaceString);
                if (line.contains("{")) {
                    CreateNewOrder createNewOrder = new CreateNewOrder();
                    while (matcher02.find()) {
                        ordersAuthor = matcher02.group();
                        createNewOrder.setUsersNameToFeedBack(ordersAuthor);
                    }
                    while (matcher03.find()) {
                        authorsTelNumber = matcher03.group();
                        createNewOrder.setUsersTelNumberToFeedback(authorsTelNumber);
                    }
                    while (matcher04.find()) {
                        authorsMail = matcher04.group();
                        createNewOrder.setUsersMailToFeedback(authorsMail);
                    }
                    while (matcher05.find()) {
                        authorsNote = matcher05.group();
                        createNewOrder.setUsersNoteInFeedback(authorsNote);
                    }
                    listOrders.add(createNewOrder);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            readText.close();
        }
        return listOrders;
    }
}




