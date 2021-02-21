package menu;

import createdObjects.CreateNewOrder;
import createdObjects.ToursListReading;

import java.io.IOException;

public class ToursClient {

    public void toursClientDisplay() {
        CreateNewOrder createNewOrder = new CreateNewOrder();
        InputCodeParagraph inputCodeParagraph = new InputCodeParagraph();
        MainMenuConstantText mainMenuConstantText = new MainMenuConstantText();
        ConstantButtonsInMenu constantButtonsInMenu = new ConstantButtonsInMenu();
        ClientStartMenu clientStartMenu = new ClientStartMenu();
        Navigation navigation = new Navigation();
        ToursListReading toursListReading = new ToursListReading();
        try {
            toursListReading.findToursActualTours();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainMenuConstantText.writeLine();
        System.out.println(constantButtonsInMenu.first + " - Поиск тура по стоимости");
        System.out.println(constantButtonsInMenu.third + " - Создать новую заявку на тур");
        mainMenuConstantText.displayMenuConstantTextGoToMainMenu();
        mainMenuConstantText.displayMenuConstantText();
        inputCodeParagraph.inputNumber();

        switch (inputCodeParagraph.getInputCode()) {
            case ONE:
                try {
                    toursListReading.findToursByDateAndPrice();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                toursClientDisplay();
                break;
            case TWO:
                createNewOrder.createNewUsersOrder();
                System.out.println("Заявка: " + createNewOrder.toString() + " принята.");
                createNewOrder.writeUsersOrderInFile();
                toursClientDisplay();
                break;
            case NINE:
                clientStartMenu.chooseNumber();
                break;
            case ZERO:
                navigation.tostartworkslike();
                break;
            default:
                mainMenuConstantText.ifEnteredWrongValue();
                toursClientDisplay();
        }
    }
}


