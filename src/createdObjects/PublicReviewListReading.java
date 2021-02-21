package createdObjects;

import menu.MainMenuConstantText;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PublicReviewListReading {

    public void displayPublicReview() throws IOException {
        MainMenuConstantText mainMenuConstantText = new MainMenuConstantText();
        String regExReviewReplace = ("ё");
        String regExPublicReview = ("(?<=\\[).+");
        String regExPublicReviewRate = ("Оценка тура.+(?=\\])");
        String regExPublicReviewComment = ("\\*{3}Ответ.+$");
        String regExPublicReviewCommentSign = ("\\tС уважением.+\\*{3}");
        Pattern patternRewReplace = Pattern.compile(regExReviewReplace);
        Pattern patternPubReview = Pattern.compile(regExPublicReview);
        Pattern patternPubReviewRate = Pattern.compile(regExPublicReviewRate);
        Pattern patternPubReviewComment = Pattern.compile(regExPublicReviewComment);
        Pattern patternPubReviewCommentSign = Pattern.compile(regExPublicReviewCommentSign);
        String filename = "PublicReviewList.txt";
        String line = "", replaceString = "", pubReview = "", pubReviewRate = "";
        BufferedReader readText = new BufferedReader(new FileReader(filename));
        while ((line = readText.readLine()) != null) {
            Matcher matcher01 = patternRewReplace.matcher(line);
            replaceString = matcher01.replaceAll("е");
            Matcher matcher02 = patternPubReview.matcher(replaceString);
            Matcher matcher03 = patternPubReviewRate.matcher(replaceString);
            Matcher matcher04 = patternPubReviewComment.matcher(replaceString);
            Matcher matcher05 = patternPubReviewCommentSign.matcher(replaceString);
            while (matcher02.find()) {
                pubReview = matcher02.group();
            }
            while (matcher03.find()) {
                pubReviewRate = matcher03.group();
                StringBuilder stringBuilder = new StringBuilder(pubReview);
                stringBuilder.append("\n").append(pubReviewRate).append("\n");
                System.out.println(stringBuilder);
            }
            while (matcher04.find()) {
                String publicReviewComment = matcher04.group();
                System.out.println(publicReviewComment);
            }
            while (matcher05.find()) {
                String publicReviewCommentSign = matcher05.group();
                System.out.println(publicReviewCommentSign);
            }
        }
        mainMenuConstantText.writeLine();
    }
}
