import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] sum1 = new int[n];
        int[] sum2 = new int[n];
        int[] sum3 = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int v3 = Integer.parseInt(st.nextToken());

            sum1[i] = v1 + v2;
            sum2[i] = v2 + v3;
            sum3[i] = v1 + v3;
        }

        Arrays.sort(sum1);
        Arrays.sort(sum2);
        Arrays.sort(sum3);

        int s1 = 0;
        int s2 = 0;
        int s3 = 0;

        for (int i = n - 1; i >= n - k; i--) {
            s1 += sum1[i];
            s2 += sum2[i];
            s3 += sum3[i];
        }
        System.out.println(Math.max(s1, Math.max(s2, s3)));
    }

}
