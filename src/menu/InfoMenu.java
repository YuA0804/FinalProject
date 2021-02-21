package menu;

import createdObjects.CompanyInfoReading;
import createdObjects.ExchangeRates;

import java.io.IOException;

public class InfoMenu {
    InputCodeParagraph inputCodeParagraph = new InputCodeParagraph();
    ConstantButtonsInMenu constantButtonsInMenu = new ConstantButtonsInMenu();
    MainMenuConstantText mainMenuConstantText = new MainMenuConstantText();
    ExchangeRates exchangeRates = new ExchangeRates();
    CompanyInfoReading companyInfoReading = new CompanyInfoReading();
    User user = new User();

    public void chooseInfoActivity() {
        ChooseMenuDisplayAdminOrUser chooseMenuDisplayAdminOrUser = new ChooseMenuDisplayAdminOrUser();
        Navigation navigation = new Navigation();
        System.out.println(constantButtonsInMenu.first + " - Информация об организации");
        System.out.println(constantButtonsInMenu.second + " - Просмотреть текущий курс валют");
        mainMenuConstantText.displayMenuConstantTextGoToMainMenu();
        mainMenuConstantText.displayMenuConstantText();
        inputCodeParagraph.inputNumber();
        switch (inputCodeParagraph.getInputCode()) {
            case ONE:
                try {
                    companyInfoReading.readCompanyInfoFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                chooseInfoActivity();
                break;
            case TWO:
                try {
                    exchangeRates.findActualRates();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                exchangeRates.displayActualExchangeRates();
                chooseInfoActivity();
                break;
            case NINE:
                chooseMenuDisplayAdminOrUser.chooseMenuDisplay();
                break;
            case ZERO:
                navigation.tostartworkslike();
                break;
            default:
                mainMenuConstantText.ifEnteredWrongValue();
                chooseInfoActivity();
                break;
        }
    }
}
