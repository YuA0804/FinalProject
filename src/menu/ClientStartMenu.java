package menu;

import createdObjects.CompanyInfoReading;

import java.io.IOException;

public class ClientStartMenu {
    MainMenuText mainMenuText = new MainMenuText();
    MainMenuConstantText mainMenuConstantText = new MainMenuConstantText();
    InputCodeParagraph inputCodeParagraph = new InputCodeParagraph();
    ClientReview clientReview = new ClientReview();
    ClientOrder clientOrder = new ClientOrder();
    CompanyInfoReading companyInfoReading = new CompanyInfoReading();

    public void chooseNumber() {
        ToursClient toursClient = new ToursClient();
        mainMenuText.mainMenuTextForAll();
        System.out.println("4 - Информация об организации");
        mainMenuConstantText.displayMenuConstantText();
        inputCodeParagraph.inputNumber();
        Navigation navigation = new Navigation();
        switch (inputCodeParagraph.getInputCode()) {
            case ONE:
                toursClient.toursClientDisplay();
                break;
            case TWO:
                clientReview.chooseReviewActivity();
                break;
            case THREE:
                clientOrder.chooseOrderActivity();
                break;
            case FOUR:
                try {
                    companyInfoReading.readCompanyInfoFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                chooseNumber();
                break;
            case ZERO:
                navigation.tostartworkslike();
                break;
            default:
                mainMenuConstantText.ifEnteredWrongValue();
                chooseNumber();
        }
    }
}
