import java.util.*;
import java.io.*;

public class Main {
    private static final int MAX_SIZE = 5;

    static class Info implements Comparable<Info>{
        int v;
        int t;
        int r;
        int c;

        public Info(int v,int t,int r,int c) {
            this.v = v;
            this.t = t;
            this.r = r;
            this.c = c;
        }

        public int compareTo(Info o) {
            if(this.v != o.v) return o.v - this.v;
            if(this.t != o.t) return this.t - o.t;
            if(this.c != o.c) return this.c - o.c;
            return this.r - o.r;
        }
    }
    private static int[] dx = {-1,0,1,0};
    private static int[] dy = {0,1,0,-1};
    private static int k, m;
    private static int[][] map = new int[MAX_SIZE][MAX_SIZE];
    private static int[] peace;
    private static List<Info> li;
    private static List<int[]> locs;
    private static int peace_idx = 0;
    private static int[] scores;
    private static int score;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        k = stoi(st.nextToken());
        m = stoi(st.nextToken());
        peace = new int[m];
        scores = new int[k + 1];
        for(int r = 0; r < MAX_SIZE; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < MAX_SIZE; c++) {
                map[r][c] = stoi(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            peace[i] = stoi(st.nextToken());
        }

        for(int t = 1; t <= k; t++) {
            score = 0;
            if(!simul()) break;
            scores[t] = score;
        }

        for(int t = 1; t <= k; t++) {
            if(scores[t] == 0) break;
            System.out.print(scores[t] + " ");
        }
    }

    private static boolean simul() {
        li = new ArrayList<>();
        for(int r = 1; r <= 3; r++) {
            for(int c = 1; c <= 3; c++){
                spin(r, c);
            }
        }
        if(li.size() == 0) {
            return false;
        }
        Collections.sort(li);

        mapTurn(li.get(0));

        while(true) {
            if(!setNewPeace()) break;
        }

        // for(int x = 0; x < MAX_SIZE; x++) {
        //     System.out.println(Arrays.toString(map[x]));
        // }

        return true;
    }

    private static boolean setNewPeace() {
        locs = new ArrayList<>();
        boolean[][] visit = new boolean[MAX_SIZE][MAX_SIZE];
        for(int x = 0; x < MAX_SIZE; x++) {
            for(int y = 0; y < MAX_SIZE; y++) {
                if(!visit[x][y]) {
                    pbfs(x, y, visit);
                }
            }
        }
        if(locs.size() < 3) return false;

        locs.sort((o1,o2) -> {
            if(o1[1] != o2[1]) return o1[1] - o2[1];
            return o2[0] - o1[0];
        });

        score += locs.size();

        for(int i = 0; i < locs.size(); i++) {
            int[] loc = locs.get(i);
            map[loc[0]][loc[1]] = peace[peace_idx++];
        }
        return true;
    }
    private static void pbfs(int x, int y, boolean[][] visit) {
        visit[x][y] = true;
        int num = map[x][y];
        Queue<int[]> q = new LinkedList<>();
        List<int[]> tmp = new ArrayList<>();
        q.add(new int[] {x, y});
        tmp.add(new int[] {x, y});

        while(!q.isEmpty()) {
            int[] now = q.poll();

            for(int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if(rangeCheck(nx, ny) && !visit[nx][ny] && map[nx][ny] == num) {
                    q.add(new int[] {nx,ny});
                    visit[nx][ny] = true;
                    tmp.add(new int[] {nx, ny});
                }
            }
        }

        if(tmp.size() >= 3) {
            for(int i = 0; i < tmp.size(); i++) {
                locs.add(tmp.get(i));
            }
        }
    }
    private static void mapTurn(Info turn) {
        int[][] copy = new int[MAX_SIZE][MAX_SIZE];
        for(int x = 0; x < MAX_SIZE; x++) {
            for(int y = 0; y < MAX_SIZE; y++) {
                copy[x][y] = map[x][y];
            }
        }

        for(int t = 0; t <= turn.t; t++) {
            int v = 2;
            for(int x = turn.r - 1; x <= turn.r + 1; x++) {
                int idx = 0;
                for(int y = turn.r - 1; y <= turn.r + 1; y++) {
                    copy[y][turn.c - 1 + v] = map[x][turn.c - 1 + idx];
                    idx++;
                }
                v--;
            }
            for(int x = turn.r - 1; x <= turn.r + 1; x++) {
                for(int y = turn.c - 1; y <= turn.c + 1; y++) {
                    map[x][y] = copy[x][y];
                }
            }
        }

    }

    private static void spin(int r, int c) {
        // System.out.println("start : " + (r + 1) + " " + (c + 1));
        int[][] copy = new int[MAX_SIZE][MAX_SIZE];
        for(int x = 0; x < MAX_SIZE; x++) {
            for(int y = 0; y < MAX_SIZE; y++) {
                copy[x][y] = map[x][y];
            }
        }
        for(int t = 0; t < 3; t++) {
            int[][] tmp = new int[MAX_SIZE][MAX_SIZE];
            for(int x = 0; x < MAX_SIZE; x++) {
                for(int y = 0; y < MAX_SIZE; y++) {
                    tmp[x][y] = copy[x][y];
                }
            }
            int v = 2;
            for(int x = r - 1; x <= r + 1; x++) {
                int idx = 0;
                for(int y = r - 1; y <= r + 1; y++) {
                    tmp[y][c - 1 + v] = copy[x][c - 1 + idx];
                    idx++;
                }
                v--;
            }
            for(int x = r - 1; x <= r + 1; x++) {
                for(int y = c - 1; y <= c + 1; y++) {
                    copy[x][y] = tmp[x][y];
                }
            }

            boolean[][] visit = new boolean[MAX_SIZE][MAX_SIZE];
            int cnt = 0;
            for(int x = 0; x < MAX_SIZE; x++) {
                for(int y = 0; y < MAX_SIZE; y++) {
                    if(!visit[x][y]) {
                        int result = bfs(x, y, visit, tmp);
                        if(result >= 3) {
                            cnt += result;
                        }
                    }
                }
            }
            // System.out.println("spin "+ (t + 1)*90 + "도 " + cnt);

            if(cnt > 0) {
                li.add(new Info(cnt, t, r, c));
            }
        }
    }

    private static int bfs(int x, int y, boolean[][] visit, int[][] arr) {
        visit[x][y] = true;
        int num = arr[x][y];
        Queue<int[]> q = new LinkedList<>();
        int count = 1;
        q.add(new int[] {x, y});

        while(!q.isEmpty()) {
            int[] now = q.poll();

            for(int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if(rangeCheck(nx, ny) && !visit[nx][ny] && arr[nx][ny] == num) {
                    q.add(new int[] {nx,ny});
                    visit[nx][ny] = true;
                    count++;
                }
            }
        }

        return count;
    }

    private static boolean rangeCheck(int nx, int ny) {
        return nx >= 0 && nx < MAX_SIZE && ny >= 0 && ny < MAX_SIZE;
    }
    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}