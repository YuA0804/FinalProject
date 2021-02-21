package createdObjects;

import menu.ClientReviewAdminWorkers;
import menu.MainMenuConstantText;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ReviewPublishing {
    ReviewListReading reviewListReading = new ReviewListReading();
    ArrayList<CreateNewReview> listReview = new ArrayList<>();
    MainMenuConstantText mainMenuConstantText = new MainMenuConstantText();
    int indexForPublicReview;
    String reviewListAfterRemove = "ReviewList.txt";
    String publicReviewList = "PublicReviewList.txt";
    BufferedWriter fileWriter;
    String reviewElementFotPublishing = "[", line = "";
    String reviewFinalString;
    LocalDate localDate = LocalDate.now();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    CreateNewReviewComment createNewReviewComment= new CreateNewReviewComment();

    public void publicReviewBySelectedElement() {
        ClientReviewAdminWorkers clientReviewAdminWorkers = new ClientReviewAdminWorkers();
        try {
            listReview = reviewListReading.readReviewListFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (listReview.isEmpty()) {
            System.err.println("Список отзывов пользователей пуст.");
        } else {
            mainMenuConstantText.publicElementByNumber();
            Scanner in = new Scanner(System.in);
            indexForPublicReview = in.nextInt();
            while ((indexForPublicReview >= listReview.size()) | (indexForPublicReview < 0)) {
                mainMenuConstantText.ifEnteredWrongValue();
                mainMenuConstantText.publicElementByNumber();
                indexForPublicReview = in.nextInt();
            }
            StringBuilder stringBuilder = new StringBuilder(reviewElementFotPublishing);
            reviewFinalString = String.valueOf(stringBuilder.append(dateTimeFormatter.format(localDate)).append(", ").append(listReview.get(indexForPublicReview).getReviewAuthorsName()).append(": \"").append(listReview.get(indexForPublicReview).getReviewDescription()).append("\".\nОценка тура: ").append(listReview.get(indexForPublicReview).getReviewRate()).append("*.]"));
            try {
                fileWriter = new BufferedWriter(new FileWriter((publicReviewList), true));
                fileWriter.newLine();
                fileWriter.write(reviewFinalString);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Отзыв " + listReview.remove(indexForPublicReview).getReviewAuthorsName() + " опубликован.");
            try {
                fileWriter = new BufferedWriter(new FileWriter(reviewListAfterRemove));
                for (CreateNewReview resultList : listReview) {
                    String removedElement = "{" + resultList.getReviewAuthorsName() + ", Оценка: " + resultList.getReviewRate() + ", Отзыв: " + resultList.getReviewDescription() + ", Адрес эл.почты: " + resultList.getReviewAuthorsEmail() + ";}";
                    fileWriter.write(removedElement);
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
            clientReviewAdminWorkers.suggestAddComment();
        }
        mainMenuConstantText.writeLine();
        clientReviewAdminWorkers.chooseReviewActivity();
    }
}

