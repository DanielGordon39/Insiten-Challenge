package insiten;

import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

public class ParseJSON {

    public static LinkedList<Company> readJSON(String filename) throws Exception {
        Scanner sc = new Scanner(new File(filename));
        while (sc.hasNextLine()) {
            System.out.println(sc.nextLine());
        }
        return null;
    }

}
