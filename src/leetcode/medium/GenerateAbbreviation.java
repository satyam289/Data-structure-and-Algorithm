package leetcode.medium;

import java.util.*;

public class GenerateAbbreviation {

    public List<String> generateAbbreviation(String word) {
        List<String> ans = new ArrayList<>();
        for (int x = 0; x < (1 << word.length()); ++x) {
            ans.add(abbr(word, x));
        }
        return ans;
    }

    public String abbr(String word, int x) {
        StringBuilder builder = new StringBuilder();
        int k = 0, n = word.length();
        for (int i = 0; i < n; ++i, x >>= 1) {
            if ((x & 1) == 0) { // bit is zero
                if (k != 0) {
                    builder.append(k);
                    k = 0; // reset counter
                }
                builder.append(word.charAt(i));
            } else // bit is one, increase k
                ++k;
        }
        if (k != 0)
            builder.append(k);
        return builder.toString();
    }

    public List<String> generateAbbrevations(String word) {
        List<String> ans = new ArrayList<>();
        backtrack(ans, new StringBuilder(), word, 0, 0);
        return ans;
    }

    private void backtrack(List<String> ans, StringBuilder builder, String word, int i, int k) {
        int len = builder.length();
        if (i == word.length()) {
            if (k != 0) {
                builder.append(k); // append last k if non zero
            }
            ans.add(builder.toString());
        } else {
            backtrack(ans, builder, word, i + 1, k + 1);
            if (k != 0) {
                builder.append(k);
            }
            builder.append(word.charAt(i));
            backtrack(ans, builder, word, i + 1, 0);
        }
        builder.setLength(len); // reset builder to original length
    }
}
