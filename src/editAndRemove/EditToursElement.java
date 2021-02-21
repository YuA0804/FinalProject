package editAndRemove;

import createdObjects.ToursListReading;
import menu.MainMenuConstantText;
import menu.ToursAdminWorker;
import createdObjects.ToursData;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditToursElement {
    MainMenuConstantText mainMenuConstantText = new MainMenuConstantText();
    ArrayList<ToursData> listTours = new ArrayList<>();
    ToursListReading toursListReading = new ToursListReading();
    public int indexForEditTour;
    private String toursNewDate;
    String verifiedToursDate;
    Scanner newValue = new Scanner(System.in);
    Scanner newValueDesc = new Scanner(System.in);
    Scanner newValuePrice = new Scanner(System.in);
    LocalDate localToursDate;
    LocalDate localDateToday = LocalDate.now();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    BufferedWriter fileWriter;
    String toursListAfterEdit = "ToursList.txt";
    String dateOfEdit = "Список был изменён: ";
    String editToursList;
    double toursNewPrice;
    public void editTourBySelectedElement() {
        ToursAdminWorker toursAdminWorker = new ToursAdminWorker();
        try {
            listTours = toursListReading.readToursListFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (listTours.isEmpty()) {
            System.err.println("Список туров пуст.");
        } else {
            mainMenuConstantText.editElementByNumber();
            Scanner in = new Scanner(System.in);
            indexForEditTour = in.nextInt();
            while (indexForEditTour >= listTours.size() | indexForEditTour < 0) {
                mainMenuConstantText.ifEnteredWrongValue();
                mainMenuConstantText.editElementByNumber();
                indexForEditTour = in.nextInt();
            }
            System.out.println("Укажите новое название тура:");
            String toursNewName = newValue.nextLine();
            while (toursNewName.isEmpty()) {
                mainMenuConstantText.ifEnteredEmptyString();
                toursNewName = newValue.nextLine();
            }
            System.out.println("Введите новое описание тура:");
            String toursNewDescription = newValueDesc.nextLine();
            while (toursNewDescription.isEmpty()) {
                mainMenuConstantText.ifEnteredEmptyString();
                toursNewDescription = newValueDesc.nextLine();
            }
            do {
                System.out.println("Укажите новую стоимость тура:");
                while (!newValuePrice.hasNextDouble()) {
                    mainMenuConstantText.ifEnteredWrongTypeOfValue();
                    newValuePrice.next();
                }
                toursNewPrice = newValuePrice.nextDouble();
            }
            while ( toursNewPrice<=0);
            System.out.println("Укажите новую дату отправления в формате дд/мм/гггг:");
            toursNewDate = newValue.nextLine();
            getVerifiedToursDate(toursNewDate);
            listTours.get(indexForEditTour).setTourName(toursNewName);
            listTours.get(indexForEditTour).setTourDescription(toursNewDescription);
            listTours.get(indexForEditTour).setTourPrice(toursNewPrice);
            listTours.get(indexForEditTour).setTourDate(verifiedToursDate);
            System.out.println("Тур " + listTours.get(indexForEditTour).getTourName() + " успешно изменён.");
            mainMenuConstantText.writeLine();
            StringBuilder stringBuilder = new StringBuilder(dateOfEdit);
            try {
                fileWriter = new BufferedWriter(new FileWriter(toursListAfterEdit));
                fileWriter.write(String.valueOf(stringBuilder.append(dateTimeFormatter.format(localDateToday))));
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

    public String getVerifiedToursDate(String toursNewDate) {
        String regExEditDate = ("^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
        Pattern patternEditDate = Pattern.compile(regExEditDate);
        Matcher matcher11 = patternEditDate.matcher(toursNewDate);
        if (matcher11.find() == false) {
            System.err.println("Вы ввели неверное значение");
            System.out.println("Укажите новую дату отправления в формате дд/мм/гггг:");
            toursNewDate = newValue.nextLine();
            getVerifiedToursDate(toursNewDate);
        }
        localToursDate = LocalDate.parse(toursNewDate, dateTimeFormatter);
        if (localToursDate.isAfter(localDateToday.minusDays(1))) {
            System.out.println("Проверка даты пройдена");
        } else {
            mainMenuConstantText.wrongDateMessage();
            System.out.println("Укажите новую дату отправления в формате дд/мм/гггг:");
            toursNewDate = newValue.nextLine();
            getVerifiedToursDate(toursNewDate);
        }
        return verifiedToursDate = dateTimeFormatter.format(localToursDate);
    }
}
