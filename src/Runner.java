import java.util.Set;
import java.util.HashMap;
import java.util.LinkedList;

public class Runner {

    public HashMap<String, Integer> getCompanyStatusCounts(LinkedList<Company> list) {
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

    public Company getCompanyClosestToTargetRevenue(LinkedList<Company> list, long target) {
        Company out = list.getLast();
        for (Company c: list) {
            if (abs(c.getRevenue() - target) < abs(out.getRevenue() - target)) {
                out = c;
            }
        }
        System.out.println(out.getName());
        return out;
    }

    public long abs(long num) {
        if (num < 0) {num *= -1;}
        return num;
    }

    public String printMap(HashMap<String, Integer> map) {
        String out = "";
        Set<String> set = map.keySet();
        for (String s: set) {
            out += String.format(s + ": " + map.get(s) + "\n");
        }
        return out;
    }

    public LinkedList<Company> run(String filename) {
        ParseJSON pJ = new ParseJSON();
        LinkedList<Company> list = pJ.readJSON(filename);
        //pJ.printList(list);
        HashMap<String, Integer> map = getCompanyStatusCounts(list);
        //Company target = getCompanyClosestToTargetRevenue(list, Long.parseLong(revenue));
        return list;
    }
}
