package createdObjects;

import menu.MainMenuConstantText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CompanyInfoReading {
    public void readCompanyInfoFile() throws IOException {
        MainMenuConstantText mainMenuConstantText = new MainMenuConstantText();
        String filename = "InfoAboutCompany.txt";
        String line = "";
        BufferedReader readText = null;
        try {
            readText = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while ((line = readText.readLine()) != null) {
            System.out.println(line);
        }
        readText.close();
        mainMenuConstantText.writeLine();
    }
}
