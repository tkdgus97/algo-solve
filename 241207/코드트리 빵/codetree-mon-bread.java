import java.util.*;
import java.io.*;
public class Main {
    static class Person {
        int r;
        int c;
        int in;

        public Person(int r, int c, int in) {
            this.r = r;
            this.c = c;
            this.in = in;
        }
    }
    static class Node {
        int r;
        int c;
        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    private static int[] dx = {-1,0,0,1};
    private static int[] dy = {0,-1,1,0};
    private static int n,m;
    private static int[][] map;
    private static List<int[]> block;
    private static Node[] martes;
    private static Person[] persons;
    private static int exit_count = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        
        map = new int[n][n];
        martes = new Node[m + 1];
        persons = new Person[m + 1];
        for(int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < n; c++){
                map[r][c] = stoi(st.nextToken());
            }
        }

        for(int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            martes[i] = new Node(stoi(st.nextToken()) - 1, stoi(st.nextToken()) - 1);
            persons[i] = new Person(-1,-1,0);
        }

        for(int t = 1; ;t++) {
            // System.out.println("---------"+ t +"---------");
            // for(int i = 0; i < n; i++) {
            //     System.out.println(Arrays.toString(map[i]));
            // }
            block = new ArrayList<>();
            move();

            checkPoint();

            if(t <= m) {
                goCamp(t);
            }

            nodeBlock();

            if(exit_count == m) {
                System.out.println(t);
                break;
            }
        }
    }
    private static void move() {
        for(int i = 1; i <= m; i++) {
            if(persons[i].in == 0) continue;
            moveBfs(i);
        }
    }

    private static void moveBfs(int num) {
        boolean[][] visit = new boolean[n][n];
        Person p = persons[num];
        visit[p.r][p.c] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {p.r, p.c, -1,-1});
        int goR = -1;
        int goC = -1;
        while(!q.isEmpty()) {
            int[] now = q.poll();
            if(now[0] == martes[num].r && now[1] == martes[num].c) {
                goR = now[2];
                goC = now[3];
                break;
            }

            for(int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if(rangeCheck(nx, ny) && !visit[nx][ny] && map[nx][ny] != -1) {
                    visit[nx][ny] = true;
                    if(now[2] == -1) {
                        q.add(new int[] {nx, ny, nx, ny});
                    } else {
                        q.add(new int[] {nx, ny, now[2], now[3]});
                    }
                }
            }
        }
        // System.out.println(num + "번 이동 : " + goR + " " + goC);

        p.r = goR;
        p.c = goC;
    }

    private static void checkPoint() {
        for(int i = 1; i <= m; i++) {
            if(persons[i].in == 1 && persons[i].r == martes[i].r && persons[i].c == martes[i].c) {
                block.add(new int[] {martes[i].r, martes[i].c});
                persons[i].in = 0;
                exit_count++;
            }
        }
    }

    private static void goCamp(int idx) {
        Node m = martes[idx];
        boolean[][] visit = new boolean[n][n];
        visit[m.r][m.c] = true;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {m.r, m.c, 0});
        List<int[]> camps = new ArrayList<>();
        while(!q.isEmpty()) {
            int[] now = q.poll();

            for(int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if(rangeCheck(nx, ny) && !visit[nx][ny] && map[nx][ny] != -1) {
                    if(map[nx][ny] == 1) camps.add(new int[] {nx,ny, now[2] + 1});
                    q.add(new int[] {nx, ny, now[2] + 1});
                    visit[nx][ny] = true;
                }
            }
        }

        camps.sort((o1, o2) -> {
            if(o1[2] != o2[2]) return o1[2] - o2[2];
            if(o1[0] != o2[0]) return o1[0] - o2[0];
            return o1[1] - o2[1];
        });

        int[] camp = camps.get(0);
        persons[idx].r = camp[0];
        persons[idx].c = camp[1];
        persons[idx].in = 1;
        block.add(camp);
    }

    private static void nodeBlock() {
        for(int i = 0; i < block.size(); i++) {
            int[] b = block.get(i);
            map[b[0]][b[1]] = -1;
        }
    }

    private static boolean rangeCheck(int nx, int ny) {
        return nx >= 0 && nx < n && ny >= 0 && ny < n;
    }
    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}