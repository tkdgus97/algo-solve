import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    private static final int MOD = 9901;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        int l = 1;
        int r = arr[n - 1];

        while (l <= r) {
            int diff = (l + r) / 2;
            int cnt = 1;
            int position = 0;
            for(int i = 1; i < n; i++) {
                if (arr[i] - arr[position] >= diff) {
                    position = i;
                    cnt++;
                }
            }

            if (cnt >= c) {
                l = diff + 1;
            } else {
                r = diff - 1;
            }
        }
        System.out.println(l - 1);
    }

}
