import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = stoi(br.readLine());

        int[] rank = new int[n + 1];

        long rSum = 0;
        int lIdx = 1;
        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int r = stoi(st.nextToken());
            arr[i] = r;
        }

        Arrays.sort(arr);

        for (int i = 1; i <= n; i++) {
            if (arr[i] != i) {
                rSum += Math.abs(arr[i]- i);
            }
        }

        System.out.println(rSum);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
