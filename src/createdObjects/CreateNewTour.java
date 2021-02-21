package createdObjects;

import menu.MainMenuConstantText;

import java.util.Scanner;

public class CreateNewTour {
    private String toursNewName;
    private String toursNewDescription;
    private int toursNewID;
    private double toursNewPrice;
    private String toursNewDate;
    MainMenuConstantText mainMenuConstantText = new MainMenuConstantText();

    public String getToursNewDescription() {
        return toursNewDescription;
    }

    public void setToursNewDescription(String toursNewDescription) {
        this.toursNewDescription = toursNewDescription;
    }

    public int getToursNewID() {
        return toursNewID;
    }

    public void setToursNewID(int toursNewID) {
        this.toursNewID = toursNewID;
    }

    public double getToursNewPrice() {
        return toursNewPrice;
    }

    public void setToursNewPrice(double toursNewPrice) {
        this.toursNewPrice = toursNewPrice;
    }

    public String getToursNewDate() {
        return toursNewDate;
    }

    public void setToursNewDate(String toursNewDate) {
        this.toursNewDate = toursNewDate;
    }

    public int createID() {
        Scanner tourID = new Scanner(System.in);
        return toursNewID = tourID.nextInt();
    }

    public String createName() {
        Scanner tourName = new Scanner(System.in);
        toursNewName = tourName.nextLine();
        while (toursNewName.isEmpty()) {
            mainMenuConstantText.ifEnteredEmptyString();
            toursNewName = tourName.nextLine();
        }
        return toursNewName;
    }

    public String createDescription() {
        Scanner tourDescription = new Scanner(System.in);
        toursNewDescription = tourDescription.nextLine();
        while (toursNewDescription.isEmpty()) {
            mainMenuConstantText.ifEnteredEmptyString();
            toursNewDescription = tourDescription.nextLine();
        }
        return toursNewDescription;
    }

    public Double createPrice() {
        Scanner tourPrice = new Scanner(System.in);
        return toursNewPrice = tourPrice.nextDouble();
    }

    public String createDate() {
        Scanner tourDate = new Scanner(System.in);
        toursNewDate = tourDate.nextLine();
        while (toursNewDate.isEmpty()) {
            mainMenuConstantText.ifEnteredEmptyString();
            toursNewDate = tourDate.nextLine();
        }
        return toursNewDate;
    }
}




