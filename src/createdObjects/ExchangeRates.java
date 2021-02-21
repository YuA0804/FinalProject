package createdObjects;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExchangeRates {
    private double usdRate;
    private double eurRate;

    public double getUsdRate() {
        return usdRate;
    }

    public void setUsdRate(double usdRate) {
        this.usdRate = usdRate;
    }

    public double getEurRate() {
        return eurRate;
    }

    public void setEurRate(double eurRate) {
        this.eurRate = eurRate;
    }

    String date;
    LocalDate localDate = LocalDate.now();
    LocalDate dateTime;
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void findActualRates() throws FileNotFoundException {
        String regExActDate = ("\\d{2}[\\.\\/]{1}[0-1]{1}\\d{1}[\\.\\/]2\\d{3}(?=\\s\\|\\sUSD)");
        String regExUsdRate = ("(?<=\\|\\sUSD\\s=\\s)\\d+[,.]\\d{1,4}");
        String regExEuroRate = ("(?<=\\|\\sEUR\\s=\\s)\\d+[,.]\\d{1,4}");
        String filename = "ExchangeRates.txt";
        String line = "";
        Pattern patternActDate = Pattern.compile(regExActDate);
        Pattern patternUsdRate = Pattern.compile(regExUsdRate);
        Pattern patternEuroRate = Pattern.compile(regExEuroRate);
        BufferedReader readText = new BufferedReader(new FileReader(filename));
        try {
            while ((line = readText.readLine()) != null) {
                Matcher matcher01 = patternActDate.matcher(line);
                Matcher matcher02 = patternUsdRate.matcher(line);
                Matcher matcher03 = patternEuroRate.matcher(line);
                if (matcher01.find()) {
                    date = matcher01.group();
                    dateTime = LocalDate.parse(date, dateTimeFormatter);
                    if (dateTimeFormatter.format(localDate).compareTo(dateTimeFormatter.format(dateTime)) == 0) {
                        matcher02.find();
                        usdRate = Double.valueOf(matcher02.group());
                        matcher03.find();
                        eurRate = Double.valueOf(matcher03.group());
                        setUsdRate(usdRate);
                        setEurRate(eurRate);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                readText.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void displayActualExchangeRates() {
        System.out.println("Дата: " + dateTimeFormatter.format(dateTime) + " Курс USD: " + usdRate + " Курс EUR: " + eurRate + "\n");
    }
}