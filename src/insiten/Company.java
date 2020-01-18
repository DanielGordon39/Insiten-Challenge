package insiten;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Set;

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

    public static LinkedList<Company> readJSON(String filename) throws FileNotFoundException {
        //if (filename.isEmpty()) {
            //throw new Exception("");
        //}
        LinkedList<Company> list = new LinkedList<>();
        String curr;
        Scanner sc = new Scanner(new File(filename));
        while (sc.hasNextLine()) {
            if (sc.nextLine().contains("{")) {
                Company company = new Company();
                curr = sc.nextLine();
                curr = curr.substring(11, curr.length() - 2);
                company.setName(curr);
                curr = sc.nextLine();
                curr = curr.substring(13, curr.length() - 2);
                company.setStatus(curr);
                curr = sc.nextLine();
                curr = curr.substring(14, curr.length() - 1);
                company.setRevenue(Long.parseLong(curr));
                curr = sc.nextLine();
                curr = curr.substring(15, curr.length() - 1);
                company.setLocation(curr);
                list.add(company);
            }
        }
        return list;
    }

    public static HashMap<String, Integer> getCompanyStatusCounts(LinkedList<Company> list) {
        HashMap<String, Integer> map = new HashMap<>();
        for (Company e: list) {
            String status = e.getStatus();
            if (map.containsKey(status)) {
                map.replace(status, map.get(status) + 1);
            } else {
                map.put(status, 1);
            }
        }
        //printMap(map);
        return map;
    }

    public String getName() {return this.name;}
    public String getStatus() {return this.status;}
    public long getRevenue() {return this.revenue;}
    public String getLocation() {return this.location;}

    public void setName(String name) {this.name = name;}
    public void setStatus(String status) {this.status = status;}
    public void setRevenue(long revenue) {this.revenue = revenue;}
    public void setLocation(String location) {this.location = location;}

    public static void main(String[] args) throws FileNotFoundException {
        LinkedList<Company> list = readJSON("C:\\Users\\Daniel\\Insiten\\resrc\\Companies .json");
        HashMap<String, Integer> map = getCompanyStatusCounts(list);
    }

    public static void printMap(HashMap<String, Integer> map) {
        Set<String> set = map.keySet();
        for (String s: set) {
            System.out.println(s + ": " + (map.get(s)));
        }
    }
}
