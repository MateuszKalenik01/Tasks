package org.example.task2;

import java.util.*;

public class Task2 {

    public static void main(String[] args) {
        int[][] image1 = {
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 0, 0, 0, 1},
                {1, 1, 1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1},
        };

        int[][] image2 = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 0, 0, 0, 1, 1, 0, 1},
                {1, 1, 1, 0, 0, 0, 1, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
        };

        System.out.println(findSingleRectangle(image1));
        System.out.println(findAllRectangles(image2));
    }

    public static Map<String, Integer> findSingleRectangle(int[][] image) {
        int rows = image.length;
        int cols = image[0].length;
        Map<String, Integer> rectangle = new HashMap<>();

        boolean foundTopLeft = false;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (image[i][j] == 0) {
                    if (!foundTopLeft) {
                        rectangle.put("x", j);
                        rectangle.put("y", i);
                        foundTopLeft = true;
                    }
                    rectangle.put("width", j - rectangle.get("x") + 1);
                    rectangle.put("height", i - rectangle.get("y") + 1);
                }
            }
        }

        return rectangle;
    }

    public static List<Map<String, Integer>> findAllRectangles(int[][] image) {
        int rows = image.length;
        int cols = image[0].length;
        List<Map<String, Integer>> rectangles = new ArrayList<>();

        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (image[i][j] == 0 && !visited[i][j]) {
                    Map<String, Integer> rectangle = new HashMap<>();
                    int x = j, y = i;
                    while (j < cols && image[i][j] == 0) {
                        j++;
                    }
                    int width = j - x;
                    j = x;
                    while (i < rows && image[i][j] == 0) {
                        for (int k = j; k < j + width; k++) {
                            visited[i][k] = true;
                        }
                        i++;
                    }
                    int height = i - y;
                    rectangle.put("x", x);
                    rectangle.put("y", y);
                    rectangle.put("width", width);
                    rectangle.put("height", height);
                    rectangles.add(rectangle);
                    i = y;
                }
            }
        }

        return rectangles;
    }
}

