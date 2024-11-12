import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int MOD = 1000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        String s = st.nextToken();
        int n = s.length();

        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            int val = s.charAt(i - 1) - '0';
            if (val > 0) {
                dp[i] += dp[i - 1];
                dp[i] %= MOD;
            }

            if (i == 1) {
                continue;
            }

            int prev = s.charAt(i - 2) - '0';
            if (prev != 0 &&  prev*10 + val <= 26 && prev*10 + val >= 10) {
                dp[i] += dp[i - 2];
                dp[i] %= MOD;
            }
        }
        System.out.println(dp[n]);
    }


    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
