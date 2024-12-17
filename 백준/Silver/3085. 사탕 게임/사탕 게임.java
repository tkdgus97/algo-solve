import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[] dx = {0, 1};
    private static int[] dy = {1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        int n = stoi(st.nextToken());

        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for (int j = 0; j < n; j++) {
                arr[i][j] = s.charAt(j) - 'A';
            }
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 2; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (check(nx, ny, n)) {
                        swap(i,j, nx, ny, arr);
                        int cnt = getMaxCount(arr, n);
                        swap(nx, ny, i,j, arr);

                        max = Math.max(cnt, max);
                    }
                }
            }
        }

        System.out.println(max);
    }
    private static int getMaxCount(int[][] arr, int n) {
        int max = 0;
        for (int i = 0; i < n; i++) {
            int r = arr[i][0];
            int c = arr[0][i];
            int rcnt = 1;
            int ccnt = 1;
            for (int k = 1; k < n; k++) {
                if (arr[i][k] == r) {
                    rcnt++;
                } else {
                    rcnt = 1;
                    r = arr[i][k];
                }

                if (arr[k][i] == c) {
                    ccnt++;
                } else {
                    ccnt = 1;
                    c = arr[k][i];
                }
                max = Math.max(Math.max(rcnt, ccnt), max);
            }
        }
        return max;
    }

    private static void swap(int r1, int c1, int r2, int c2, int[][] arr) {
        int tmp = arr[r1][c1];
        arr[r1][c1] = arr[r2][c2];
        arr[r2][c2] = tmp;
    }

    private static boolean check(int nx, int ny, int n) {
        return nx >= 0 && nx < n && ny >= 0 && ny < n;
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}
