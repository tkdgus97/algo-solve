import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = stoi(st.nextToken());
        st = new StringTokenizer(br.readLine());

        int[] bigArr = new int[n];

        for (int i = 0; i < n; i++) {
            bigArr[i] = stoi(st.nextToken());
        }

        int[] order = new int[n];

        for (int i = 0; i < n; i++) {
            int v = bigArr[i];
            int idx = 0;
            while (true) {
                if (v == 0 && order[idx] == 0) {
                    order[idx] = i + 1;
                    break;
                }
                if (order[idx] == 0) {
                    v--;
                }
                idx++;
            }
        }

        for (int i = 0; i < n; i++) {
            sb.append(order[i]).append(" ");
        }
        System.out.print(sb);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
