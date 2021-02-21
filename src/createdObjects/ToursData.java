package createdObjects;

import java.io.FileNotFoundException;
import java.text.DecimalFormat;

public class ToursData {
    private int tourID;
    private String tourName;
    private String tourDescription;
    private double tourPrice;
    private String tourDate;

    private double actualPriceInEuro;

    public double getActualPriceInUSD() {
        return actualPriceInUSD;
    }

    public void setActualPriceInUSD(double actualPriceInUSD) {
        this.actualPriceInUSD = actualPriceInUSD;
    }

    private double actualPriceInUSD;
    ExchangeRates exchangeRates = new ExchangeRates();
    DecimalFormat dF = new DecimalFormat( "#.##" );
    public String getTourDate() {
        return tourDate;
    }

    public void setTourDate(String tourDate) {
        this.tourDate = tourDate;
    }

    public int getTourID() {
        return tourID;
    }

    public void setTourID(int tourID) {
        this.tourID = tourID;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getTourDescription() {
        return tourDescription;
    }

    public void setTourDescription(String tourDescription) {
        this.tourDescription = tourDescription;
    }

    public double getTourPrice() {
        return tourPrice;
    }

    public void setTourPrice(double tourPrice) {
        this.tourPrice = tourPrice;
    }
    public double getActualPriceInEuro() {
        return actualPriceInEuro;
    }

    public void setActualPriceInEuro(double actualPriceInEuro) {
        this.actualPriceInEuro = actualPriceInEuro;
    }

    @Override
    public String toString() {
        actualPriceInEuro();
        actualPriceInUSD();
        return "ID: "  +" " +tourID + ", Дата отправления: " + tourDate + ", Название тура: " + tourName + ", Стоимость тура: " + tourPrice + " руб./чел., " +new DecimalFormat("#.##").format(actualPriceInEuro) +" евро/чел." +
                new DecimalFormat("#.##").format(actualPriceInUSD) +" длр/чел." +", Описание тура: " + tourDescription;
    }

    public ToursData(int tourID, String tourName, String tourDescription, double tourPrice, String tourDate) {
        this.tourID = tourID;
        this.tourName = tourName;
        this.tourDescription = tourDescription;
        this.tourPrice = tourPrice;
        this.tourDate = tourDate;
    }

    private double actualPriceInEuro() {
        try {
            exchangeRates.findActualRates();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        actualPriceInEuro=tourPrice/ exchangeRates.getEurRate();
        return actualPriceInEuro;
    }
    private double actualPriceInUSD() {
        try {
            exchangeRates.findActualRates();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        actualPriceInUSD=tourPrice/ exchangeRates.getUsdRate();
        return actualPriceInUSD;
    }

}
