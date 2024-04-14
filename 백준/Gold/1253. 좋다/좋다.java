import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

//문제 풀이용
public class Main {

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] nums = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        int result = 0;
        for (int i = 0; i < n; i++) {
            int lp = 0;
            int rp = n - 1;
            int t = nums[i];

            while (lp < rp) {
                if (nums[lp] + nums[rp] == t) {
                    if (lp != i && rp != i) {
                        result++;
                        break;
                    } else if (lp == i) {
                        lp++;
                    } else if (rp == i) {
                        rp--;
                    }
                } else if (nums[lp] + nums[rp] < t) {
                    lp++;
                } else if (nums[lp] + nums[rp] > t) {
                    rp--;
                }
            }
        }

        System.out.println(result);
    }
}
