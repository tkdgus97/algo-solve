import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//문제 풀이용
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int[] degree = new int[N + 1];
        int[] times = new int[N + 1];

        List<Integer>[] nodes = new List[N + 1];

        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            int time = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            degree[i] = M;
            times[i] = time;

            for (int j = 0; j < M; j++) {
                nodes[Integer.parseInt(st.nextToken())].add(i);
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return o1[1] - o2[1];
        });

        int[] dis = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                pq.add(new int[]{i, times[i]});
            }
        }

        int result = 0;

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            result = now[1];
            for (int i = 0; i < nodes[now[0]].size(); i++) {
                degree[nodes[now[0]].get(i)] -= 1;
                if (degree[nodes[now[0]].get(i)] == 0) {
                    pq.add(new int[]{ nodes[now[0]].get(i), now[1] + times[nodes[now[0]].get(i)]});
                }
            }
        }

        System.out.println(result);

    }
}
