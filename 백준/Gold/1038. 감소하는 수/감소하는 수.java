import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static List<Long> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        for (long i = 0; i < 10; i++) {
            rec(i);
        }
        if (n >= list.size()) {
            System.out.println(-1);
            return;
        }
        Collections.sort(list);
        System.out.println(list.get(n));
    }

    private static void rec(long num) {
        list.add(num);
        for (int i = 0; i < num % 10; i++) {
            rec(num * 10 + i);
        }
    }
}
