import java.util.*;
import java.io.*;
public class Main {
    private static int[] dx = {-1, 0, 1, 0, 1, 1, -1, -1};
    private static int[] dy = {0, 1, 0, -1, 1, -1, 1, -1};
    private static int n,m,p,c,d;
    private static int[][] map;
    private static int lux, luy;
    private static Santa[] santas;
    private static boolean[] isOut;
    static class Santa {
        int idx;
        int r;
        int c;
        int score;
        int turn;

        public Santa(int idx, int r, int c, int score, int turn) {
            this.idx = idx;
            this.r = r;
            this.c = c;
            this.score = score;
            this.turn = turn;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        p = stoi(st.nextToken());
        c = stoi(st.nextToken());
        d = stoi(st.nextToken());

        st = new StringTokenizer(br.readLine());
        
        lux = stoi(st.nextToken());
        luy = stoi(st.nextToken());

        map = new int[n + 1][n + 1];
        santas = new Santa[p + 1];
        isOut = new boolean[p + 1];

        for(int i = 1; i <= p; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = stoi(st.nextToken());
            int r = stoi(st.nextToken());
            int c = stoi(st.nextToken());

            santas[idx] = new Santa(idx, r, c, 0, 1);
            map[r][c] = i;
        }

        simul();
        
        for(int i = 1; i <= p; i++) {
            System.out.print(santas[i].score + " ");
        }
    }
    private static void simul() {
        // for(int t = 1; t <= m; t++) {
            System.out.println("==============");
            // for(int i = 0; i <= n; i++) {
            //     System.out.println(Arrays.toString(map[i]));
            // }
            luMove();
            santaMove(t);
            if(end()) {
                break;
            }
        }
    }
    private static void luMove() {
        List<int[]> li = new ArrayList<>();

        for(int i = 1; i <= p; i++) {
            if(!isOut[i]) {
                int distance = (int) Math.pow(lux - santas[i].r, 2) + (int) Math.pow(luy - santas[i].c, 2);
                li.add(new int[] {santas[i].idx, santas[i].r, santas[i].c, distance});
            }
        }

        li.sort((o1, o2) -> {
            if(o1[3] != o2[3]) return o1[3] - o2[3];
            if(o1[1] != o2[1]) return o2[1] - o1[1];
            return o2[2] - o1[2];
        });

        int[] goSanta = li.get(0);
        int goDir = 0;
        int minDistance = Integer.MAX_VALUE;
        int goR = lux;
        int goC = luy;
        for(int i = 0; i < 8; i++) {
            int nx = lux + dx[i];
            int ny = luy + dy[i];

            if(rangeCheck(nx,ny)) {
                int diff = (int) Math.pow(nx - goSanta[1], 2) + (int) Math.pow(ny - goSanta[2], 2);
                if(minDistance > diff) {
                    minDistance = diff;
                    goDir = i;
                    goR = nx;
                    goC = ny;
                }
            }
        }

        if(map[goR][goC] > 0) {
            int idx = map[goR][goC];
            pushSanta(idx, goDir);
        }

        lux = goR;
        luy = goC;

        // System.out.println("루돌프 이동 : " + lux + " " + luy);
    }

    private static void santaMove(int time) {
        for(int i = 1; i <= p; i++) {
            // System.out.println(i + "번 산타 : " + isOut[i] + " " + santas[i].turn);
            if(isOut[i]) continue;
            if(santas[i].turn != time) continue;

            Santa santa  = santas[i];
            santa.turn++;

            // System.out.println(i + "번 산타 이전 : " + santa.r + " " + santa.c);
            int minDis = (int) Math.pow(santa.r - lux, 2) + (int) Math.pow(santa.c - luy, 2);
            int goDir = -1;
            for(int j = 0; j < 4; j++) {
                int nx = santa.r + dx[j];
                int ny = santa.c + dy[j];

                if(rangeCheck(nx, ny) && map[nx][ny] == 0) {
                    int dis = (int) Math.pow(nx - lux, 2) + (int) Math.pow(ny- luy, 2);
                    if(dis < minDis) {
                        goDir = j;
                        minDis = dis;
                    }
                }
            }

            if(goDir == -1) continue;
            map[santa.r][santa.c] = 0;
            santa.r += dx[goDir];
            santa.c += dy[goDir];
            if(santa.r == lux && santa.c == luy) {
                crashSanta(i, goDir);
            } else {
                map[santa.r][santa.c] = santa.idx;
            }

        }
    }

    private static void crashSanta(int idx, int dir) {
        dir = (dir + 2) % 4;
        int pushR = santas[idx].r + dx[dir]*d;
        int pushC = santas[idx].c + dy[dir]*d;

        santas[idx].score += d;

        if(!rangeCheck(pushR, pushC)) {
            isOut[idx] = true;
            return;
        }

        if(map[pushR][pushC] != 0) {
            push(map[pushR][pushC], dir);
        }

        santas[idx].r = pushR;
        santas[idx].c = pushC;
        map[pushR][pushC] = idx;
        santas[idx].turn += 2;
    }

    private static void pushSanta(int idx, int dir) {
        int pushR = santas[idx].r + dx[dir]*c;
        int pushC = santas[idx].c + dy[dir]*c;
        santas[idx].score += c;
        map[santas[idx].r][santas[idx].c] = 0;

        if(!rangeCheck(pushR, pushC)) {
            isOut[idx] = true;
            return;
        }

        if(map[pushR][pushC] != 0) {
            push(map[pushR][pushC], dir);
        }

        santas[idx].r = pushR;
        santas[idx].c = pushC;
        map[pushR][pushC] = idx;
        santas[idx].turn += 2;
    }

    private static void push(int idx, int dir) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visit = new boolean[p + 1];
        visit[idx] = true;
        q.add(idx);

        while(!q.isEmpty()) {
            int now = q.poll();
            int nx = santas[now].r + dx[dir];
            int ny = santas[now].c + dy[dir];

            System.out.println( nx + " " + ny);

            if(!rangeCheck(nx, ny)) {
                isOut[now] = true;
                break;
            }
            santas[now].r = nx;
            santas[now].c = ny;
            map[nx][ny] = now;
            if(map[nx][ny] > 0 && !visit[map[nx][ny]]) {
                q.add(map[nx][ny]);
            }
        }
    }
    private static boolean rangeCheck(int nx, int ny) {
        return nx >= 1 && nx <= n && ny >= 1 && ny <= n;
    }

    private static boolean end() {
        int cnt = 0;
        for(int i = 1; i <= p; i++) {
            if(!isOut[i]) {
                santas[i].score += 1;
                cnt++;
            }
        }

        return cnt > 0 ? false : true;
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}