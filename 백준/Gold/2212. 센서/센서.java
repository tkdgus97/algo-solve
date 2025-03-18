
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int n = stoi(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int k = stoi(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] sensor = new int[n];

        if (n <= k) {
            System.out.println(0);
            return;
        }
        for (int i = 0; i < n; i++) {
            sensor[i] = stoi(st.nextToken());
        }

        Arrays.sort(sensor);

        int[] diff = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            diff[i] = sensor[i + 1] - sensor[i];
        }
        Arrays.sort(diff);
        int result = 0;
        for (int i = 0; i < n - k; i++) {
            result += diff[i];
        }

        System.out.println(result);
    }


    private static int stoi(String v) {
        return Integer.parseInt(v);
    }
}