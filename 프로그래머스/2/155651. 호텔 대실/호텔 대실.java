class Solution {
    public int solution(String[][] book_time) {
        int answer = surfix(book_time);;
        return answer;
    }

    private static int surfix(String[][] times) {
        int max = Integer.MIN_VALUE;
        int[] arr = new int[1500];
        
        for(String[] time : times) {
            arr[convertTime(time[0])] += 1;
            arr[convertTime(time[1]) + 10] -= 1;
        }
        
        for(int i = 1; i < 1500; i++) {
            arr[i] += arr[i - 1];
            max = Math.max(arr[i], max);
        }
        
        return max;
    }
    
    private static int convertTime(String time) {
        String[] s = time.split(":");
        
        return (Integer.parseInt(s[0])*60) + Integer.parseInt(s[1]);
    }
}