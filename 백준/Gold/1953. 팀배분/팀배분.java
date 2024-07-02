import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    private static int n, m;

    private static int[] team;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        List<Integer>[] adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        team = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            int c = Integer.parseInt(st.nextToken());

            for (int j = 0; j < c; j++) {
                int h = Integer.parseInt(st.nextToken());
                adj[i].add(h);
            }
        }

        bfs(adj);
    }

    private static void bfs(List<Integer>[] adj) {
        Queue<Integer> q = new LinkedList<>();

        boolean[] visit = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                team[i] = 1;
                q.add(i);

                while (!q.isEmpty()) {
                    int now = q.poll();

                    for (int next : adj[now]) {
                        if (!visit[next]) {
                            visit[next] = true;
                            q.add(next);

                            if (team[now] == 0) team[next] = 1;
                            else team[next] = 0;
                        }
                    }
                }
            }

        }

        StringBuilder s1 = new StringBuilder();
        StringBuilder s2 = new StringBuilder();

        int cnt1 = 0;
        int cnt2 = 0;
        for (int j = 1; j <= n; j++) {
            if (team[j] == 1) {
                s1.append(j).append(" ");
                cnt1++;
            } else {
                s2.append(j).append(" ");
                cnt2++;
            }
        }


        System.out.println(cnt1);
        System.out.println(s1);
        System.out.println(cnt2);
        System.out.println(s2);

    }

}
