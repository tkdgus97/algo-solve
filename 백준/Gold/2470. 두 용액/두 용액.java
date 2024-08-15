import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            int t = Integer.parseInt(st.nextToken());
            arr[i] = t;
        }

        Arrays.sort(arr);

        int l = 0;
        int r = n - 1;

        int san = arr[n - 1];
        int al = arr[0];

        int result = Integer.MAX_VALUE;
        while (l < r) {
            int sum = arr[l] + arr[r];

            if (result > Math.abs(sum)) {
                san = arr[r];
                al = arr[l];
                result = Math.abs(sum);
            }

            if (sum > 0) {
                r--;
            } else {
                l++;
            }

        }

        System.out.println(al + " " + san);
    }

}
