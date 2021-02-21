package menu;

import createdObjects.CreateNewTour;
import createdObjects.ToursData;
import createdObjects.ToursListReading;
import editAndRemove.EditToursElement;
import editAndRemove.RemoveToursElement;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ToursAdminWorker {
    InputCodeParagraph inputCodeParagraph = new InputCodeParagraph();
    MainMenuConstantText mainMenuConstantText = new MainMenuConstantText();
    ConstantButtonsInMenu constantButtonsInMenu = new ConstantButtonsInMenu();
    CreateNewTour createNewTour = new CreateNewTour();
    ToursListReading toursListReading = new ToursListReading();
    RemoveToursElement removeToursElement = new RemoveToursElement();
    EditToursElement editToursElement = new EditToursElement();
    ArrayList<ToursData> listTours = new ArrayList<>();
    BufferedWriter fileWriter;
    String toursListAfterAdd = "ToursList.txt";
    String addToursList;
    int id;
    LocalDate localDate = LocalDate.now();
    LocalDate newDate;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void toursAdminWorkerDisplay() {
        WorkerStartMenu workerStartMenu = new WorkerStartMenu();
        try {
            toursListReading.findToursActualTours();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainMenuConstantText.writeLine();
        System.out.println(constantButtonsInMenu.first + " - Добавить тур");
        System.out.println(constantButtonsInMenu.second + " - Поиск тура по стоимости");
        System.out.println(constantButtonsInMenu.third + " - Редактировать данные о туре");
        System.out.println(constantButtonsInMenu.fourth + " - Удалить тур");
        System.out.println(constantButtonsInMenu.fifth + " - Архив туров");
        mainMenuConstantText.displayMenuConstantTextGoToMainMenu();
        mainMenuConstantText.displayMenuConstantText();
        inputCodeParagraph.inputNumber();
        Navigation navigation = new Navigation();
        ChooseMenuDisplayAdminOrUser chooseMenuDisplayAdminOrUser = new ChooseMenuDisplayAdminOrUser();

        switch (inputCodeParagraph.getInputCode()) {
            case ONE:
                addNewTour();
                toursAdminWorkerDisplay();
                break;
            case TWO:
                try {
                    toursListReading.findToursByDateAndPrice();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                toursAdminWorkerDisplay();
                break;
            case THREE:
                editToursElement.editTourBySelectedElement();
                break;
            case FOUR:
                removeToursElement.removeTourBySelectedElement();
                break;
            case FIVE:
                try {
                    toursListReading.findArchiveTours();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                toursAdminWorkerDisplay();
                break;
            case NINE:
                chooseMenuDisplayAdminOrUser.chooseMenuDisplay();
                break;
            case ZERO:
                navigation.tostartworkslike();
                break;
            default:
                mainMenuConstantText.ifEnteredWrongValue();
                toursAdminWorkerDisplay();
        }
    }

    private void addNewTour() {
        System.out.println("Добавьте ID нового тура: ");
        id = createNewTour.createID();
        for (ToursData resultTourList : listTours) {
            while (id <= 0) {
                System.err.println("Значение ID должно быть больше 0. Пожалуйста, введите новое значение.");
                id = createNewTour.createID();
            }
            while (id == resultTourList.getTourID()) {
                System.err.println("Значение ID не уникально. Пожалуйста, введите новое значение.");
                id = createNewTour.createID();
            }
        }
        System.out.println("Укажите название нового тура: ");
        String name = createNewTour.createName();
        System.out.println("Добавьте описание нового тура: ");
        String descr = createNewTour.createDescription();
        System.out.println("Укажите стоимость тура, руб/чел: ");
        Double price = createNewTour.createPrice();
        System.out.println("Укажите дату отправления в формате дд/мм/гггг: ");
        String date = createNewTour.createDate();
        newDate = LocalDate.parse(date, dateTimeFormatter);
        while (newDate.isBefore(localDate)) {
            mainMenuConstantText.wrongDateMessage();
            date = createNewTour.createDate();
            newDate = LocalDate.parse(date, dateTimeFormatter);
        }
        listTours.add(new ToursData(id, name, descr, price, date));
        System.out.println("\n" + "Тур " + name + " успешно создан." + "\n");
        try {
            fileWriter = new BufferedWriter(new FileWriter((toursListAfterAdd), true));
            fileWriter.newLine();
            addToursList = "{ID = " + listTours.get(listTours.size() - 1).getTourID() + "; tourName = " + listTours.get(listTours.size() - 1).getTourName() + "; tourDescription = " + listTours.get(listTours.size() - 1).getTourDescription() + "; tourPrices = " + listTours.get(listTours.size() - 1).getTourPrice() + "; departureDate = " + listTours.get(listTours.size() - 1).getTourDate() + ";}";
            fileWriter.write(addToursList);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            // return listTours;
        }
    }
}
