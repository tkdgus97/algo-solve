import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int s;
        int e;
        int len;

        public Node(int s, int e, int len) {
            this.s = s;
            this.e = e;
            this.len = len;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = stoi(st.nextToken());
        int d = stoi(st.nextToken());

        Node[] loads = new Node[n];
        boolean[] isLoad = new boolean[10000];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s = stoi(st.nextToken());
            int e = stoi(st.nextToken());
            int l = stoi(st.nextToken());
            loads[i] = new Node(s, e, l);
            isLoad[e] = true;
        }

        int[] dp = new int[d + 1];
        for (int i = 0; i <= d; i++) {
            dp[i] = i;
        }

        for (int i = 1; i <= d; i++) {
            dp[i] = Math.min(dp[i], dp[i - 1] + 1);
            if (isLoad[i]){
                for (int j = 0; j < n; j++) {
                    if (loads[j].e == i) {
                        dp[i] = Math.min(dp[i], dp[loads[j].s] + loads[j].len);
                    }
                }
            }
        }
        System.out.println(dp[d]);
    }


    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
