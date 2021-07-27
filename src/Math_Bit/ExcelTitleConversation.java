package Math_Bit;

public class ExcelTitleConversation {

    // https://www.interviewbit.com/problems/excel-column-title/
    public String convertToTitle(int A) {
        String str = "";
        int num, B;
        char[] alphabets = { 'Z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
                'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y' };
        while (A != 0) {
            num = A % 26;
            str = alphabets[num] + str;
            B = A;
            A = A / 26;
            if (A * 26 == B)
                A = A - 1;
        }
        return str;
    }

    public String convertToTitle2(int A) {
        StringBuilder sb = new StringBuilder();
        while (A > 0) {
            int remainder = A % 26;
            if (remainder == 0) {
                sb.append("Z");
                A = (A / 26) - 1;
            } else {
                sb.append((char) (remainder - 1 + 'A'));
                A = A / 26;
            }
        }
        sb = sb.reverse();
        return sb.toString();
    }

    public String convertToTitle3(int a) {
        String s = "";
        while (a != 0) {
            a = a - 1;
            char c = (char) (a % 26 + 65);
            s = c + s;
            a /= 26;
        }
        return s;
    }

    // https://www.interviewbit.com/problems/excel-column-number/
    public int titleToNumber(String a) {
        int num = 0;
        for (int i = a.length() - 1, j = 0; i >= 0; i--) {
            num += (int) Math.pow(26, j) * (a.charAt(i) - 'A' + 1);
            j++;
        }
        return num;
    }

    public int titleToNumber2(String A) {
        char[] b = A.toCharArray();
        int result = 0;
        for (int i = 0; i < b.length; i++) {
            result = result * 26 + (b[i] - 'A' + 1);
        }
        return result;
    }
}
