package menu;

import java.util.Scanner;

public class InputCodeParagraph {
    MainMenuConstantText mainMenuConstantText = new MainMenuConstantText();
    private ConstantButtonsInMenuClient inputCode;
    private String checkInputData;

    public ConstantButtonsInMenuClient getInputCode() {
        return inputCode;
    }

    public void setInputCode(ConstantButtonsInMenuClient inputCode) {
        this.inputCode = inputCode;
    }

    public void inputNumber() {
        Scanner in = new Scanner(System.in);
        inputCode = ConstantButtonsInMenuClient.getValue(in.nextInt());
       /* do {
            in.nextLine();
            mainMenuConstantText.ifEnteredWrongValue();
        }
        while (!in.hasNext("1|2|3|4|5|9|0"));
        inputCode = ConstantButtonsInMenuClient.getValue(in.nextInt());*/


    }
}
