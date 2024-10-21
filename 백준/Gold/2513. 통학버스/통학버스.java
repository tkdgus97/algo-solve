import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> small = new PriorityQueue<>((o1,o2) -> {
            return o1[0] - o2[0];
        });
        PriorityQueue<int[]> big = new PriorityQueue<>((o1,o2) -> {
            return o2[0] - o1[0];
        });

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            //학교보다 오른쪽
            if (l > s) big.add(new int[] {l,p});
            else small.add(new int[] {l,p});
        }
        int result = 0;
        while (!small.isEmpty()) {
            int dis = 0;
            int ride = k;

            while (!small.isEmpty()) {
                int[] now = small.poll();

                if (dis == 0) dis += (s - now[0]);
                if (ride < now[1]) {
                    small.add(new int[] {now[0], now[1] - ride});
                    break;
                } else {
                    ride -= now[1];
                }
            }

            result += (dis * 2);
        }

        while (!big.isEmpty()) {
            int dis = 0;
            int ride = k;

            while (!big.isEmpty()) {
                int[] now = big.poll();

                if (dis == 0) dis += (now[0] - s);
                if (ride < now[1]) {
                    big.add(new int[] {now[0], now[1] - ride});
                    break;
                } else {
                    ride -= now[1];
                }
            }

            result += (dis * 2);
        }

        System.out.println(result);
    }

}
