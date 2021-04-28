package Array;

public class CountAndSay {
    
    public static String sayCount(int A){
        if(A == 1){
            return "1";
        }
        return sayCountRec("1",A);
    }

    private static String sayCountRec(String s, int A) {
        if (A == 0)
            return s;
        StringBuilder sb = new StringBuilder();
        int count = 1;
        char ch = s.charAt(0);
        int n = s.length();
        for (int i = 1; i < n; ++i) {
            if (s.charAt(i) != ch) {
                sb.append(count);
                sb.append(ch);
                count = 1;
                ch = s.charAt(i);
            } else {
                ++count;
            }
        }
        sb.append(count);
        sb.append(ch);
        return sayCountRec(sb.toString(), A - 1);
    }

    public String countAndSay(int n) {
        if (n <= 0) {
            return "";
        }
        String string = "1";
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < n + 1; i++) {
            char[] chars = string.toCharArray();
            char ch = 0;
            int count = 0;
            for (int j = 0; j < chars.length; j++) {
                if (j == 0) {
                    ch = chars[0];
                    count = 1;
                } else if (chars[j] == ch) {
                    count++;
                } else {
                    sb.append(count).append(ch);
                    ch = chars[j];
                    count = 1;
                }
            }
            sb.append(count).append(ch);
            string = sb.toString();
            sb.delete(0, sb.length());
        }
        return string;
    }

    public static void main(String [] args){
        System.out.println(sayCount(4));
    }

}
