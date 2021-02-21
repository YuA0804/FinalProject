package menu;

import createdObjects.CreateNewOrder;
import createdObjects.OrdersListReading;

import java.util.ArrayList;

public class ClientOrderAdminWorker {
    InputCodeParagraph inputCodeParagraph = new InputCodeParagraph();
    MainMenuConstantText mainMenuConstantText = new MainMenuConstantText();
    OrdersListReading ordersListReading = new OrdersListReading();
    ArrayList<CreateNewOrder> listOrders = new ArrayList<>();

    public void chooseOrderActivity() {
        Navigation navigation = new Navigation();
        ChooseMenuDisplayAdminOrUser chooseMenuDisplayAdminOrUser = new ChooseMenuDisplayAdminOrUser();
        try {
            listOrders = ordersListReading.readOrdersListFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (CreateNewOrder resultOrdersList : listOrders) {
            System.out.println(resultOrdersList);
        }
        System.out.println("==============================================");

        mainMenuConstantText.displayMenuConstantTextGoToMainMenu();
        mainMenuConstantText.displayMenuConstantText();
        inputCodeParagraph.inputNumber();

        switch (inputCodeParagraph.getInputCode()) {
            case NINE:
                chooseMenuDisplayAdminOrUser.chooseMenuDisplay();
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
