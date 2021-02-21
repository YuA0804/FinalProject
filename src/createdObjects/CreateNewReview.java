package createdObjects;

import menu.MainMenuConstantText;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CreateNewReview {
    MainMenuConstantText mainMenuConstantText = new MainMenuConstantText();
    private String reviewAuthorsName;
    private String reviewDescription;
    private int reviewRate;
    private String reviewAuthorsEmail;

    public String getReviewAuthorsName() {
        return reviewAuthorsName;
    }

    public void setReviewAuthorsName(String reviewAuthorsName) {
        this.reviewAuthorsName = reviewAuthorsName;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }

    public int getReviewRate() {
        return reviewRate;
    }

    public void setReviewRate(int reviewRate) {
        this.reviewRate = reviewRate;
    }

    public String getReviewAuthorsEmail() {
        return reviewAuthorsEmail;
    }

    public void setReviewAuthorsEmail(String reviewAuthorsEmail) {
        this.reviewAuthorsEmail = reviewAuthorsEmail;
    }

    public String createNewUsersReview() {
        Scanner usersName = new Scanner(System.in);
        System.out.println("Укажите Ваше имя:");
        reviewAuthorsName = usersName.nextLine();
        while (reviewAuthorsName.isEmpty()) {
            mainMenuConstantText.ifEnteredEmptyString();
            reviewAuthorsName = usersName.nextLine();
        }
        Scanner revDesc = new Scanner(System.in);
        System.out.println("Введите текст отзыва:");
        reviewDescription = revDesc.nextLine();
        while (reviewDescription.isEmpty()) {
            mainMenuConstantText.ifEnteredEmptyString();
            reviewDescription = revDesc.nextLine();
        }
        Scanner revRate = new Scanner(System.in);
        do {
            System.out.println("Оцените наш тур (по шкале от 1 до 10 (1 - плохо, 10 - восхитительно):");
            while (!revRate.hasNextInt()) {
                mainMenuConstantText.ifEnteredWrongTypeOfValue();
                revRate.next();
            }
            reviewRate = revRate.nextInt();
        }
        while (reviewRate <= 0 || reviewRate > 10);
        Scanner usersMail = new Scanner(System.in);
        System.out.println("Укажите адрес электронной почты:");
        reviewAuthorsEmail = usersMail.nextLine();
        while (reviewAuthorsEmail.isEmpty()) {
            mainMenuConstantText.ifEnteredEmptyString();
            reviewAuthorsEmail = usersMail.nextLine();
        }

        return getReviewAuthorsName() + reviewDescription + reviewRate + reviewAuthorsEmail;
    }

    @Override
    public String toString() {
        return reviewAuthorsName + ", Оценка: " + reviewRate + ", Отзыв: " + reviewDescription +
                ", Адрес эл.почты: " + reviewAuthorsEmail;
    }

    public void writeUsersReviewInFile() {
        BufferedWriter fileWriter = null;
        String editOrdersList = "ReviewList.txt";
        try {
            fileWriter = new BufferedWriter(new FileWriter((editOrdersList), true));
            fileWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String dateOfEdit = "{" + toString() + ";}";
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
