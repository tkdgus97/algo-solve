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
        MyQ q = new MyQ();
        int n = stoi(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String oper = st.nextToken();
            if (oper.equals("push")) {
                int v = stoi(st.nextToken());
                q.push(v);
            } else if (oper.equals("pop")) {
                sb.append(q.pop()).append("\n");
            } else if (oper.equals("size")) {
                sb.append(q.size()).append("\n");
            } else if (oper.equals("empty")) {
                sb.append(q.isEmpty() ? 1 : 0).append("\n");
            } else if (oper.equals("front")) {
                sb.append(q.front()).append("\n");
            } else if (oper.equals("back")) {
                sb.append(q.back()).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}

class MyQ {
    private final int DEFAULT_CAPACITY = 100;
    private int[] arr;
    private int size;
    private int f;
    private int t;

    public MyQ() {
        this.arr = new int[DEFAULT_CAPACITY];
        this.size = 0;
        this.f = 0;
        this.t = 0;
    }

    private void resize() {
        int originLen = arr.length;
        int[] newArr = new int[originLen + DEFAULT_CAPACITY];

        for (int i = 1, j = f + 1; i <= size; i++, j++) {
            newArr[i] = arr[j % originLen];
        }

        this.arr = newArr;
        this.f = 0;
        this.t = size;
    }

    public void push(int v) {
        if ((t + 1) % arr.length == f) {
            resize();
        }

        t = (t + 1) % arr.length;
        arr[t] = v;
        this.size++;
    }

    public int pop() {
        if (this.isEmpty()) {
            return -1;
        }
        f = (f + 1) % arr.length;
        int v = arr[f];
        arr[f] = 0;
        this.size--;
        return v;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int front() {
        if (isEmpty()) {
            return -1;
        }
        return arr[(f + 1) % arr.length];
    }
    public int back() {
        if (isEmpty()) {
            return -1;
        }
        return arr[t % arr.length];
    }
}
