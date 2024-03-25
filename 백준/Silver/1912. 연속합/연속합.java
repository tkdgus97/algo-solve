import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());

        int[] values = new int[N];

        int[] dp = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            values[i] = Integer.parseInt(st.nextToken());
        }
        dp[0] = values[0];
        for (int i = 1; i < N; i++) {
            if (values[i] > dp[i - 1]) {
                dp[i] = values[i];
                if (dp[i - 1] + values[i] > dp[i]) {
                    dp[i] = dp[i - 1] + values[i];
                }
                continue;
            } else {
                dp[i] = dp[i - 1] + values[i];
            }
        }

        int result = dp[0];

        for (int i = 1; i < N; i++) {
            result = Math.max(result, dp[i]);
        }

        System.out.println(result);
    }

}
