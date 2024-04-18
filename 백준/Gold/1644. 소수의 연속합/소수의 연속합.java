import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    private static List<Integer>[] trees;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());

        boolean[] isPrime = new boolean[n + 1];
        isPrime[0] = isPrime[1] = true;
        int primCnt = 0;
        int result = 0;
        for (int i = 2; i <= n; i++) {
            if (!isPrime[i]) {
                primCnt++;
                for (int j = i + i; j <= n; j += i) {
                    isPrime[j] = true;
                }
            }
        }

        int[] primeNum = new int[primCnt];
        int idx = 0;
        for (int i = 2; i <= n; i++) {
            if (!isPrime[i]) {
                primeNum[idx++] = i;
            }
        }

        int lt = 0;
        int sum = 0;
        for (int i = 0; i < primCnt; i++) {
            sum += primeNum[i];
            if (sum == n) result++;
            if (sum > n) {
                while(sum > n) {
                    sum -= primeNum[lt++];
                    if (sum == n) result++;
                }
            }
        }

        System.out.println(result);
    }


}
