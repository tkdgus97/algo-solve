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
        int m = stoi(st.nextToken());
        int[] arr = new int[n];

        int r = 0;
        int l = Integer.MIN_VALUE;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = stoi(st.nextToken());
            l = Math.max(l, arr[i]);
            r += arr[i];
        }

        while (l <= r) {
            int mid = (l + r) / 2;
            int cnt = 0;
            long tmp = 0;

            for (int i = 0; i < n; i++) {
                if (tmp + arr[i] > mid) {
                    cnt++;
                    tmp = 0;
                }
                tmp += arr[i];
            }
            if (tmp != 0) cnt++;
            if (cnt > m) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        System.out.println(l);
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}