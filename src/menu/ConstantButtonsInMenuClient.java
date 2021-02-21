package menu;

public enum ConstantButtonsInMenuClient {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    NINE(9),
    ZERO(0);
    private final int buttonsNumber;

    ConstantButtonsInMenuClient(int buttonsNumber) {
        this.buttonsNumber = buttonsNumber;
    }

    public static ConstantButtonsInMenuClient getValue(int i) {
        for (ConstantButtonsInMenuClient buttons : ConstantButtonsInMenuClient.values()) {
            if (buttons.buttonsNumber == i) {
                return buttons;
            }
        }
        return null;
    }

    public int getButtonsNumber() {
        return buttonsNumber;
    }
}

