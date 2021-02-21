package menu;

public class AdminStartMenu {
    InputCodeParagraph inputCodeParagraph = new InputCodeParagraph();
    MainMenuConstantText mainMenuConstantText = new MainMenuConstantText();
    MainMenuTextWorker mainMenuTextWorker = new MainMenuTextWorker();
    UsersInfoNavigation usersInfoNavigation = new UsersInfoNavigation();
    ToursAdminWorker toursAdminWorker = new ToursAdminWorker();
    ClientReviewAdminWorkers clientReviewAdminWorkers = new ClientReviewAdminWorkers();
    ClientOrderAdminWorker clientOrderAdminWorker = new ClientOrderAdminWorker();
    InfoMenu infoMenu = new InfoMenu();

    public void selectAdminMenu() {
        mainMenuTextWorker.mainMenuTextForAll();
        mainMenuTextWorker.mainMenuTextForAdmin();
        mainMenuTextWorker.mainMenuTextForWorkerAdmin();
        mainMenuConstantText.displayMenuConstantText();
        inputCodeParagraph.inputNumber();
        Navigation navigation= new Navigation();
        switch (inputCodeParagraph.getInputCode()) {
            case ONE:
                toursAdminWorker.toursAdminWorkerDisplay();
                break;
            case TWO:
                clientReviewAdminWorkers.chooseReviewActivity();
                break;
            case THREE:
                clientOrderAdminWorker.chooseOrderActivity();
                break;
            case FOUR:
                usersInfoNavigation.selectUserMenu();
                break;
            case FIVE:
                infoMenu.chooseInfoActivity();
                break;
           case ZERO:
                navigation.tostartworkslike();
                break;
            default:
                break;
        }
    }
}
