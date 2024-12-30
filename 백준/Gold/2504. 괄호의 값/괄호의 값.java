import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        String s = st.nextToken();

        Stack<Character> stack = new Stack<>();
        int tmp = 1;
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(c);
                tmp *= 2;
            } else if (c == '[') {
                stack.push(c);
                tmp *= 3;
            } else {
                if (stack.isEmpty()) {
                    System.out.println(0);
                    return;
                }
                if (c == ')') {
                    char prev = stack.peek();
                    if (prev != '(') {
                        System.out.println(0);
                        return;
                    }
                    if (s.charAt(i - 1) == '(') {
                        result += tmp;
                    }
                    stack.pop();
                    tmp /= 2;
                } else if (c == ']') {
                    char prev = stack.peek();
                    if (prev != '[') {
                        System.out.println(0);
                        return;
                    }
                    if (s.charAt(i - 1) == '[') {
                        result += tmp;
                    }
                    stack.pop();
                    tmp /= 3;
                }
            }
        }

        if (!stack.isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(result);
        }

    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}

