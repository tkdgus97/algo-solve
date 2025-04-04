
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < 3; k++) {
            st = new StringTokenizer(br.readLine());
            int n = stoi(st.nextToken());
            int[] coin = new int[n];
            int[] cnt = new int[n];
            int sum = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int v = stoi(st.nextToken());
                int c = stoi(st.nextToken());
                coin[i] = v;
                cnt[i] = c;
                sum += (v * c);
            }
            int[][] dp = new int[n + 1][sum + 1];
            if (sum % 2 == 1) {
                sb.append(0).append("\n");
                continue;
            }
            dp[0][0] = 1;
            sum /= 2;
            for (int i = 1; i <= n; i++) {
                int v = coin[i - 1];
                int count = cnt[i - 1];
                for (int j = 0; j <= sum; j++) {
                    if (dp[i - 1][j] == 1) {
                        for (int l = 0; l <= count; l++) {
                            if (j + v * l <= sum) {
                                dp[i][j + v * l] = 1;
                            }
                        }
                    }
                }
            }
            sb.append(dp[n][sum]).append("\n");
        }

        System.out.println(sb);

    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }
}