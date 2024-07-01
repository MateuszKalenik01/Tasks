package org.example.task3;
//
import java.util.Stack;

public class StringDecoder {
/*    String processing
    Given this input: "3[asdf]" you have to generate an output string: asdfasdfasdf
# Q1
## input => "3[asdf]"
            ## output => "asdfasdfasdf"
            # Q2
## input => "3[a]4[b]"
            ## output => "aaabbbb"
            # Q3
## input => "3[a2[b]]"
            ## interim output => "3[abb]"
            ## output => "abbabbabb" (edited)*/

    public static void main(String[] args) {
        System.out.println(decodeString("3[asdf]"));     // Output: asdfasdfasdf
        System.out.println(decodeString("3[a]4[b]"));    // Output: aaabbbb
        System.out.println(decodeString("3[a2[b]]"));    // Output: abbabbabb
    }

    public static String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> resultStack = new Stack<>();
        StringBuilder currentString = new StringBuilder();
        int k = 0;

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                k = k * 10 + ch - '0';
            } else if (ch == '[') {
                countStack.push(k);
                resultStack.push(currentString);
                currentString = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                StringBuilder decodedString = resultStack.pop();
                int currentK = countStack.pop();
                for (int i = 0; i < currentK; i++) {
                    decodedString.append(currentString);
                }
                currentString = decodedString;
            } else {
                currentString.append(ch);
            }
        }
        return currentString.toString();
    }
}
