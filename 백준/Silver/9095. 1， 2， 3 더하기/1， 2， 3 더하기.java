import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            int[] dp = new int[11];

            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 4;

            if (N < 4) {
                sb.append(dp[N]).append("\n");
                continue;
            }

            for (int j = 4; j <= N; j++) {
                dp[j] = dp[j - 3] + dp[j - 2] + dp[j - 1];
            }

            sb.append(dp[N]).append("\n");
        }

        System.out.println(sb);
    }

}
