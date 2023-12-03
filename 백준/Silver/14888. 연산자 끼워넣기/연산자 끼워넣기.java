
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] nums;
	static int[] operators;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	
	
	public static void perm(int depth, int result) {
		
		if(depth == N) {
			max = Math.max(max, result);
			min = Math.min(min, result);
			
			return;
		}
		
		for(int i=0 ; i<4; i++) {
			if(operators[i] != 0) {
				operators[i]--;
				
				if(i==0) {
					// 덧셈
					perm(depth+1, result + nums[depth]);
				}else if(i==1) {
					// 뺄셈
					perm(depth+1, result - nums[depth]);
				}else if(i==2) {
					// 곱셈
					perm(depth+1, result * nums[depth]);
				}else {
					// 나눗셈
					perm(depth+1, result / nums[depth]);
				}
				
				operators[i]++;
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		operators = new int[4]; // +, -, *, /
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<4; i++) {
			operators[i] = Integer.parseInt(st.nextToken());
		}
		
		perm(1, nums[0]);
		System.out.println(max);
		System.out.println(min);
	}

}
