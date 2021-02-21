package menu;

public class MainMenuText {
    ConstantButtonsInMenu constantButtonsInMenu = new ConstantButtonsInMenu();

    public void mainMenuTextForAll() {
        System.out.println(constantButtonsInMenu.first + " - Туры");
        System.out.println(constantButtonsInMenu.second + " - Отзывы пользователей");
        System.out.println(constantButtonsInMenu.third + " - Заявки");
    }


}
