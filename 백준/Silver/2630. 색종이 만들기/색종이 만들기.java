import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//문제 풀이용
public class Main {
    private static int[][] map;
    private static int blue = 0, white = 0;

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

        System.out.println(white);
        System.out.println(blue);
    }

    private static void func(int size, int x, int y) {
        int start = map[x][y];
        boolean eq = true;
        if (size == 1) {
            if (map[x][y] == 0) white++;
            else blue++;
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
            if (map[x][y] == 0) white++;
            else blue++;
            return;
        }
        int newSize = size / 2;
        func(newSize, x, y);
        func(newSize, x, y + newSize);
        func(newSize, x + newSize, y);
        func(newSize, x + newSize, y + newSize);

    }

}
