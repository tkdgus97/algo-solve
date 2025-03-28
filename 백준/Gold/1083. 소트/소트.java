import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());

        int n = stoi(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = stoi(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int s = stoi(st.nextToken());

        int now = 0;
        while(s > 0 && now < n) {
            int idx = -1;
            int max = arr[now];
            for (int j = now + 1; j < n && j <= now + s; j++) {
                if (max < arr[j]) {
                    max = arr[j];
                    idx = j;
                }
            }

            if (idx == -1) {
                now++;
                continue;
            }
            s -= (idx - now);
            for (int i = idx; i >= now + 1; i--) {
                int tmp = arr[i];
                arr[i] = arr[i - 1];
                arr[i - 1] = tmp;
            }
        }

        for (int i = 0; i < n; i++) {
            sb.append(arr[i]).append(" ");
        };

        System.out.println(sb);
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }
}