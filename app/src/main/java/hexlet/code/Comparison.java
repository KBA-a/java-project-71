package hexlet.code;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;




public class Comparison {

    public static List<Map<String, Object>> compareJsonFile(Map<String, Object> file1, Map<String, Object> file2) {
        List<Map<String, Object>> compareResult = new ArrayList<>();
        var keys = new TreeSet<>();
        keys.addAll(file1.keySet());
        keys.addAll(file2.keySet());
        keys.forEach(key -> {
            if (!file1.containsKey(key)) {
                compareResult.add(Map.of("Field", key,
                        "Status", "Add",
                        "New_value", file2.get(key)));
            } else if (!file2.containsKey(key)) {
                compareResult.add(Map.of("Field", key,
                        "Status", "Delete",
                        "Old_value", file1.get(key)));
            } else if (file1.get(key).equals(file2.get(key))) {
                compareResult.add(Map.of("Field", key,
                        "Status", "Same",
                        "Value", file1.get(key)));
            } else {
                compareResult.add(Map.of("Field", key,
                        "Status", "Update",
                        "Old_value", file1.get(key),
                        "New_value", file2.get(key)));
            }
        });
        return compareResult;
    }
}
