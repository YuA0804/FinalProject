package menu;

import createdObjects.CreateNewOrder;

public class ClientOrder {
    InputCodeParagraph inputCodeParagraph = new InputCodeParagraph();
    MainMenuConstantText mainMenuConstantText = new MainMenuConstantText();
    CreateNewOrder createNewOrder = new CreateNewOrder();
    ConstantButtonsInMenu constantButtonsInMenu = new ConstantButtonsInMenu();

    public void chooseOrderActivity() {
        Navigation navigation = new Navigation();
        ClientStartMenu clientStartMenu = new ClientStartMenu();
        createNewOrder.createNewUsersOrder();
        System.out.println("Заявка: " + createNewOrder.toString() + " принята.");
        createNewOrder.writeUsersOrderInFile();
        System.out.println("==============================================");
        System.out.println(constantButtonsInMenu.first + " - Создать новую заявку");
        mainMenuConstantText.displayMenuConstantTextGoToMainMenu();
        mainMenuConstantText.displayMenuConstantText();
        inputCodeParagraph.inputNumber();
        switch (inputCodeParagraph.getInputCode()) {
            case ONE:
                chooseOrderActivity();
                break;
            case NINE:
                clientStartMenu.chooseNumber();
                break;
            case ZERO:
                navigation.tostartworkslike();
                break;
            default:
                mainMenuConstantText.ifEnteredWrongValue();
                chooseOrderActivity();
        }
    }
}