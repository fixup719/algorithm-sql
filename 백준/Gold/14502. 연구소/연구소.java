
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static int[][] copyMap;
	static final int WALL = 3;
	static Queue<int[]> q = new LinkedList<>();
	static int[] delR = {-1,1,0,0}; // 상하좌우
	static int[] delC = {0,0,-1,1}; // 상항좌우
	static int[][] zeroSpot;
	static int safeCnt = 0;
	static int tmp; 
	static int ans = 0; 
	
	static void buildWall(int depth, int start) {
		
		if(depth == WALL) {
			// 벽 3개를 다 세울 경우
			tmp = safeCnt;
			onCopyMap();
			// 바이러스 퍼뜨리기 
			onSpreadVirus();
			
			ans = Math.max(ans, tmp);
			
			return;
		}
		
		
		for(int i=start; i<safeCnt; i++) {
			int row = zeroSpot[i][0];
			int col = zeroSpot[i][1];
			
			map[row][col] = 1;
			buildWall(depth+1, i+1);
			map[row][col] = 0;
			
		}
	}
	
	static void onCopyMap() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==2) q.offer(new int[] {i, j}); // 바이러스2 있는 칸일 겨우 큐에 담기
				copyMap[i][j] = map[i][j];
			}
		}
	}
	
	static void onSpreadVirus() {
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			for(int i=0; i<size; i++) {
				int row = q.peek()[0];
				int col = q.poll()[1];
				
				for(int dir=0; dir<4; dir++) {
					int mrow = row + delR[dir];
					int mcol = col + delC[dir];
					
					if(mrow<0 || mcol<0 || N<=mrow || M<=mcol) continue;
					
					if(copyMap[mrow][mcol]==0) {
						copyMap[mrow][mcol] = 2;
						q.offer(new int[] {mrow, mcol});
						tmp--;
					}
				}
				
			}
			
			
		}
		
		
		
	}
	

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 연구소 크기 N*M
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		copyMap = new int[N][M];
		zeroSpot = new int[N*M][2];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				int num = Integer.parseInt(st.nextToken());
				
				if(num==0) {
					zeroSpot[safeCnt][0] = i;
					zeroSpot[safeCnt++][1] = j;
				}
				
				map[i][j] = num; // 맵 그리기
			}
		}
	
		
		
		buildWall(0, 0);
		System.out.println(ans-3);
		
	}

}
