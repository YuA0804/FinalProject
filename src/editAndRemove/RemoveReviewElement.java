package editAndRemove;

import createdObjects.CreateNewReview;
import createdObjects.ReviewListReading;
import menu.ClientReviewAdminWorkers;
import menu.MainMenuConstantText;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class RemoveReviewElement {
    ReviewListReading reviewListReading = new ReviewListReading();
    ArrayList<CreateNewReview> listReview = new ArrayList<>();
    MainMenuConstantText mainMenuConstantText= new MainMenuConstantText();
    int indexForRemoveReview;
    String reviewListAfterRemove = "ReviewList.txt";
    BufferedWriter fileWriter;

    public void removeReviewBySelectedElement() {
        ClientReviewAdminWorkers clientReviewAdminWorkers = new ClientReviewAdminWorkers();
        try {
            listReview = reviewListReading.readReviewListFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (listReview.isEmpty()) {
            System.err.println("Список отзывов пользователей пуст." + "\n");
        } else {
            mainMenuConstantText.replaceElementByNumber();
            Scanner in = new Scanner(System.in);
            indexForRemoveReview = in.nextInt();

            while ((indexForRemoveReview >= listReview.size()) || (indexForRemoveReview < 0)) {
                mainMenuConstantText.ifEnteredWrongValue();
                mainMenuConstantText.replaceElementByNumber();
                indexForRemoveReview = in.nextInt();
            }
            System.out.println("Отзыв " + listReview.remove(indexForRemoveReview).getReviewAuthorsName() + " успешно удалён.");
            mainMenuConstantText.writeLine();
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
        }
        clientReviewAdminWorkers.chooseReviewActivity();
    }
}
