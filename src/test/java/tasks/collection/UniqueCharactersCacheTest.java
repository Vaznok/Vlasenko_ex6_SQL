package tasks.collection;

import org.junit.*;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertTrue;

import static tasks.collection.UniqueCharactersCache.clearCache;
import static tasks.collection.UniqueCharactersCache.getCachedMap;
import static tasks.collection.UniqueCharactersCache.getData;
import static tasks.collection.UniqueCharactersHandler.getUniqueCharactersCounts;


public class UniqueCharactersCacheTest {

    @Before
    public void refreshFields() {
        clearCache();
    }

    @Test
    public void getData_FieldDataIsEmpty_CorrectResult() throws ExecutionException {
        assertTrue(getData().isEmpty());
    }

    @Test
    public void getData_FieldDataInProcessLengthThree_CorrectResult() throws ExecutionException {
        getUniqueCharactersCounts("First data added!");
        getUniqueCharactersCounts("Second data added!");
        getUniqueCharactersCounts("Third data added!");
        assertTrue(getData().size() == 3);
    }

    @Test
    public void getCachedMap_FieldCachedMapIsNull_CorrectResult() throws ExecutionException {
        assertTrue(getCachedMap() == null);
    }

    @Test
    public void getCachedMap_FieldCachedMapInProcessNotNull_CorrectResult() throws ExecutionException {
        assertTrue(getCachedMap() == null);
        getUniqueCharactersCounts("Data added!");
        assertTrue(getCachedMap() != null);
    }
}
