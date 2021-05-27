package domain;

public class Client extends User{

    private String fName;
    private String lName;
    private Integer points;

    public Client(String username, String password, String fName, String lName, Integer points) {
        super(username, password);
        this.fName = fName;
        this.lName = lName;
        this.points = points;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Client() {
    }
}
