import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        long[] dp = new long[10000];

        dp[0] = dp[2] = 1;

        for (int i = 4; i <= n; i += 2) {
            for (int j = 0; j <= i - 2; j+=2) {
                dp[i] += dp[j] * dp[i - j - 2];
                dp[i] %= 987654321;
            }
        }

        System.out.println(dp[n]);
    }

}
