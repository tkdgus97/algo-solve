import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    private static final int MOD = 9901;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n + 1][m + 1];
        String[] square = new String[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            square[i] = st.nextToken();
        }
        int result = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (square[i - 1].charAt(j - 1) - '0' == 1) {
                    int min = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                    dp[i][j] = min + 1;
                    result = Math.max(dp[i][j], result);
                }
            }
        }
        System.out.println(result * result);

    }

}
