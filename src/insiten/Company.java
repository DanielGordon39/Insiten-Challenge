package insiten;

public class Company {

    String name;
    String status;
    long revenue;
    String location;

    Company(String name, String status, long revenue, String location) {
        this.name = name;
        this.status = status;
        this.revenue = revenue;
        this.location = location;
    }

    Company() {
        this("", "", 0, "");
    }

    public String getName() {return this.name;}
    public String getStatus() {return this.status;}
    public long getRevenue() {return this.revenue;}
    public String getLocation() {return this.location;}

    public void setName(String name) {this.name = name;}
    public void setStatus(String status) {this.status = status;}
    public void setRevenue(long revenue) {this.revenue = revenue;}
    public void setLocation(String location) {this.location = location;}

}
