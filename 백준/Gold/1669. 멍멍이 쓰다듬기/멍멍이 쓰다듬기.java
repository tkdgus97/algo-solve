import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int x = stoi(st.nextToken());
        int y = stoi(st.nextToken());

        if (x == y) {
            System.out.println(0);
            return;
        }
        int num = y - x;

        long n = 1;
        while (n * n <= num) {
            n++;
        }

        n -= 1;
        long day = 2 * n - 1;
        long diff = num - n*n;
        while (diff > 0) {
            diff -= Math.min(n, diff);
            day++;
        }
        System.out.println(day);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
