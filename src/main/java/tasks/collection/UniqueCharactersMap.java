package tasks.collection;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class UniqueCharactersMap {
    private static Map<Character, Integer> map = new ConcurrentHashMap<>();

    public void add(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (map.containsKey(chars[i])) {
                map.put(chars[i], map.get(chars[i]) + 1);
            } else {
                map.put(chars[i], 1);
            }
        }
    }

    public Map<Character, Integer> get2() {
        return new TreeMap<>(map);
    }

    private static LoadingCache map() {
        LoadingCache<String, UniqueCharactersMap> cachedMap = CacheBuilder.newBuilder()
                .maximumSize(800)
                .expireAfterWrite(6, TimeUnit.HOURS)
                .build(
                        new CacheLoader<String, UniqueCharactersMap>() {
                            @Override
                            public UniqueCharactersMap load(String s) throws Exception {
                                return getFromCache(s);
                            }
                        });
        return cachedMap;
    }

    private static UniqueCharactersMap getFromCache(String str) {
        Map<String, UniqueCharactersMap> map = new TreeMap<>();
        return map.get(str);
    }
}
