import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//문제 풀이용
public class Main {
    private static int[][] map;
    private static int f = 0, s = 0, t = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        func(N, 0, 0);

        System.out.println(f);
        System.out.println(s);
        System.out.println(t);
    }

    private static void func(int size, int x, int y) {
        int start = map[x][y];
        boolean eq = true;
        if (size == 1) {
            if (map[x][y] == -1) f++;
            else if (map[x][y] == 0) s++;
            else t++;
            return;
        }
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (start != map[i][j]) {
                    eq = false;
                    break;
                }
            }
            if (!eq) break;
        }

        if (eq) {
            if (map[x][y] == -1) f++;
            else if (map[x][y] == 0) s++;
            else t++;
            return;
        }

        int newSize = size / 3;
        for (int i = x; i < x + size; i += newSize) {
            for (int j = y; j < y + size; j += newSize) {
                func(newSize, i , j);
            }
        }

    }

}
