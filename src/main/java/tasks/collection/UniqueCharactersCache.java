package tasks.collection;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class UniqueCharactersCache {
    private static Map<String, UniqueCharactersCounter> data = new ConcurrentHashMap<>();
    private static LoadingCache<String, UniqueCharactersCounter> cachedMap;

    static synchronized LoadingCache cachedMap() {
        if (cachedMap == null) {
            cachedMap = CacheBuilder.newBuilder()
                    .maximumSize(250)
                    .expireAfterWrite(6, TimeUnit.HOURS)
                    .build(new CacheLoader<String, UniqueCharactersCounter>() {
                        @Override
                        public UniqueCharactersCounter load(String str) throws Exception {
                            return getFromCache(str);
                        }
                    });
            return cachedMap;
        } else {
            return cachedMap;
        }
    }

    private static UniqueCharactersCounter getFromCache(String str) {
        return data.get(str);
    }

    static void clearCache() {
        data.clear();
        cachedMap = null;
    }

    static Map<String, UniqueCharactersCounter> getData() {
        return data;
    }

    static LoadingCache<String, UniqueCharactersCounter> getCachedMap() {
        return cachedMap;
    }
}
