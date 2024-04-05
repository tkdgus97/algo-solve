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

        int[][] dp = new int[N][3];
        int[][] board = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0] = board[0];
        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.max(dp[i - 1][0] + board[i][0], dp[i - 1][1] + board[i][0]);
            dp[i][1] = Math.max(dp[i - 1][0] + board[i][1], Math.max(dp[i - 1][1] + board[i][1], dp[i - 1][2] + board[i][1]));
            dp[i][2] = Math.max(dp[i - 1][1] + board[i][2], dp[i - 1][2] + board[i][2]);
        }

        int max = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            max = Math.max(dp[N - 1][i], max);
        }

        dp = new int[N][3];
        dp[0] = board[0];
        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.min(dp[i - 1][0] + board[i][0], dp[i - 1][1] + board[i][0]);
            dp[i][1] = Math.min(dp[i - 1][0] + board[i][1], Math.min(dp[i - 1][1] + board[i][1], dp[i - 1][2] + board[i][1]));
            dp[i][2] = Math.min(dp[i - 1][1] + board[i][2], dp[i - 1][2] + board[i][2]);
        }

        for (int i = 0; i < 3; i++) {
            min = Math.min(dp[N - 1][i], min);
        }
        System.out.println(max + " " + min);
    }
}
