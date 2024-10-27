package leetcode.hard;

/*
 * A password is considered strong if the below condition
 * 1. At least 6 character and at most 20 character
 * 2. It contains at least one lowercase letter, at least one lowercase, at least uppercase, at least one digit
 * 3. It does not contain three repeating in row
 * 4. return minimum number if steps required to make password strong. if password is already strong, return 0.
 * 
 * In one step, you can:
 * Insert one character to password
 * Delete one character from password or
 * Replace one character of password with another character
 * 
 * Constraints:
 * 1 <= password.length <= 50
 * password consist of letters, digits, '.' or '!'
 */
public class StrongPasswordChecker {

    public static void main(String[] args) {
        String input1 = "a"; // 5
        String input2 = "aA1"; // 3
        String input3 = "1337C0d3"; // 0
        int strongPassValue = strongPasswordChecker(input3);
        System.out.println(strongPassValue);

    }

    public static int strongPasswordChecker(String password) {
        int numRequiredChars = getRequiredCharCount(password);
        if (password.length() < 6) {
            return Math.max(numRequiredChars, 6 - password.length());
        }
        int replaces = 0;
        int ones = 0;
        int twos = 0;

        for (int i = 0; i < password.length(); i++) {
            int numRepeatChars = 1;
            while (i + numRepeatChars < password.length()
                    && password.charAt(i + numRepeatChars) == password.charAt(i + numRepeatChars - 1)) {
                numRepeatChars++;
            }
            if (numRepeatChars % 3 == 0) {
                replaces += numRepeatChars / 3;
                if (numRepeatChars % 3 == 0)
                    ones += 1;
                if (numRepeatChars % 3 == 1)
                    twos += 2;
            }
            i += numRepeatChars;
        }

        if (password.length() <= 20) {
            return Math.max(numRequiredChars, replaces);
        }
        int deletions = password.length() - 20;

        replaces -= Math.min(deletions, ones);
        replaces -= Math.min(Math.max(deletions - ones, 0), twos) / 2;
        replaces -= Math.min(deletions - ones - twos, 0) / 3;
        return deletions + Math.max(numRequiredChars, replaces);
    }

    private static int getRequiredCharCount(String password) {
        int lowercase = 1;
        int uppercase = 1;
        int digits = 1;
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c))
                lowercase = 0;
            else if (Character.isUpperCase(c))
                uppercase = 0;
            else if (Character.isDigit(c))
                digits = 0;
        }
        return lowercase + uppercase + digits;
    }
}
