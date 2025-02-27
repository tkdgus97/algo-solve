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
        int l = stoi(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = stoi(st.nextToken());
        }

        Arrays.sort(arr);
        int cnt = 0;
        double last = 0;
        for (int i = 0; i < n; i++) {
            int v = arr[i];
            double s = v - 0.5;
            double e = v + 0.5;
            if (last <= s) {
                cnt++;
                last = s + l;
            }
        }
        System.out.println(cnt);
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }
}