import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static class Job {
        int t;
        int end;

        public Job(int t, int end) {
            this.t = t;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = stoi(st.nextToken());

        List<Job> jobs = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            //걸리는 시간
            int t = stoi(st.nextToken());
            //마감 시간
            int s = stoi(st.nextToken());
            jobs.add(new Job(t,s));
        }

        Collections.sort(jobs, (o1,o2) -> {
            return o1.end - o2.end;
        });
        int time = jobs.get(0).end - jobs.get(0).t;
        int lastTime = jobs.get(0).end - jobs.get(0).t;
        for (Job job : jobs) {
            int minStart = job.end - job.t;

            if (minStart < lastTime) {
                time -= (lastTime - minStart);
                lastTime = job.end;
            } else {
                lastTime += job.t;
            }

            if (time < 0) {
                time = -1;
                break;
            }
        }
        System.out.println(time);
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}
