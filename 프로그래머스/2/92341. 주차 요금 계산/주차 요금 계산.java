import java.util.*;
class Solution {
        public int[] solution(int[] fees, String[] records) {
        boolean[] car = new boolean[10000];
        int[] total = new int[10000];
        HashMap<Integer, Integer> timeRecord = new HashMap<>();
        int defaultTime = fees[0], defaultPrice = fees[1], unitTime = fees[2], unitPrice = fees[3];
        int maxTime = (23 * 60) + 59;
        for (String log : records) {
            String[] record = log.split(" ");

            String[] time = record[0].split(":");
            int totalTime = (Integer.parseInt(time[0]) * 60) + Integer.parseInt(time[1]);
            int num = Integer.parseInt(record[1]);
            if (record[2].equals("IN")) {
                car[num] = true;
                timeRecord.put(num, totalTime);
            } else if (record[2].equals("OUT")) {
                car[num] = false;
                total[num] += (totalTime - timeRecord.get(num));
                timeRecord.put(num,0);
            }
        }

        int[] sort = new int[timeRecord.size()];
        int[] answer = new int[timeRecord.size()];
        int i = 0;

        for (Integer carNum : timeRecord.keySet()) {
            sort[i++] = carNum;
            if (car[carNum]) {
                total[carNum] += (maxTime - timeRecord.get(carNum));
            }
            if (total[carNum] <= defaultTime) {
                timeRecord.put(carNum, defaultPrice);
            } else {
                int price = (int) (defaultPrice + (Math.ceil(((double) (total[carNum] - defaultTime) / unitTime)) * unitPrice));
                timeRecord.put(carNum, price);
            }
        }

        Arrays.sort(sort);

        i = 0;
        for (int c : sort) {
            answer[i++] = timeRecord.get(c);
        }

        return answer;
    }
}