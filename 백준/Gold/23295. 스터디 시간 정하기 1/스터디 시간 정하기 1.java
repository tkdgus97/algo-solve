import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        long[] sum = new long[100001];
        
        int maxTime = Integer.MIN_VALUE;
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            
            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                maxTime = Math.max(maxTime, e);
                sum[s]++;
                sum[e]--;
            }
        }
        for (int i = 1; i <= maxTime; i++) {
            sum[i] += sum[i - 1];
        }
        for (int i = 1; i <= maxTime; i++) {
            sum[i] += sum[i - 1];
        }
        int sTime = 0, eTime = t;
        long max = sum[t - 1];

        for (int i = t; i < maxTime; i++) {
            long i1 = sum[i] - sum[i - t];
            if (max < i1) {
                sTime = i - t + 1 ;
                eTime = i + 1;
                max = i1;
            }
        }
        System.out.println(sTime + " " + eTime);
    }
}
