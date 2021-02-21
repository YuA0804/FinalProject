package password;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.io.IOException;

public class PasswordLoginExtract {

    private String loginInProperties;
    private String passwordInProperties;
    private String userLoginInProperties;
    private String userPasswordInProperties;

    public String getLoginInProperties() {
        return loginInProperties;
    }

    public String getPasswordInProperties() {
        return passwordInProperties;
    }

    public String getUserLoginInProperties() {
        return userLoginInProperties;
    }

    public String getUserPasswordInProperties() {
        return userPasswordInProperties;
    }

    public void checkAdminLogin() throws FileNotFoundException {
        Properties properties = new Properties();

        try {
            FileInputStream inp = new FileInputStream("app.properties");
            properties.load(inp);
            loginInProperties = properties.getProperty("adminLogin");

        } catch (IOException a) {
            a.printStackTrace();
        }
    }

    public void checkAdminPassword() throws FileNotFoundException {
        Properties properties = new Properties();
        try {
            FileInputStream inp = new FileInputStream("app.properties");
            properties.load(inp);
            passwordInProperties = properties.getProperty("adminPassword");
        } catch (IOException b) {
            b.printStackTrace();
        }
    }

    public void checkUserLogin() throws FileNotFoundException {
        Properties properties = new Properties();

        try {
            FileInputStream inp = new FileInputStream("app.properties");
            properties.load(inp);
            userLoginInProperties = properties.getProperty("userLogin");

        } catch (IOException a) {
            a.printStackTrace();
        }
    }

    public void checkUserPassword() throws FileNotFoundException {
        Properties properties = new Properties();
        try {
            FileInputStream inp = new FileInputStream("app.properties");
            properties.load(inp);
            userPasswordInProperties = properties.getProperty("userPassword");
        } catch (IOException b) {
            b.printStackTrace();
        }
    }

}
