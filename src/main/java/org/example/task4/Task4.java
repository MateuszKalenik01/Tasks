package org.example.task4;

import java.util.HashMap;
import java.util.Map;

public class Task4 {
/*  given two strings , return true if they are palindromes.
    I did in 2 different way, but they want the answer using hash, since the complexity is lower*/
    public static void main(String[] args) {
        String str1 = "racecar";
        String str2 = "level";

        System.out.println("Both strings are palindromes: " + areBothPalindromes(str1, str2));
    }

    public static boolean areBothPalindromes(String str1, String str2) {
        return isPalindrome(str1) && isPalindrome(str2);
    }

    public static boolean isPalindrome(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }

        int hashForward = computeHash(str);

        int hashBackward = computeReverseHash(str);

        return hashForward == hashBackward;
    }

    private static int computeHash(String str) {
        int hash = 0;
        int base = 31;

        for (int i = 0; i < str.length(); i++) {
            hash = hash * base + str.charAt(i);
        }

        return hash;
    }

    private static int computeReverseHash(String str) {
        int hash = 0;
        int base = 31;

        for (int i = str.length() - 1; i >= 0; i--) {
            hash = hash * base + str.charAt(i);
        }

        return hash;
    }
}

