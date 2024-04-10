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
        int K = Integer.parseInt(st.nextToken());

        ArrayDeque<int[]>[] list = new ArrayDeque[M];
        for (int i = 0; i < M; i++) {
            list[i] = new ArrayDeque<>();
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            list[i % M].add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i, i % M});
        }

        int t = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0]) {
                if (o1[1] == o2[1]) {
                    return o1[3] - o2[3];
                }
                return o2[1] - o1[1];
            }

            return o2[0] - o1[0];
        });
        for (int i = 0; i < M; i++) {
            if (!list[i].isEmpty()) {
                pq.add(list[i].poll());
            }
        }
        while (!pq.isEmpty()) {
            int[] p = pq.poll();

            if (p[2] == K) {
                break;
            }
            if (!list[p[3]].isEmpty()) pq.add(list[p[3]].poll());
            t++;
        }

        System.out.println(t);
    }
}
