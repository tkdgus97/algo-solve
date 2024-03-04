import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//문제 풀이용
public class Main {
    private static class Village{
        long v;
        long p;
        public Village(long v, long p) {
            this.v = v;
            this.p = p;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());

        List<Village> list = new ArrayList<>();
        long total = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            list.add(new Village(v,p));
            total += p;
        }

        Collections.sort(list, (o1, o2) -> Long.compare(o1.v , o2.v));

        long people = 0;

        for (Village village : list) {
            people += village.p;
            if ((total + 1) / 2 <= people) {
                System.out.println(village.v);
                break;
            }
        }
    }

}
