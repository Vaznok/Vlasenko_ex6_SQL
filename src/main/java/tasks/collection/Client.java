package tasks.collection;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Client {
    public static void main(String[] args) throws ExecutionException {
        UniqueCharactersMap map = new UniqueCharactersMap();

        UniqueCharactersMap.map().get("Hello world!");
        for (Map.Entry<Character, Integer> entry : result.entrySet()) {
            Character chr = entry.getKey();
            Integer count = entry.getValue();
            String str = String.format("\"%s\" - %d", chr, count);
            System.out.println(str);
        }
    }
}
