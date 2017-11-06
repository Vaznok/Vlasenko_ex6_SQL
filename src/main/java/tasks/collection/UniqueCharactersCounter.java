package tasks.collection;

import java.util.Map;
import java.util.TreeMap;

public class UniqueCharactersCounter {
    private final String string;
    private final Map<Character, Integer> map = new TreeMap<>();

    public UniqueCharactersCounter(String string) {
        this.string = string;
        add(string);
    }

    private void add(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i])) {
                map.put(chars[i], map.get(chars[i]) + 1);
            } else {
                map.put(chars[i], 1);
            }
        }
    }

    @Override
    public String toString() {
        String str = "";
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            Character chr = entry.getKey();
            Integer count = entry.getValue();
            str += String.format("\"%s\" - %d\n", chr, count);
        }
        return str;
    }

    Map<Character, Integer> getMap() {
        return map;
    }
}
