package insiten;

import java.util.Set;
import java.util.HashMap;
import java.util.LinkedList;

public class Runner {

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

    public static Company getCompanyClosestToTargetRevenue(LinkedList<Company> list, long target) {
        long currMin = -1;
        System.out.println(currMin);
        Company out = null;
        for (Company c: list) {
            if (currMin < 0) {
                out = c;
                currMin = abs(c.getRevenue() - target);
            } else if (abs(c.getRevenue() - target) <= currMin) {
                out = c;
                currMin = abs(c.getRevenue() - target);
            }
        }
        System.out.println(out.getName());
        return out;
    }

    public static long abs(long num) {
        if (num < 0) {num *= -1;}
        return num;
    }

    public static void printMap(HashMap<String, Integer> map) {
        Set<String> set = map.keySet();
        for (String s: set) {
            System.out.println(s + ": " + (map.get(s)));
        }
    }

    public static void main(String[] args) throws Exception {
        ParseJSON pJ = new ParseJSON();
        LinkedList<Company> list = pJ.readJSON(".\\resrc\\Companies .json");
        HashMap<String, Integer> map = getCompanyStatusCounts(list);
        Company target = getCompanyClosestToTargetRevenue(list, 234704000);
    }
}
