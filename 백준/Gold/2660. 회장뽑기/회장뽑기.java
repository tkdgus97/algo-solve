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

        List<Integer>[] adjList = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == - 1 && b == -1) {
                break;
            }

            adjList[a].add(b);
            adjList[b].add(a);
        }

        int[] result = new int[N + 1];
        int min = Integer.MAX_VALUE;
        int cnt = 0;
        for (int i = 1; i <=N ; i++) {
            result[i] = bfs(i,N, adjList);
            min = Math.min(min, result[i]);
        }

        for (int i = 1; i <= N; i++) {
            if (min == result[i]) {
                cnt++;
                sb.append(i).append(" ");
            }
        }
        System.out.println(min + " " + cnt);
        System.out.println(sb);
    }

    private static int bfs(int s, int N, List<Integer>[] adList) {
        boolean[] visit = new boolean[N + 1];

        Queue<int[]> Q = new ArrayDeque<>();
        visit[s] = true;
        Q.add(new int[] {s, 0});
        int t = Integer.MIN_VALUE;
        while(!Q.isEmpty()) {
            int[] now = Q.poll();

            t = Math.max(now[1] , t);
            for (Integer i : adList[now[0]]) {
                if (!visit[i]) {
                    visit[i] = true;
                    Q.add(new int[] {i, now[1] + 1});
                }
            }
        }
        return t;
    }

}
