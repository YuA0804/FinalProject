package createdObjects;

import menu.MainMenuConstantText;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToursListReading {
    ArrayList<ToursData> listTours = new ArrayList<>();
    String regExID = ("(?<=ID\\s\\=\\s)\\d+");
    String regExTourName = ("(?<=tourName\\s\\=\\s)[A-Za-zА-Яа-яё0-9\\-\\–\\.\\\"\\s\\!\\'\\,]+");
    String regExTourDescription = ("(?<=tourDescription\\s\\=\\s)[A-Za-zА-Яа-я0-9\\s\\-\\:\\,\\.\\(\\)\\\"\\']+(?=\\;\\stourPrices)");
    String regExTourDescriptionReplace = ("ё");
    String regExTourPrice = ("\\d+[,.-]\\d*");
    String regExTourDepartureDate = ("(?<=departureDate\\s\\=\\s)[0-3]\\d[-|.\\/][0-1]\\d[-|.\\/]\\d{4}");
    Pattern patternID = Pattern.compile(regExID);
    Pattern patternTourName = Pattern.compile(regExTourName);
    Pattern patternTourDescription = Pattern.compile(regExTourDescription);
    Pattern patternTourDescriptionReplace = Pattern.compile(regExTourDescriptionReplace);
    Pattern patternToursPrice = Pattern.compile(regExTourPrice);
    Pattern patternToursDepartureDate = Pattern.compile(regExTourDepartureDate);
    String filename = "ToursList.txt";
    String line = "";
    String tID, tourName, tourDescription, tPrice, replaceString, tDepDate;
    int tourID;
    Double tourPrice;
    LocalDate localDate;
    LocalDate actualDate = LocalDate.now();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    MainMenuConstantText mainMenuConstantText = new MainMenuConstantText();
    ExchangeRates exchangeRates = new ExchangeRates();

    public ArrayList<ToursData> readToursListFile() throws IOException {
        listTours.clear();
        BufferedReader readText = new BufferedReader(new FileReader(filename));
        try {
            while ((line = readText.readLine()) != null) {
                Matcher matcher03 = patternTourDescriptionReplace.matcher(line);
                replaceString = matcher03.replaceAll("е");
                Matcher matcher00 = patternID.matcher(replaceString);
                Matcher matcher01 = patternTourName.matcher(replaceString);
                Matcher matcher02 = patternTourDescription.matcher(replaceString);
                Matcher matcher04 = patternToursPrice.matcher(replaceString);
                Matcher matcher05 = patternToursDepartureDate.matcher(replaceString);
                if (line.startsWith("{")) {
                    while (matcher00.find()) {
                        tID = matcher00.group();
                        tourID = Integer.parseInt(tID);
                        //  toursData.setTourID(tourID);
                    }
                    while (matcher01.find()) {
                        tourName = matcher01.group();
                    }
                    while (matcher02.find()) {
                        tourDescription = matcher02.group();
                    }
                    while (matcher04.find()) {
                        tPrice = matcher04.group();
                        tPrice = tPrice.replaceAll(",", ".");
                        tourPrice = Double.valueOf(tPrice);
                    }
                    while (matcher05.find()) {
                        tDepDate = matcher05.group();
                        localDate = LocalDate.parse(tDepDate, dateTimeFormatter);
                    }
                    //  listTours.add(toursData);
                    listTours.add(new ToursData(tourID, tourName, tourDescription, tourPrice, tDepDate));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            readText.close();
        }
        exchangeRates.findActualRates();

        return listTours;
    }

    public void findToursByDateAndPrice() throws IOException {
        int countTours = 0;
        if (listTours.isEmpty()) {
            System.out.println("Список актуальных туров пуст.");
        } else {
            System.out.println("Укажите минимальную стоимость тура руб/чел:");
            Scanner minRatePrice = new Scanner(System.in);
            double minPrice = minRatePrice.nextDouble();
            while (minPrice <= 0) {
                System.err.println("Акций и бесплатных туров нет. Стоимость должна быть больше 0. Введите новое значение.");
                minPrice = minRatePrice.nextDouble();
            }


            for (ToursData resultTourList : listTours) {
                String tourDateforSearch = resultTourList.getTourDate();
                LocalDate tourDateforCompare = LocalDate.parse(tourDateforSearch, dateTimeFormatter);
                if (tourDateforCompare.isAfter(actualDate.minusDays(1)) & resultTourList.getTourPrice() >= minPrice) {
                    System.out.println(listTours.indexOf(resultTourList) + " " + resultTourList);
                    countTours = countTours + 1;
                }
            }
        }

        if (countTours <= 0) {
            System.out.println("Действующих туров, подходящих под описание, нет.\nИдёт перенаправление в раздел Туры.\n");
        }
        mainMenuConstantText.writeLine();
    }

    public void findToursActualTours() throws IOException {
        readToursListFile();
        int countActualTours = 0;
        System.out.println("ДЕЙСТВУЮЩИЕ ТУРЫ: ");
        for (ToursData resultTourList : listTours) {
            if (listTours.isEmpty()) {
                countActualTours = countActualTours - 1;
            } else {
                LocalDate dateForDisplayActualTours = LocalDate.parse(resultTourList.getTourDate(), dateTimeFormatter);
                if (dateForDisplayActualTours.isBefore(actualDate)) {
                } else {
                    System.out.println(listTours.indexOf(resultTourList) + " " + resultTourList);
                    countActualTours = countActualTours + 1;
                }
            }
        }
        if (countActualTours <= 0) {
            System.err.println("Список актуальных туров пуст. \n***Для просмотра архивных туров, перейдите в раздел \"Архив туров\", ");
        }
    }

    public void findArchiveTours() throws IOException {
        int countArchiveTours = 0;
        System.out.println("АРХИВ ТУРОВ: ");
        for (ToursData resultTourList : listTours) {
            if (listTours.isEmpty()) {
                System.err.println("Список туров пуст.");
            } else {
                LocalDate dateForDisplayActualTours = LocalDate.parse(resultTourList.getTourDate(), dateTimeFormatter);
                if (dateForDisplayActualTours.isBefore(actualDate)) {
                    System.out.println(listTours.indexOf(resultTourList) + " " + resultTourList);
                    countArchiveTours = countArchiveTours + 1;
                } else {
                }
            }
        }
        if (countArchiveTours < 1) {
            System.err.println("Архив туров пуст.");
        }
        mainMenuConstantText.writeLine();
    }
}

