package tests;

import java.util.ArrayList;
import java.util.List;

public class Exercise {

    private static String[] names = {"joeson", "joeslib", "joesmon"};
    private static List<String> arrayString = new ArrayList<>(List.of(names));

    public static void main(String[] args) {
        Exercise ex = new Exercise();
        String result = ex.getCommonPrefix(arrayString);
        System.out.println(result);
    }

    public String getCommonPrefix(List<String> arr){
        String firstRemoved = arr.removeLast();
        int first_item_length = firstRemoved.length();
        while (!arr.isEmpty()) {
            String nextWord = arr.removeLast();
            int nextWordSize = nextWord.length();
            int i = 0;
            while (i < Math.min(first_item_length, nextWordSize) && firstRemoved.charAt(i) == nextWord.charAt(i)) {
                i++;
            }
            first_item_length = i;
        }
        return firstRemoved.substring(0, first_item_length);
    };

//    public int reveredNumber(int numbers) {
//        String numbersToString = numbers.toString();
//    }
}
