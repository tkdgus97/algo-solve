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

        int r = Integer.MIN_VALUE;
        int l = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = stoi(st.nextToken());
            r = Math.max(r, arr[i]);
        }

        st = new StringTokenizer(br.readLine());
        int m = stoi(st.nextToken());

        while (l <= r) {
            int mid = (l + r) / 2;

            long sum = 0;
            for (int i = 0; i < n; i++) {
                if (sum > m) break;
                if (arr[i] <= mid) {
                    sum += arr[i];
                } else {
                    sum += mid;
                }
            }

            if (sum <= m) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        System.out.println(r);
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}