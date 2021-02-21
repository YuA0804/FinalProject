package menu;

public class MainMenuConstantText {
    ConstantButtonsInMenu constantButtonsInMenu = new ConstantButtonsInMenu();

    public void displayMenuConstantText() {
        System.out.println(constantButtonsInMenu.exit + " - Выход");
        System.out.println("Введите код,чтобы перейти в нужный Вам раздел.");
    }

    public void displayMenuConstantTextGoToMainMenu() {
        System.out.println(constantButtonsInMenu.backInMainMenu + " - Вернуться в Главное меню");
    }

    public void successfulVerificationMessage() {
        System.out.println("==========*****==========");
        System.out.println("Проверка пароля пройдена");
        System.out.println("==========*****==========");
    }

    public void failedVerificationMessage() {
        System.err.println("==========*****==========");
        System.err.println("Что-то пошло не так. ..");
        System.err.println("==========*****==========");
    }

    public void ifEnteredWrongValue() {
        System.err.println("Вы ввели неверное значение. Попробуйте ещё раз.");
    }

    public void ifEnteredWrongTypeOfValue() {
        System.err.println("Неверный формат данных. Попробуйте ещё раз.");
    }

    public void ifEnteredEmptyString() {
        System.err.println("Значение поля не может быть пустым. Попробуйте ещё раз.");
    }

    public void replaceElementByNumber() {
        System.out.println("Укажите порядковый номер объекта, который вы хотите удалить.");
    }

    public void editElementByNumber() {

        System.out.println("Укажите порядковый номер объекта, который вы хотите изменить.");
    }

    public void writeLine() {
        System.out.println("=====================================" + "\n");
    }

    public void wrongDateMessage() {
        System.err.println("Дата тура не может быть меньше текущей. Введите дату ещё раз.");
    }

    public void publicElementByNumber() {

        System.out.println("Укажите порядковый номер отзыва, который вы хотите опубликовать.");
    }
}