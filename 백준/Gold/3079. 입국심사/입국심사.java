import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] time = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            time[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(time);
        long lt = 1;
        long rt = (long) time[n - 1] * m;
        long result = Long.MAX_VALUE;
        while(lt <= rt) {
            long mid = (lt + rt) / 2;

            long personCnt = 0;
            for (int i : time) {
                long cnt = mid/i;
                if (personCnt >= m) break;
                personCnt += cnt;
            }

            if (personCnt >= m) {
                rt = mid - 1;
                result = Math.min(mid, result);
            } else {
                lt = mid + 1;
            }
        }

        System.out.println(result);

    }

}
