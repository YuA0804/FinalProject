package createdObjects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReviewListReading {
    ArrayList<CreateNewReview> listReview = new ArrayList<>();
    String regExUserName = ("(?<=\\{).+(?=\\,\\sОценка)");
    String regExUserRate = ("(?<=Оценка\\:\\s)\\d{1,2}");
    String regExRevDescription = ("(?<=Отзыв\\:\\s).+(?=\\,\\sАдрес)");
    String regExUserMail = ("(?<=Адрес эл\\.почты\\:\\s).+(?=\\;)");
    String regExRevDate = ("(?<=отзыв получен\\:\\s).+(?=\\;\\})");
    String regExSymbolExchange = ("ё");
    Pattern patternUserName = Pattern.compile(regExUserName);
    Pattern patternUserRate = Pattern.compile(regExUserRate);
    Pattern patternRevDescription = Pattern.compile(regExRevDescription);
    Pattern patternUsersMail = Pattern.compile(regExUserMail);
    Pattern patternRevDate = Pattern.compile(regExRevDate);
    Pattern patternSymbolExchange = Pattern.compile(regExSymbolExchange);
    String file = "ReviewList.txt";
    String replaceString;
    String line = "";
    String reviewAuthor, reviewDescription, authorsRate, authorsEmail, dateOfCreate;
    int reviewRate;
    BufferedReader readText = null;
    int countNewReview;

    public ArrayList<CreateNewReview> readReviewListFile() throws Exception {
        listReview.clear();
        readText = new BufferedReader(new FileReader(file));
        try {
            while ((line = readText.readLine()) != null) {
                Matcher matcher01 = patternSymbolExchange.matcher(line);
                replaceString = matcher01.replaceAll("е");
                Matcher matcher02 = patternUserName.matcher(replaceString);
                Matcher matcher03 = patternUserRate.matcher(replaceString);
                Matcher matcher04 = patternRevDescription.matcher(replaceString);
                Matcher matcher05 = patternUsersMail.matcher(replaceString);
                Matcher matcher06 = patternRevDate.matcher(replaceString);
                if (line.contains("{")) {
                    CreateNewReview createNewReview = new CreateNewReview();
                    while (matcher02.find()) {
                        reviewAuthor = matcher02.group();
                        createNewReview.setReviewAuthorsName(reviewAuthor);
                    }
                    while (matcher03.find()) {
                        authorsRate = matcher03.group();
                        reviewRate = Integer.parseInt(authorsRate);
                        createNewReview.setReviewRate(reviewRate);
                    }
                    while (matcher04.find()) {
                        reviewDescription = matcher04.group();
                        createNewReview.setReviewDescription(reviewDescription);
                    }
                    while (matcher05.find()) {
                        authorsEmail = matcher05.group();
                        createNewReview.setReviewAuthorsEmail(authorsEmail);
                    }
                    listReview.add(createNewReview);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            readText.close();
        }
        return listReview;
    }

    public void displayNewReview() {
        try {
            readReviewListFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (listReview.isEmpty()) {
            System.out.println("Список отзывов пользователей пуст");
        } else {
            for (CreateNewReview resultReviewList : listReview) {
                System.out.println(listReview.indexOf(resultReviewList) + " " + resultReviewList);
            }
        }
    }
    public int countNewReview() {
        try {
            readReviewListFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (CreateNewReview resultReviewList : listReview) {
            countNewReview = countNewReview + 1;
        }
        if (countNewReview>0) {
            System.err.println("Получено новых отзывов: " + countNewReview + ".  Перейдите в раздел Отзывы пользователей,чтобы прочитать сообщение.");
        }
        return countNewReview;
    }
}
