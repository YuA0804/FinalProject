package menu;

public class
WorkerStartMenu {
    InputCodeParagraph inputCodeParagraph = new InputCodeParagraph();
    MainMenuConstantText mainMenuConstantText= new MainMenuConstantText();
    MainMenuTextWorker mainMenuTextWorker = new MainMenuTextWorker();
    ToursAdminWorker toursAdminWorker = new ToursAdminWorker();
    ClientReviewAdminWorkers clientReviewAdminWorkers = new ClientReviewAdminWorkers();
    ClientOrderAdminWorker clientOrderAdminWorker = new ClientOrderAdminWorker();
    InfoMenu infoMenu = new InfoMenu();

    public void selectWorkerMenu() {
        Navigation navigation = new Navigation();
        mainMenuTextWorker.mainMenuTextForAll();
        mainMenuTextWorker.mainMenuTextForWorkerAdmin();
        mainMenuConstantText.displayMenuConstantText();
        inputCodeParagraph.inputNumber();

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
