package org.example.task8;

import java.util.HashMap;
import java.util.Map;

public class Task8 {
    public static Map<Character, Integer> countCharacters(String input) {
        Map<Character, Integer> characterCount = new HashMap<>();

        for (char c : input.toCharArray()) {
            characterCount.put(c, characterCount.getOrDefault(c, 0) + 1);
        }

        return characterCount;
    }

    public static void main(String[] args) {
        String testString = "Hello world";
        Map<Character, Integer> result = countCharacters(testString);

        for (Map.Entry<Character, Integer> entry : result.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}
