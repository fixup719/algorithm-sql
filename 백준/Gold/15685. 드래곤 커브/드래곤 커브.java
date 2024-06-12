
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	/*
	 시계 방향 90도 회전 할 경우
	 이전 점이 동쪽에 있을 경우 -> 남쪽으로 0 -> 3
	 서쪽에 있을 경우 -> 북쪽으로 2 -> 1
	 북쪽에 있을 경우 -> 동쪽으로 1 -> 0
	 남쪽에 있을 경우 -> 서쪽으로 3 -> 2
	 * */

	
	// 드래곤 커브 개수
	static int N;
	// 좌표 범위
	static final int SIZE = 101;
	// 방향 : 동 북 서 남
	static int[] delY = {0, -1, 0, 1};
	static int[] delX = {1, 0, -1, 0};
	// 드래곤 커브 별 & 세대별 표시 맵
	static int[][] dragon = new int[SIZE][SIZE];
	// 연결된 이전 노드 표시 
	static ArrayList<Integer>[] preNode = new ArrayList[SIZE * SIZE];
	// dfs 탐색할 떄 재방문 방지 visited[i][j] : 현재 i노드일 때 이전 노드가 j인 경우 이미 방문 했는지?
	static boolean[][] visited = new boolean[SIZE * SIZE][SIZE * SIZE];
	// 최종 드래곤 표시 맵
	static int[][] answerMap = new int[SIZE][SIZE];
	// 가장 끝 노드 
	static int ex, ey;
	
	// answerMap에 드래곤 기록
	static void toAnswerMap() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (dragon[i][j] == 1) answerMap[i][j] = dragon[i][j];
			}
		}
	}
	
	// 드래곤 커브 만들기
	static void makeDragon(int curX, int curY, int endX, int endY) {
		ex = endX;
		ey = endY;
				
		int curNum = SIZE * curY + curX;
		int endNum = SIZE * endY + endX;
		
		int nxtNum, nxtX, nxtY, dir;
		for (int i = preNode[curNum].size() - 1; 0 <= i; i--) {
			nxtNum = preNode[curNum].get(i);
					
			if (visited[curNum][nxtNum]) continue;
						
			visited[curNum][nxtNum] = true;
					
			// 노드로 변환		
			nxtX = nxtNum % SIZE;
			nxtY = nxtNum / SIZE;
			
			// 이전 노드가 현재 노드의 어느 방향에 있는지 파악
			for (int d = 0; d < 4; d++) {
				if (curX + delX[d] == nxtX
						&& curY + delY[d] == nxtY) {
					
					// d방향에 있으므로, 90도회전했을 때 방향 찾기
					dir = (d + 3) % 4;
										
					// 지금까지 기록된 가장 끝 노드에 이어주기 
					endX += delX[dir];
					endY += delY[dir];
					
					// 드래곤 기록
					dragon[endY][endX] = 1;
					
					// 이전 노드 추가 
					preNode[endY * SIZE + endX].add(endNum);
					
					// 재귀 호출
					makeDragon(nxtX, nxtY, endX, endY);
					
					// 이전 노드 하나만 찾으면 되니까 재귀 돌아오면 현재 노드 탐색은 종료
					return;
				}
			}
			
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st;
		
		N = Integer.parseInt(sc.nextLine());
		
		int sx, sy, dir, g, mx, my;
		while(N-- > 0) {
			st = new StringTokenizer(sc.nextLine());
			
			// 드래곤 커브  정보
			// 드래곤 커브 시작점 (sx, sy) -> sy : 행 / sx : 열
			sx = Integer.parseInt(st.nextToken());
			sy = Integer.parseInt(st.nextToken());
			// 시작 방향 (0 : 동 / 1 : 북 / 2 : 서 / 3 : 남)
			dir = Integer.parseInt(st.nextToken());
			// 세대 
			g = Integer.parseInt(st.nextToken());
			
			for (int i = 0; i < SIZE * SIZE; i++) {
				preNode[i] = new ArrayList<>();
			}
			
			// 0세대 만들기
			dragon[sy][sx] = 1;
			mx = sx + delX[dir];
			my = sy + delY[dir];
			ex = mx;
			ey = my;
			dragon[my][mx] = 1;
			preNode[my * SIZE + mx].add(sy * SIZE + sx);
			
			// 드래곤 커브 만들기
			for (int i = 1; i <= g; i++) {
				makeDragon(ex, ey, ex, ey);
				// 방문체크 초기화
				for (boolean[] arr : visited) {
					Arrays.fill(arr, false);
				}
			}
			
			// 드래곤 커브 기록
			toAnswerMap();
			
			// 드래곤 배열 초기화
			for (int[] arr : dragon) {
				Arrays.fill(arr, 0);
			}
			
		}
		
		// 1*1 정사각형 개수 찾기 (0~100 좌표만 탐색)
		int cnt = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (answerMap[i][j] == 1 && answerMap[i + 1][j + 1] == 1
						&& answerMap[i + 1][j] == 1 && answerMap[i][j + 1] == 1) {
					cnt++;
				}
			}
		}
		
		System.out.println(cnt);
	}
}
