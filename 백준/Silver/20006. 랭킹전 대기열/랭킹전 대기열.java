
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static class Player implements Comparable<Player> {
        int level;
        String name;
        Player(int level, String name) {
            this.level = level;
            this.name = name;
        }

        @Override
        public int compareTo(Player o) {
            return this.name.compareTo(o.name);
        }
    }
    private static class Room {
        int min;
        int max;

        List<Player> players;

        public Room(int min, int max) {
            this.min = min;
            this.max = max;
            this.players = new ArrayList<>();
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int p = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int roomCnt = 0;
        Room[] rooms = new Room[300];
        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int level = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            boolean isIn = false;
            int levelMin = Math.max(level - 10, 0);
            int levelMax = level + 10;
            for (int j = 0; j < roomCnt; j++) {
                if (rooms[j].min <= level && rooms[j].max >= level && rooms[j].players.size() < m) {
                    rooms[j].players.add(new Player(level, name));
                    isIn = true;
                    break;
                }
            }

            if (!isIn) {
                rooms[roomCnt] = new Room(levelMin, levelMax);
                rooms[roomCnt].players.add(new Player(level, name));
                roomCnt++;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < roomCnt; i++) {
            if (rooms[i].players.size() == m) {
                sb.append("Started!").append("\n");
            } else {
                sb.append("Waiting!").append("\n");
            }
            Collections.sort(rooms[i].players);
            for (Player player : rooms[i].players) {
                sb.append(player.level).append(" ").append(player.name).append("\n");
            }
        }

        System.out.println(sb);
    }

}
