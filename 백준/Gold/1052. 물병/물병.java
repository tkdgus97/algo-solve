import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        System.out.println(sol(n,k));
    }
    private static int sol(int n, int k) {
        int result = 0;
        while (Integer.bitCount(n) > k) {
            n++;
            result++;
        }
        return result;
    }
}
