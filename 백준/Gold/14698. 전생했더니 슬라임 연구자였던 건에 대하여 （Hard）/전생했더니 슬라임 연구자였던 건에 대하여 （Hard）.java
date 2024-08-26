import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        long MOD = 1000000007;
        int t = Integer.parseInt(st.nextToken());
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());

            PriorityQueue<Long> pq = new PriorityQueue<>();
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                pq.add(Long.parseLong(st.nextToken()));
            }

            long result = 1;
            while (pq.size() > 1) {
                long v1 = pq.poll();
                long v2 = pq.poll();
                long tmp = v1 * v2;
                result *= tmp % MOD;
                result %= MOD;
                pq.add(tmp);
            }

            sb.append(result).append("\n");
        }

        System.out.println(sb);
    }

}
