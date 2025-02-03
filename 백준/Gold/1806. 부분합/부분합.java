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
        int s = stoi(st.nextToken());

        int[] arr = new int[n];

        int sum = 0;
        int len = Integer.MAX_VALUE;
        int l = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = stoi(st.nextToken());
        }

        for (int r = 0; r < n; r++) {
            sum += arr[r];

            while (sum >= s) {
                len = Math.min(len, (r - l) + 1);
                sum -= arr[l++];
            }
        }
        System.out.println(len == Integer.MAX_VALUE ? 0 : len);
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }


}