package org.example.task6;

import java.util.*;

public class Test6 {
  /*  You have next input parameters.
            Coins = 400;
    Menu = { “coffee” : 100
                 “cake” : 200
		“popcorn” : 225
    }
    You need to calculate optimal amount of products that you can buy for your coins. Print all possible combinations of foods.
*/
  public static void main(String[] args) {
      int coins = 400;
      Map<String, Integer> menu = Map.of(
              "coffee", 100,
              "cake", 200,
              "popcorn", 225
      );

      List<List<String>> result = getAllCombinations(menu, coins);
      result.forEach(System.out::println);
  }

    public static List<List<String>> getAllCombinations(Map<String, Integer> menu, int coins) {
        List<String> items = new ArrayList<>(menu.keySet());
        List<List<String>> allCombinations = new ArrayList<>();

        generateCombinations(menu, coins, items, new ArrayList<>(), 0, allCombinations);

        return allCombinations;
    }

    public static void generateCombinations(Map<String, Integer> menu, int coins, List<String> items, List<String> currentCombination, int start, List<List<String>> allCombinations) {
        int currentSum = currentCombination.stream().mapToInt(menu::get).sum();
        if (currentSum > coins) {
            return;
        }
        if (!currentCombination.isEmpty()) {
            allCombinations.add(new ArrayList<>(currentCombination));
        }

        for (int i = start; i < items.size(); i++) {
            String item = items.get(i);
            currentCombination.add(item);
            generateCombinations(menu, coins, items, currentCombination, i, allCombinations);
            currentCombination.remove(currentCombination.size() - 1);
        }
    }
}