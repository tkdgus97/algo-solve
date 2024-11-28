import java.util.*;
import java.io.*;
public class Main {
    static class Virus {
        int r;
        int c;
        int age;
        public Virus(int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
        }
    }
    private static int n,m,k;
    private static int[] dx = {-1,0,1,0, -1,-1, 1,1};
    private static int[] dy = {0,1,0,-1, -1,1,1,-1};
    private static int[][] food;
    private static ArrayDeque<Virus> viruses = new ArrayDeque<>();
    private static int[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = stoi(st.nextToken());
        m = stoi(st.nextToken());
        k = stoi(st.nextToken());

        food = new int[n][n];

        for(int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < n; c++) {
                food[r][c] = stoi(st.nextToken());
            }
        }

        List<Virus> tmpList = new ArrayList<>();

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = stoi(st.nextToken()) - 1;
            int c = stoi(st.nextToken()) - 1;
            int age = stoi(st.nextToken());
            tmpList.add(new Virus(r,c,age));
        }
        tmpList.sort((o1,o2) -> {
            return Integer.compare(o1.age, o2.age);
        });

        for(int i = 0; i < m; i++) {
            viruses.add(tmpList.get(i));
        }

        int result = simul();
        System.out.println(result);
    }

    private static int simul() {
        map = new int[n][n];
        for(int r = 0; r < n; r++) {
            Arrays.fill(map[r], 5);
        }
        int time = 0;
        while(time < k) {
            //1
            List<Virus> dead = eat();
            //2
            kill(dead);
            //3
            spread();
            //4
            addFood();

            time++;
        }
        return viruses.size();
    }
    private static List<Virus> eat() {
        List<Virus> dead = new ArrayList<>();
        int size = viruses.size();
        for(int i = 0; i < size; i++) {
            Virus virus = viruses.poll();
            if(map[virus.r][virus.c] >= virus.age) {
                map[virus.r][virus.c] -= virus.age;
                virus.age += 1;
                viruses.add(virus);
            } else {
                dead.add(virus);
            }
        }
        return dead;
    }

    private static void kill(List<Virus> dead) {
        for(int i = 0; i < dead.size(); i++) {
            Virus virus = dead.get(i);
            map[virus.r][virus.c] += (virus.age / 2);
        }
    }

    private static void spread() {
        List<Virus> tmpList = new ArrayList<>();
        while(!viruses.isEmpty()) {
            tmpList.add(viruses.poll());
        }
        int size = tmpList.size();
        for(int i = 0; i < size; i++) {
            Virus virus = tmpList.get(i);
            if(virus.age % 5 == 0) {
                for(int j = 0; j < 8; j++) {
                    int nx = virus.r + dx[j];
                    int ny = virus.c + dy[j];

                    if(checkRange(nx, ny)) {
                        viruses.add(new Virus(nx, ny, 1));
                    }
                }
            }
        }

        for(int i = 0; i < size; i++) {
            viruses.add(tmpList.get(i));
        }
    }

    private static void addFood() {
        for(int r = 0; r < n; r++) {
            for(int c = 0; c < n; c++) {
                map[r][c] += food[r][c];
            }
        }
    }
    private static boolean checkRange(int nx, int ny) {
        return (nx >= 0 && nx < n) && (ny >= 0 && ny < n);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}