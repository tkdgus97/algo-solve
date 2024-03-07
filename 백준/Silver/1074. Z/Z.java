import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static StringBuilder sb;
	private static long result = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
//		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		func((long) Math.pow(2, N) / 2, 0, 0, R, C, 0);
		System.out.println(result);
	}
	
	private static void func(long size, long x, long y, int R, int C, long num) {
		if (size == 0) {
			if (x == R && y == C) {
				result = num;
			}
			return;
		}
		
		long newSize = size / 2;
		
		if ((R >= x && R < x + size) && (C >= y && C < y + size)) {
			func(newSize, x, y, R, C, num);
		} else if((R >= x && R < x + size) && (C >= y + size && C < y + 2*size)) {
			func(newSize, x, y + size, R, C, num + size*size*1);
		} else if((R >= x + size && R < x + + 2*size) && (C >= y && C < y + size)) {
			func(newSize, x + size, y, R, C, num + size*size*2);
		} else {
			func(newSize, x + size, y + size, R, C, num + size*size*3);
		}
	}
	
}
