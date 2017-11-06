package tasks.collection;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertTrue;
import static tasks.collection.UniqueCharactersHandler.getUniqueCharactersCounts;


public class UniqueCharactersHandlerTest {
    @Test(expected = IllegalArgumentException.class)
    public void getUniqueCharactersCounts_ArgumentNull_IllegalArgumentExceptionThrown() throws ExecutionException {
        getUniqueCharactersCounts(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getUniqueCharactersCounts_ArgumentEmpty_IllegalArgumentExceptionThrown() throws ExecutionException {
        getUniqueCharactersCounts("");
    }

    @Test
    public void getUniqueCharactersCounts_SpeedTestDifferenceWithCacheAndWithout_CorrectResult() throws ExecutionException {
        getUniqueCharactersCounts("Preload!");

        long startWithoutCache = System.nanoTime();
        getUniqueCharactersCounts("Hello World!");
        long endWithoutCache = System.nanoTime();
        long differenceWithoutCache = endWithoutCache - startWithoutCache;
        System.out.println("getUniqueCharactersCounts() without cache = " + differenceWithoutCache + " ns");

        long startWithCache = System.nanoTime();
        getUniqueCharactersCounts("Hello World!");
        long endWithCache = System.nanoTime();
        long differenceWithCache = endWithCache - startWithCache;
        System.out.println("getUniqueCharactersCounts() with cache = " + differenceWithCache + " ns");

        assertTrue(differenceWithoutCache > differenceWithCache);
    }
}
