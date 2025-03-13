import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int n = stoi(st.nextToken());

        int[] dp = new int[n];
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = stoi(st.nextToken());
        }
        dp[0] = 1;
        int result = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            int max = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    max = Math.max(dp[j] + 1, max);
                }
            }
            dp[i] = max;
            result = Math.max(result, dp[i]);
        }

        System.out.println(result);
    }


    private static int stoi(String v) {
        return Integer.parseInt(v);
    }
}