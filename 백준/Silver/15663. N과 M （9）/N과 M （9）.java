
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] nums;
	static int[] ans;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	
	public static void perm(int depth) {
		
		if(depth == M) {
			for(int i=0; i<M; i++) {
				sb.append(ans[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		int before=0;
		for(int i=0 ; i<N; i++) {
			if(visited[i]) continue;
			
			if(before != nums[i]) {
				visited[i] = true;
				ans[depth] = nums[i];
				before = nums[i];
				perm(depth+1);
				visited[i] = false;
			}
			
		}
	}
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		nums = new int[N];
		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(nums);
		
		ans = new int[M];
		visited = new boolean[N];
		perm(0);
		System.out.println(sb);
	}

}
