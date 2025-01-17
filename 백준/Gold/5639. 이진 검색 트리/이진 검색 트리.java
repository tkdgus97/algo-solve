import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node {
        int v;
        Node l;
        Node r;

        public Node(int v) {
            this.v = v;
        }
    }
    private static Node root;

    private static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String s;
        while (true) {
            s = br.readLine();
            if (s == null || s.equals("")) {
                break;
            }
            int v = stoi(s);
            if (root == null) {
                root = new Node(v);
            } else {
                add(root, v);
            }
        }

        aorder(root);
        System.out.println(sb);
    }
    private static void aorder (Node n) {
        if (n.l != null) {
            aorder(n.l);
        }
        if (n.r != null) {
            aorder(n.r);
        }
        sb.append(n.v).append("\n");
    }
    private static void add(Node n, int v) {
        if (n.v > v) {
            if (n.l == null) {
                n.l = new Node(v);
                return;
            }
            add(n.l, v);
        } else {
            if (n.r == null) {
                n.r = new Node(v);
                return;
            }
            add(n.r, v);
        }
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}