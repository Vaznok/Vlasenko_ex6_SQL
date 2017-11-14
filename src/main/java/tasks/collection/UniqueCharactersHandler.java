package tasks.collection;

import java.util.concurrent.ExecutionException;

import static tasks.collection.UniqueCharactersCache.cachedMap;
import static tasks.collection.UniqueCharactersCache.getData;

public class UniqueCharactersHandler {
    private static void addString(String str) {
        UniqueCharactersCounter newCounter = new UniqueCharactersCounter(str);
        getData().put(str, newCounter);
    }

    public static UniqueCharactersCounter getUniqueCharactersCounts(String str) throws ExecutionException {
        if (str == null || str.isEmpty())
            throw new IllegalArgumentException("Argument mustn't been 'null' or empty!");
        if (!getData().containsKey(str)) {
            addString(str);
        }
        UniqueCharactersCounter output = (UniqueCharactersCounter) cachedMap().get(str);
        return output;
    }
}
