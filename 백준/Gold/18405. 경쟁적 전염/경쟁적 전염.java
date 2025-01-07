import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());

        int n = stoi(st.nextToken());
        int k = stoi(st.nextToken());

        int[][] arr = new int[n][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[0], o2[0]));
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = stoi(st.nextToken());
                if (arr[i][j] != 0) {
                    pq.add(new int[]{arr[i][j], i, j});
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        int s = stoi(st.nextToken());
        int tx = stoi(st.nextToken());
        int ty = stoi(st.nextToken());

        for (int i = 0; i < s; i++) {
            PriorityQueue<int[]> tmp = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[0], o2[0]));
            while (!pq.isEmpty()) {
                int[] now = pq.poll();
                for (int j = 0; j < 4; j++) {
                    int nx = now[1] + dx[j];
                    int ny = now[2] + dy[j];
                    if (check(n, nx, ny) && arr[nx][ny] == 0) {
                        arr[nx][ny] = now[0];
                        tmp.add(new int[]{arr[nx][ny], nx, ny});
                    }
                }
            }
            pq = tmp;
        }

        System.out.println(arr[tx - 1][ty - 1]);
    }

    private static boolean check(int n, int nx, int ny) {
        return nx >= 0 && nx < n && ny >= 0 && ny < n;
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}

