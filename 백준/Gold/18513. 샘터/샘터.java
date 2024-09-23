import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int houseCnt = 0;
        Queue<int[]> q = new LinkedList<>();

        st = new StringTokenizer(br.readLine());

        HashSet<Integer> visit = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int t = Integer.parseInt(st.nextToken());
            q.add(new int[] {t, 1});
            visit.add(t);
        }

        long result = 0;
        while (!q.isEmpty()) {
            int[] now = q.poll();

            int left = now[0] - 1;
            int right = now[0] + 1;
            if (!visit.contains(left)) {
                houseCnt++;
                result += now[1];
                visit.add(left);
                q.add(new int[]{ left, now[1] + 1});
            }
            if (houseCnt == k) {
                break;
            }
            if (!visit.contains(right)) {
                houseCnt++;
                result += now[1];
                visit.add(right);
                q.add(new int[]{right, now[1] + 1});
            }
            if (houseCnt == k) {
                break;
            }
        }

        System.out.println(result);
    }
}
