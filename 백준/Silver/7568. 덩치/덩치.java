import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int MAX_DAY = 14;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        int n = stoi(st.nextToken());

        int[][] person = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int w = stoi(st.nextToken());
            int k = stoi(st.nextToken());

            person[i][0] = w;
            person[i][1] = k;
        }
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if (j == i) continue;
                if (person[i][0] < person[j][0] && person[i][1] < person[j][1]) {
                    cnt++;
                }
            }
            result[i] = cnt + 1;
        }

        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}
