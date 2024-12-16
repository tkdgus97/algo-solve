import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        int h = stoi(st.nextToken());
        int w = stoi(st.nextToken());

        int[] wall = new int[w];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < w; i++) {
            wall[i] = stoi(st.nextToken());
        }
        int result = 0;
        for (int i = 1; i < w - 1; i++) {
            int l = 0;
            int r = 0;
            for (int j = 0; j < i; j++) {
                r = Math.max(r, wall[j]);
            }
            for (int j = i + 1; j < w; j++) {
                l = Math.max(l,wall[j]);
            }

            if (r > wall[i] && l > wall[i]) {
                int v = Math.min(r, l);
                result += (v - wall[i]);
            }
        }

        System.out.println(result);
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}
