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

        int[] dp = new int[N + 1];
        int[] pre = new int[N + 1];

        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + 1;
            pre[i] = i - 1;
            if (i % 2 == 0 && dp[i / 2] + 1 < dp[i]) {
                dp[i] = dp[i / 2] + 1;
                pre[i] = i / 2;
            }
            if (i % 3 == 0 && dp[i / 3] + 1 < dp[i]) {
                dp[i] = dp[i / 3] + 1;
                pre[i] = i / 3;
            }
        }
        sb.append(dp[N]).append("\n");
        sb.append(N).append(" ");
        for (int i = N; i >= 2; i = pre[i]) {
            sb.append(pre[i]).append(" ");
        }
        System.out.println(sb);
    }

}
