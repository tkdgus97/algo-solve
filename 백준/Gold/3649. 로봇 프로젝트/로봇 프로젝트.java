import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//        StringTokenizer st = new StringTokenizer(br.readLine());
        String s = null;
        while ((s = br.readLine()) != null) {
            int x = stoi(s) * 10000000;
            int n = stoi(br.readLine());

            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                int v = stoi(br.readLine());
                arr[i] = v;
            }

            Arrays.sort(arr);
            int l = 0;
            int r = n - 1;
            boolean flag = false;
            while (l < r) {
                int sum = arr[l] + arr[r];
                if (sum == x) {
                    System.out.println("yes " + arr[l] + " " + arr[r]);
                    flag = true;
                    break;
                }
                if (sum > x) {
                    r--;
                } else {
                    l++;
                }
            }

            if (!flag) {
                System.out.println("danger");
            }
        }
    }
    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
