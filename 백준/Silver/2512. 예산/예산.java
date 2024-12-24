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
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            arr[i] = stoi(st.nextToken());
            max = Math.max(max, arr[i]);
        }

        st = new StringTokenizer(br.readLine());
        int v = stoi(st.nextToken());

        int l = 0;
        int r = max;
        while (l <= r) {
            int mid = (l + r) / 2;
            long sum = 0;
            for (int i = 0; i < n; i++) {
                if (mid < arr[i]) sum += mid;
                else sum += arr[i];
            }

            if (sum > v) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        System.out.println(r);
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}

