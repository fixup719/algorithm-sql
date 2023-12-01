
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 상하좌우
	static int[] delR = {1,-1,0,0};
	static int[] delC = {0,0,-1,1};
	static int N,M;
	static int[][] map;
	static int MAX = Integer.MIN_VALUE;
	static boolean[][] visited;

	static void dfs(int row, int col, int sum, int cnt) {
		
		if(cnt==4) {
			MAX = Math.max(MAX, sum);
			return; 
		}
		
		for(int dir = 0; dir<4; dir++) {
			int mrow = row + delR[dir];
			int mcol = col + delC[dir];
			
			if(mrow<0 || mrow>=N || mcol<0 || mcol>=M) continue;
			
			if(!visited[mrow][mcol]) {
				if(cnt == 2) {
					visited[mrow][mcol] = true;
					dfs(row, col, sum+map[mrow][mcol], cnt+1);
					visited[mrow][mcol] = false;
				}
				visited[mrow][mcol] = true;
				dfs(mrow, mcol, sum+map[mrow][mcol], cnt+1);
				visited[mrow][mcol] = false;
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new boolean[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				visited[i][j] = true;
				dfs(i,j,map[i][j],1);
				visited[i][j] = false;
			}
		}
		
		System.out.println(MAX);
	}

}
