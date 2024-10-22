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

        //dp[i - 1] + 2?;
        int[][] dp = new int[n][3];
        dp[0][0] = 1;
        dp[0][1] = 1;
        dp[0][2] = 1;

        for (int i = 1; i < n; i++) {
            dp[i][0] = ((dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % MOD);
            dp[i][1] = ((dp[i - 1][0] + dp[i - 1][2]) % MOD);
            dp[i][2] = ((dp[i - 1][0] + dp[i - 1][1]) % MOD);
        }
        int result = dp[n - 1][0] + dp[n - 1][1] + dp[n - 1][2];

        System.out.println(result % MOD);
    }

}
