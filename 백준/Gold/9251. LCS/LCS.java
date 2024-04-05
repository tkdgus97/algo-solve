import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        char[] s1 = st.nextToken().toCharArray();

        st = new StringTokenizer(br.readLine());
        char[] s2 = st.nextToken().toCharArray();

        int[][] dp = new int[s1.length + 1][s2.length + 1];

        for (int i = 1; i <=s1.length; i++) {
            for (int j = 1; j <= s2.length; j++) {
                if (s1[i - 1] == s2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j] , dp[i][j - 1]);
                }
            }
        }

        System.out.println(dp[s1.length][s2.length]);
    }
}
