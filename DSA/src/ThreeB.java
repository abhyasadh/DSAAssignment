// You are provided certain string and pattern, return true if pattern entirely matches the string
// otherwise return false.
// Note: if pattern contains char @ it matches entire sequence of characters and # matches any single
// character within string.

//Answer:
//This can be done by:
// 1) First checking if the provided pattern is '@'. If it is, it matches the entire string and true is
//    returned. If there is characters before or after '@', it should return false as the @ character
//    replaces the entire string and more characters are added to the string.

// 2) After checking for '@' character, we must check if the string length is same or not. If it is not
//    and the pattern is not '@', the result is automatically false.

// 3) If the string and pattern does not fulfill the given criteria, we must check for each character of
//    the string if it matches the pattern or pattern contains a #. Also, if the remaining characters
//    after checking are same, we can return true.

import java.util.Objects;
import java.util.Scanner;

public class ThreeB {

    String string;
    String pattern;

    public ThreeB(String string, String pattern) {
        this.string = string;
        this.pattern = pattern;
    }


    //function to check the if the pattern matches
    public boolean patternChecker(){
        //this function checks if the string matches the pattern and returns a boolean value as a result

        //this if statement checks if the provided pattern is '@' and only enters into the block if it's not
        if (!Objects.equals(pattern, "@")) {

            //this if statement checks if the length of string matches the length of pattern.
            //if it doesn't, false is returned
            if (string.length()!=pattern.length()) return false;

            //this loop iterates through each character of string and pattern while comparing
            for (int i = 0; i < string.length(); i++) {

                //this if statement checks if the remaining pattern is same as remaining string
                if (string.substring(i).equals(pattern.substring(i))){
                    return true;
                }

                //this if statement checks if each character of string matches each character of the pattern
                //it is only executed if remaining characters are not same
                if (!(string.toCharArray()[i] == pattern.toCharArray()[i] || pattern.toCharArray()[i] == '#')) {
                    return false;
                }
            }
        }

        //if pattern is '@', if-block is not executed and true is returned.
        return true;
    }

    public static void main(String[] args) {

        //taking input from the user
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String string = input.next();
        System.out.print("Enter a pattern: ");
        String pattern = input.next();

        //calling the function
        ThreeB patternChecking = new ThreeB(string, pattern);
        System.out.println(patternChecking.patternChecker());
    }
}
