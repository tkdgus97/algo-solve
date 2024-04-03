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
        int M = Integer.parseInt(st.nextToken());

        int[] memory = new int[N + 1];
        int[] costs = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }
        int costTotal = 0;
        int min = Integer.MAX_VALUE;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
            costTotal += costs[i];
            if (memory[i] >= M) {
                min = Math.min(min, costs[i]);
            }
        }

        int[][] dp = new int[N + 1][costTotal + 1];

        for (int i = 1; i <= N; i++) {
            int mem = memory[i];
            int cost = costs[i];
            for (int j = 0; j <= costTotal; j++) {
                if (j >= cost) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - cost] + mem);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }

                if (dp[i][j] >= M) {
                    min = Math.min(min, j);
                }
            }
        }
        System.out.println(min);
    }
}
