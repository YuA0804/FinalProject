package createdObjects;

import menu.InputCodeParagraph;
import menu.MainMenuConstantText;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CreateNewReviewComment {
    private String commentTextForPublic;

    public String getCommentTextForPublic() {
        return commentTextForPublic;
    }

    public void setCommentTextForPublic(String commentTextForPublic) {
        this.commentTextForPublic = commentTextForPublic;
    }

    public void commentTextForPublic() {
        InputCodeParagraph inputCodeParagraph = new InputCodeParagraph();
        MainMenuConstantText mainMenuConstantText = new MainMenuConstantText();
        System.out.println("Введите текст комментария:");
        Scanner commentText = new Scanner(System.in);
        commentTextForPublic = commentText.nextLine();
        while (commentTextForPublic.isEmpty()) {
            mainMenuConstantText.ifEnteredEmptyString();
            commentTextForPublic = commentText.nextLine();
        }
        toString();
        publicReview();
    }

    @Override
    public String toString() {
        return "\t***Ответ: " + commentTextForPublic + "\n\tС уважением, Администрация агентства.\"***";
    }

    public void publicReview() {
        BufferedWriter fileWriter = null;
        String publicReviewList = "PublicReviewList.txt";
        try {
            fileWriter = new BufferedWriter(new FileWriter((publicReviewList), true));
            fileWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            fileWriter.write(toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Комментарий к отзыву публикован.");
    }
}
