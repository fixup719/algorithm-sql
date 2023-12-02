
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] exps;
	static boolean[] visited;
	static int diff = Integer.MAX_VALUE;
	
	//조합
	static void comb(int depth, int start) {
		if(depth==(N/2)) {
			expsCompare();
			return;
		}
		
		for(int i=start; i<=N; i++) {
			visited[i] = true;
			comb(depth+1, i+1);
			visited[i] = false;
		}
	}
	
	// 능력치 합 비교
	static void expsCompare() {
		int start = 0;
		int link = 0;
		
		for(int i=1; i<=N; i++) {
			for(int j=i; j<=N; j++) {
				if(visited[i] && visited[j]) {
					start += exps[i][j];
					start += exps[j][i];
				}else if(!visited[i] && !visited[j]){
					link += exps[i][j];
					link += exps[j][i];
				}
			}
		}
		
		diff = Math.min(diff,  Math.abs(start-link));
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		exps = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; j++) {
				exps[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		
		visited = new boolean[N+1];
		comb(0,1);
		System.out.println(diff);
		
	}

}
