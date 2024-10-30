import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int MOD = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        long[][] dp = new long[n + 1][10];

        for (int i = 0; i <= 9; i++) {
            dp[1][i] = 1;
        }

        for(int i = 2; i <= n; i++) {
            dp[i][0] = dp[i -1][1];
            for (int j = 1; j <= 9; j++) {
                if (j == 9) dp[i][j] = (dp[i - 1][j - 1] % MOD);
                else {
                    dp[i][j] = ((dp[i - 1][j - 1] % MOD) + (dp[i - 1][j + 1] % MOD)) % MOD;
                }
            }
        }
        long result = 0;
        for (int i = 1; i <= 9; i++) {
            result = ((result + dp[n][i]) % MOD);
        }
        System.out.println(result);
    }

}
