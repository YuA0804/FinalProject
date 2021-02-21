package menu;

import createdObjects.CreateNewReview;
import createdObjects.PublicReviewListReading;

import java.io.IOException;

public class ClientReview {
    InputCodeParagraph inputCodeParagraph = new InputCodeParagraph();
    ConstantButtonsInMenu constantButtonsInMenu = new ConstantButtonsInMenu();
    MainMenuConstantText mainMenuConstantText = new MainMenuConstantText();

    public void chooseReviewActivity() {
        ClientStartMenu clientStartMenu = new ClientStartMenu();
        Navigation navigation = new Navigation();
        System.out.println(constantButtonsInMenu.first + " - Просмотреть список отзывов");
        System.out.println(constantButtonsInMenu.second + " - Добавить отзыв");
        mainMenuConstantText.displayMenuConstantTextGoToMainMenu();
        mainMenuConstantText.displayMenuConstantText();
        CreateNewReview createNewReview = new CreateNewReview();
        inputCodeParagraph.inputNumber();
        PublicReviewListReading publicReviewListReading=new PublicReviewListReading();
        switch (inputCodeParagraph.getInputCode()) {
            case ONE:
                try {
                    publicReviewListReading.displayPublicReview();
                } catch (IOException e) {
                    e.printStackTrace();
                }
               chooseReviewActivity();
                break;
            case TWO:
                createNewReview.createNewUsersReview();
                createNewReview.writeUsersReviewInFile();
                System.out.println("Отзыв " + createNewReview.toString() + " отправлен. Спасибо, что воспользовались услугами нашего агентства!");
                chooseReviewActivity();
                break;
            case NINE:
                clientStartMenu.chooseNumber();
                break;
            case ZERO:
                navigation.tostartworkslike();
                break;
            default:
                mainMenuConstantText.ifEnteredWrongValue();
                chooseReviewActivity();
        }
    }
}