import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N,M; // 사무실 크기 N*M
	static int[][] room; // 사무실
	static ArrayList<int[]> cctvs = new ArrayList<>(); // CCTV 정보 
	static int[] cases; // 경우의 수 담을 것 
	static int safeZone; // 안전 구역(0개수)
	static int[] delR = {-1,1,0,0}; // 행 방향, 상하좌우
	static int[] delC = {0,0,-1,1}; // 열 방향, 상하좌우
	static boolean[][] visited;// 방문체크
	static int ans = 64; // 안전구역 
	
	/*
	 CCTV 1번 : 상/하/좌/우
	 CCTV 2번 : 상하/좌우
	 CCTV 3번 : 상우/우하/하좌/좌상
	 CCTV 4번 : 좌상우/상우하/우하좌/하좌상
	 CCTV 5번 : 상하좌우
	*/
	
	static void duPerm(int depth) {
		if(depth == cctvs.size()) {
			// 경우의 수 하나 완성
			visited = new boolean[N][M];
			int sumWatch=0;
			for(int i=0; i<cases.length; i++) {
				int cctvNo = cctvs.get(i)[0];
				int row = cctvs.get(i)[1];
				int col = cctvs.get(i)[2];
				
				sumWatch += branchOut(cctvNo, row, col, cases[i]);
			}
			ans = Math.min(ans, safeZone-sumWatch);
			
			return;
		}
		
		for(int dir=0; dir<4; dir++) {
			cases[depth] = dir;
			duPerm(depth+1);
		}
		
	}
	
	
	static int branchOut(int cctvNo, int row, int col, int dir) {
		
		int sumWatch = 0;
		
		if(cctvNo == 1) {
			
			sumWatch = watch(row, col, dir);
			
		}else if(cctvNo == 2) {
			
			if(dir == 0 || dir == 1) {
				// 상하
				sumWatch = watch(row, col, 0) + watch(row, col, 1);
				
			}else if(dir == 2 || dir == 3) {
				// 좌우
				sumWatch = watch(row, col, 2) + watch(row, col, 3);
				
			}
			
		}else if(cctvNo == 3) {
			
			if(dir==0) {
				
				// 상좌
				sumWatch = watch(row, col, 0) + watch(row, col, 2);
				
			}else if(dir==1) {
				
				// 하좌
				sumWatch = watch(row, col, 1) + watch(row, col, 2);
				
			}else if(dir==2) {
				
				// 상우
				sumWatch = watch(row, col, 0) + watch(row, col, 3);
				
			}else {
				
				// 하우
				sumWatch = watch(row, col, 1) + watch(row, col, 3);
				
			}
			
		}else if(cctvNo == 4) {
			
			if(dir==0) {
				
				// 상좌우
				sumWatch = watch(row, col, 0) + watch(row, col, 2) + watch(row, col, 3);
				
			}else if(dir==1) {
				
				// 하좌우
				sumWatch = watch(row, col, 1) + watch(row, col, 2) + watch(row, col, 3);
				
			}else if(dir==2) {
				
				// 좌상하
				sumWatch = watch(row, col, 2) + watch(row, col, 0) + watch(row, col, 1);
				
			}else {
				
				// 우상하
				sumWatch = watch(row, col, 3) + watch(row, col, 0) + watch(row, col, 1);
				
			}
			
		}else {
			sumWatch = watch(row, col, 0) + watch(row, col, 1) + watch(row, col, 2) + watch(row, col, 3);
		}
		
		return sumWatch;
		
	}
	
	
	static int watch(int row, int col, int dir) {
		
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {row, col});
		int sumWatch = 0;
		
		while(!q.isEmpty()) {
			int mrow  = q.peek()[0] + delR[dir];
			int mcol  = q.poll()[1] + delC[dir];
			
			
			if(mrow < 0 || mcol < 0 || N <= mrow || M <= mcol || room[mrow][mcol] == 6) continue;
			
			if(!visited[mrow][mcol] && room[mrow][mcol]==0) {
				visited[mrow][mcol] = true;
				sumWatch++;
				q.offer(new int[] {mrow, mcol});
			}else q.offer(new int[] {mrow, mcol});	
		}
		
		return sumWatch;
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 방크기
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 방 정보 입력 받기
		room = new int[N][M];
		safeZone = 0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				int input = Integer.parseInt(st.nextToken());
				room[i][j] = input;
				
				if(input==0) safeZone++;
				else if(input <= 5) cctvs.add(new int[] {input, i, j});
			}
		}
		
		cases = new int[cctvs.size()];
		duPerm(0);
		
		System.out.println(ans);
		
	}

}