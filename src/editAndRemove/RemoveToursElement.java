package editAndRemove;

import createdObjects.ToursData;
import createdObjects.ToursListReading;
import menu.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class RemoveToursElement {
    MainMenuConstantText mainMenuConstantText= new MainMenuConstantText();
    ToursListReading toursListReading = new ToursListReading();
    ArrayList<ToursData> listTours = new ArrayList<>();
    String toursListAfterRemove = "ToursList.txt";
    BufferedWriter fileWriter;
    String editToursList;
    LocalDate localDate = LocalDate.now();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String dateOfEdit = "Список был изменён: ";
    public int indexForRemoveTour;

    public void removeTourBySelectedElement() {
        ToursAdminWorker toursAdminWorker = new ToursAdminWorker();
        try {
            listTours = toursListReading.readToursListFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (listTours.isEmpty()) {
            System.err.println("Список туров пуст.");
        } else {
            mainMenuConstantText.replaceElementByNumber();
            Scanner in = new Scanner(System.in);
            indexForRemoveTour = in.nextInt();
            while ((indexForRemoveTour >= listTours.size()) | (indexForRemoveTour < 0)) {
                mainMenuConstantText.ifEnteredWrongValue();
                mainMenuConstantText.replaceElementByNumber();
                indexForRemoveTour = in.nextInt();
            }
            System.out.println("Объект " + listTours.remove(indexForRemoveTour).getTourName() + " успешно удалён.");
            mainMenuConstantText.writeLine();
            StringBuilder stringBuilder = new StringBuilder(dateOfEdit);
            try {
                fileWriter = new BufferedWriter(new FileWriter(toursListAfterRemove));
                fileWriter.write(String.valueOf(stringBuilder.append(dateTimeFormatter.format(localDate))));
                fileWriter.newLine();
                for (ToursData resultList : listTours) {
                    editToursList = "{ID = " + resultList.getTourID() + "; tourName = " + resultList.getTourName() + "; tourDescription = " + resultList.getTourDescription() + "; tourPrices = " + resultList.getTourPrice() + "; departureDate = " + resultList.getTourDate() + ";}";
                    fileWriter.write(editToursList);
                    fileWriter.newLine();
                }
            } catch (IOException e) {

                e.printStackTrace();
            } finally {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        toursAdminWorker.toursAdminWorkerDisplay();
    }
}
