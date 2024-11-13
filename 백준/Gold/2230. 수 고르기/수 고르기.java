import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int MOD = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = stoi(st.nextToken());
        int m = stoi(st.nextToken());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = stoi(st.nextToken());
        }

        Arrays.sort(arr);
        int l = 0;
        int result = Integer.MAX_VALUE;
        for (int r = 0; r < n; r++) {
            if (arr[r] - arr[l] < m) {
                continue;
            }
            if (arr[r] - arr[l] == m) {
                result = m;
                break;
            }
            while ((arr[r] - arr[l]) >= m) {
                result = Math.min(result, arr[r] - arr[l]);
                l++;
            }
        }
        System.out.println(result);
    }


    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
