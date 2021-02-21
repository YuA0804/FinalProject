package createdObjects;

public class UsersData {
    private String userLogin;
    private String userName;
    private String status;

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "userLogin = " + userLogin + "; userName = " + userName + "; status = " + status;
    }

    public UsersData(String userLogin, String userName, String status) {
        this.userLogin = userLogin;
        this.userName = userName;
        this.status = status;
    }
}
