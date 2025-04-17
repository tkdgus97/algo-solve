import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int maxA, maxB, maxC;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        maxA = stoi(st.nextToken());
        maxB = stoi(st.nextToken());
        maxC = stoi(st.nextToken());

        boolean[] result = bfs();

        for (int i = 0; i <= maxC; i++) {
            if (result[i]) sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    private static boolean[] bfs() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][][] visit = new boolean[201][201][201];
        q.add(new int[]{0, 0, maxC});

        boolean[] result = new boolean[maxC + 1];

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int a = now[0];
            int b = now[1];
            int c = now[2];

            if (visit[a][b][c]) continue;
            if (a == 0) result[c] = true;
            visit[a][b][c] = true;

            if (a + b >= maxB) q.add(new int[]{a - (maxB - b), maxB, c});
            else q.add(new int[]{0, a + b, c});
            if (a + c >= maxC) q.add(new int[]{a - (maxC - c), b, maxC});
            else q.add(new int[]{0, b, a + c});

            if (b + a >= maxA) q.add(new int[]{maxA, b - (maxA - a), c});
            else q.add(new int[]{b + a, 0, c});
            if (b + c >= maxC) q.add(new int[]{a, b - (maxC - c), maxC});
            else q.add(new int[]{a, 0, b + c});

            if (c + a >= maxA) q.add(new int[] {maxA, b, c - (maxA - a)});
            else q.add(new int[] {c + a, b, 0});
            if (c + b >= maxB) q.add(new int[] {a, maxB, c - (maxB - b)});
            else q.add(new int[] {a,c + b, 0});
        }

        return result;
    }


    private static int stoi(String v) {
        return Integer.parseInt(v);
    }
}
