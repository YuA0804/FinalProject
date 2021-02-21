package menu;

import createdObjects.*;
import editAndRemove.RemoveReviewElement;

import java.io.IOException;

public class ClientReviewAdminWorkers {
    InputCodeParagraph inputCodeParagraph = new InputCodeParagraph();
    ConstantButtonsInMenu constantButtonsInMenu = new ConstantButtonsInMenu();
    MainMenuConstantText mainMenuConstantText = new MainMenuConstantText();
    RemoveReviewElement removeReviewElement = new RemoveReviewElement();
    ReviewPublishing reviewPublishing = new ReviewPublishing();
    PublicReviewListReading publicReviewListReading = new PublicReviewListReading();
    CreateNewReviewComment createNewReviewComment = new CreateNewReviewComment();

    public void chooseReviewActivity() {
        Navigation navigation = new Navigation();
        WorkerStartMenu workerStartMenu = new WorkerStartMenu();
        ChooseMenuDisplayAdminOrUser chooseMenuDisplayAdminOrUser = new ChooseMenuDisplayAdminOrUser();
        ReviewListReading reviewListReading = new ReviewListReading();
        System.out.println("ПОЛУЧЕНЫ НОВЫЕ ОТЗЫВЫ:");
        try {
            reviewListReading.displayNewReview();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mainMenuConstantText.writeLine();
        System.out.println(constantButtonsInMenu.first + " - Опубликовать полученный отзыв");
        System.out.println(constantButtonsInMenu.second + " - Удалить отзыв");
        System.out.println(constantButtonsInMenu.third + " - Посмотреть список опубликованных отзывов");
        mainMenuConstantText.displayMenuConstantTextGoToMainMenu();
        mainMenuConstantText.displayMenuConstantText();
        inputCodeParagraph.inputNumber();
        switch (inputCodeParagraph.getInputCode()) {
            case ONE:
                reviewPublishing.publicReviewBySelectedElement();
                break;
            case TWO:
                removeReviewElement.removeReviewBySelectedElement();
                break;
            case THREE:
                try {
                    publicReviewListReading.displayPublicReview();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                chooseReviewActivity();
                break;
            case NINE:
                chooseMenuDisplayAdminOrUser.chooseMenuDisplay();
                break;
            case ZERO:
                navigation.tostartworkslike();
                break;
            default:
                mainMenuConstantText.ifEnteredWrongValue();
                chooseReviewActivity();
        }
    }

    public void suggestAddComment() {
        System.out.println("Добавить комментарий к публикуемому отзыву?");
        System.out.println(constantButtonsInMenu.first + " - Да");
        System.out.println(constantButtonsInMenu.second + " - Нет");
        inputCodeParagraph.inputNumber();
        switch (inputCodeParagraph.getInputCode()) {
            case ONE:
                createNewReviewComment.commentTextForPublic();
                chooseReviewActivity();
                break;
            case TWO:
                chooseReviewActivity();
                break;
            default:
                mainMenuConstantText.ifEnteredWrongValue();
                chooseReviewActivity();
                break;
        }
    }
}
