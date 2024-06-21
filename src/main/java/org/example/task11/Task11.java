package org.example.task11;

import java.util.*;

public class Task11 {
    public static <K, V> Map<V, Collection<K>> invertMap(Map<K, V> originalMap) {
        Map<V, Collection<K>> invertedMap = new HashMap<>();

        for (Map.Entry<K, V> entry : originalMap.entrySet()) {
            V value = entry.getValue();
            K key = entry.getKey();
            
            if (!invertedMap.containsKey(value)) {
                invertedMap.put(value, new ArrayList<>());
            }

            invertedMap.get(value).add(key);
        }

        return invertedMap;
    }

    public static void main(String[] args) {

        Map<String, Integer> originalMap = new HashMap<>();
        originalMap.put("a", 1);
        originalMap.put("b", 2);
        originalMap.put("c", 1);
        originalMap.put("d", 3);
        originalMap.put("e", 2);

        Map<Integer, Collection<String>> invertedMap = invertMap(originalMap);

        for (Map.Entry<Integer, Collection<String>> entry : invertedMap.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}
