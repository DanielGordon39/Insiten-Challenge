package insiten;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class ParseJSON {

    public static LinkedList<Company> readJSON(String filename) throws FileNotFoundException {
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

}
