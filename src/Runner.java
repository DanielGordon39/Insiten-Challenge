import java.util.Set;
import java.util.HashMap;
import java.util.LinkedList;

public class Runner {

    protected HashMap<String, Integer> getCompanyStatusCounts(LinkedList<Company> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("The argument 'list' was 'null' or empty while running 'getCompanyStatusCounts()'");
        }
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

    protected Company getCompanyClosestToTargetRevenue(LinkedList<Company> list, long target) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("The argument 'list' was 'null' or empty while running 'getCompanyClosestToTargetRevenue()'");
        }
        Company out = list.getLast();
        for (Company c: list) {
            if (abs(c.getRevenue() - target) < abs(out.getRevenue() - target)) {
                out = c;
            }
        }
        //System.out.println(out.getName());
        return out;
    }

    private long abs(long num) {
        if (num < 0) {num *= -1;}
        return num;
    }

    protected String printMap(HashMap<String, Integer> map) {
        String out = "";
        Set<String> set = map.keySet();
        for (String s: set) {
            out += String.format(s + ": " + map.get(s) + "\n");
        }
        return out;
    }

    protected LinkedList<Company> run(String filename) {
        ParseJSON pJ = new ParseJSON();
        LinkedList<Company> list = pJ.readJSON(filename);
        //pJ.printList(list);
        return list;
    }
}
