import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int t;
    private static List<Integer>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());

        int n = stoi(st.nextToken());
        t = stoi(st.nextToken());

        adj = new List[t + 1];
        for (int i = 0; i <= t; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int y = stoi(st.nextToken());
            int x = stoi(st.nextToken());

            adj[x].add(y);
        }

        if (adj[t].isEmpty()) {
            System.out.println(-1);
        } else {
            int re = bfs();
            System.out.println(re);
        }

    }

    private static int bfs() {
        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{0, 0, 0});

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] now = q.poll();

                if (now[0] == t) return now[2];

                for (int nx = (now[0] - 2); nx <= (now[0] + 2); nx++) {
                    if (nx < 0 || nx > t) continue;

                    for (int j = 0; j < adj[nx].size(); j++) {
                        int ny =  adj[nx].get(j);
                        if (Math.abs(now[1] - ny) <= 2) {
                            q.add(new int[]{nx, ny, now[2] + 1});
                            adj[nx].remove(j);
                            j--;
                        }
                    }
                }
            }
        }

        return -1;
    }


    private static boolean check(int nx, int ny) {
        return nx >= 0 && nx <= t && ny >= 0 && ny <= 1000000;
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}