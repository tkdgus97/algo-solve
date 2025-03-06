import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.*;

public class Main {
    private static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static int dir = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int n = stoi(st.nextToken());

        st = new StringTokenizer(br.readLine());
        char[] m = st.nextToken().toCharArray();

        int maxR = 0;
        int maxC = 0;
        int minR = 0;
        int minC = 0;
        int nowR = 0;
        int nowC = 0;

        for (int i = 0; i < m.length; i++) {
            if (m[i] == 'F') {
                nowR += d[dir][0];
                nowC += d[dir][1];
                maxR = Math.max(nowR, maxR);
                maxC = Math.max(nowC, maxC);
                minR = Math.min(minR, nowR);
                minC = Math.min(minC, nowC);
            } else {
                turn(m[i]);
            }
        }

        int r = maxR - minR + 1;
        int c = maxC - minC + 1;
        int sR = Math.abs(minR);
        int sC = Math.abs(minC);

        char[][] map = new char[r][c];
        for (int i = 0; i < r; i++) {
            Arrays.fill(map[i], '#');
        }
        map[sR][sC] = '.';
        dir = 2;
        for (int i = 0; i < m.length; i++) {
            if (m[i] == 'F') {
                sR += d[dir][0];
                sC += d[dir][1];
                map[sR][sC] = '.';
            } else {
                turn(m[i]);
            }
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    private static void turn(char t) {
        if (t == 'L') dir = (dir - 1 + 4) % 4;
        if (t == 'R') dir = (dir + 1) % 4;
    }


    private static int stoi(String v) {
        return Integer.parseInt(v);
    }
}