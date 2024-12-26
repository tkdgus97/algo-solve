import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    private static int[] dx = {-1,0,1,0};
    private static int[] dy = {0,1,0,-1};
    private static int[][] arr = new int[5][5];
    private static HashSet<Integer> nums = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        arr = new int[5][5];

        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                arr[i][j] = stoi(st.nextToken());
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                dfs(0,String.valueOf(arr[i][j]), i,j);
            }
        }
        System.out.println(nums.size());
    }

    private static void dfs(int n, String s, int x, int y) {
        if (n == 5) {
            int v = stoi(s);
            nums.add(v);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5) {
                dfs(n + 1, s + arr[nx][ny], nx, ny);
            }
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
