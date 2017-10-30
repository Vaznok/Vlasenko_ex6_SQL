package tasks.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static tasks.collection.UniqueCharactersCache.clearCache;
import static tasks.collection.UniqueCharactersHandler.getUniqueCharactersCounts;

public class UniqueCharactersCounterTest {

    @Before
    public void refreshFields() {
        clearCache();
    }

    @Test
    public void addString_ArgumentCorrect_CorrectResult() throws ExecutionException {
        TreeMap<Character, Integer> treeMap = new TreeMap<>();
        treeMap.put(' ', 1);
        treeMap.put('!', 1);
        treeMap.put('H', 1);
        treeMap.put('W', 1);
        treeMap.put('d', 1);
        treeMap.put('e', 1);
        treeMap.put('l', 3);
        treeMap.put('o', 2);
        treeMap.put('r', 1);

        UniqueCharactersCounter result = getUniqueCharactersCounts("Hello World!");
        assertEquals(treeMap, result.getMap());
    }

    @Test
    public void addString_ArgumentCorrectSymbolsLettersAndNumbers_CorrectResult() throws ExecutionException {
        TreeMap<Character, Integer> treeMap = new TreeMap<>();
        treeMap.put(' ', 2);
        treeMap.put('#', 3);
        treeMap.put('$', 3);
        treeMap.put('%', 1);
        treeMap.put('*', 1);
        treeMap.put('-', 2);
        treeMap.put('1', 1);
        treeMap.put('3', 2);
        treeMap.put('5', 1);
        treeMap.put('@', 1);
        treeMap.put('m', 1);
        treeMap.put('o', 1);

        UniqueCharactersCounter result = getUniqueCharactersCounts("mo13@#$5 - *-%#$#$3");
        assertEquals(treeMap, result.getMap());
    }
}
