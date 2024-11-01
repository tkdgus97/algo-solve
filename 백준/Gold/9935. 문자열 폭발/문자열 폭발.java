import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        String str = st.nextToken();
        st = new StringTokenizer(br.readLine());
        String boom = st.nextToken();
        int boomLen = boom.length();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));
            if (stack.size() >= boomLen) {
                boolean flag = true;
                for(int j = 0; j < boomLen; j++) {
                    if(stack.get(stack.size() - j - 1) != boom.charAt(boomLen - j - 1)) {
                        flag = false;
                        break;
                    }
                }
                if(flag) {
                    for(int j = 0; j < boomLen; j++) {
                        stack.pop();
                    }
                }
            }
        }
        if (!stack.isEmpty()) {
            StringBuilder result = new StringBuilder();
            for(char c : stack) result.append(c);
            System.out.println(result);
        } else {
            System.out.println("FRULA");
        }
    }
}
