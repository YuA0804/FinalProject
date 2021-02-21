package menu;

public class User {

    private String role;//на момент входа
    private String adminStatus = "admin";
    private String workerStatus = "user";

    public String getAdminStatus() {
        return adminStatus;
    }

    public String getWorkerStatus() {
        return workerStatus;
    }

}
