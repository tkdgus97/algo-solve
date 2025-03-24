import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        char c;
        int idx;

        public Node(char c, int idx) {
            this.c = c;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        String s = st.nextToken();
        Stack<Node> stack = new Stack<>();
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty() || s.charAt(i) == '(') {
                stack.push(new Node(s.charAt(i), i));
            } else {
                if (s.charAt(i) == ')') {
                    Node now = stack.pop();

                    if (now.idx == i - 1) {
                        result += stack.size();
                    } else {
                        result += 1;
                    }
                }
            }
        }

        System.out.println(result);
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }
}