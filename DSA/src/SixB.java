// You are given an array of different words and target words. Each character of a word represents
// a different digit ranging from 0 to 9, and no two character are linked to same digit. If the sum of
// the numbers represented by each word on the array equals the sum the number represented by the
// targeted word, return true; otherwise, return false.

//Answer: The aim of this code is to find whether the given equation is solvable where each alphabet in
//the equation has a unique number and ranging from 0 to 9.

// The code first initializes some variables to keep track of the unique characters, their counts, and
// whether they can have a leading zero. It then loops through each word in the words array, counts the
// number of times each character appears in the equation, and marks the characters that cannot have a
// leading zero. It does the same thing for the result string.

// The code then initializes some variables for backtracking and creates a character array of unique
// characters from the equation. It then recursively checks each possible digit mapping to determine if
// the equation can be solved. It starts by checking if the first character can be mapped to 1-9 or 0-9
// depending on whether it can have a leading zero. If it can, it marks that digit as used and continues
// recursively checking the next character. If a solution is found, it returns true, and if not, it
// backtracks and tries the next possible digit mapping. If all possible digit mappings have been tried
// and no solution has been found, it returns false.

import java.util.HashSet;
import java.util.Set;

public class SixB {
    private static final int[] POW_10 = new int[]{1, 10, 100, 1000, 10000, 100000, 1000000};
    public boolean isSolvable(String[] words, String result) {
        Set<Character> charSet = new HashSet<>();
        int[] charCount = new int[91];
        boolean[] nonLeadingZero = new boolean[91]; // ASCII of A..Z chars are in range 65..90
        for (String word : words) {
            char[] cs = word.toCharArray();
            for (int i = 0; i < cs.length; i++) {
                if (i == 0 && cs.length > 1) nonLeadingZero[cs[i]] = true;
                charSet.add(cs[i]);
                charCount[cs[i]] += POW_10[cs.length - i - 1]; // charCount is calculated by units
            }
        }
        char[] cs = result.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            if (i == 0 && cs.length > 1) nonLeadingZero[cs[i]] = true;
            charSet.add(cs[i]);
            charCount[cs[i]] -= POW_10[cs.length - i - 1]; // charCount is calculated by units
        }
        boolean[] used = new boolean[10];
        char[] charList = new char[charSet.size()];
        int i = 0;
        for (char c : charSet) charList[i++] = c;
        return backtracking(used, charList, nonLeadingZero, 0, 0, charCount);
    }

    private boolean backtracking(boolean[] used, char[] charList, boolean[] nonLeadingZero, int step, int diff, int[] charCount) {
        if (step == charList.length) return diff == 0; // difference between sum of words and result equal to 0
        for (int d = 0; d <= 9; d++) { // each character is decoded as one digit (0 - 9).
            char c = charList[step];
            if (!used[d] // each different characters must map to different digits
                    && (d > 0 || !nonLeadingZero[c])) {  // decoded as one number without leading zeros.
                used[d] = true;
                if (backtracking(used, charList, nonLeadingZero, step + 1, diff + charCount[c] * d, charCount)) return true;
                used[d] = false;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SixB puzzle = new SixB();
        System.out.println(puzzle.isSolvable(new String[]{"SIX","SEVEN","SEVEN"}, "TWENTY"));
    }
}
