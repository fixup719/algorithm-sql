

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 	[문제 요약]
 	재현시는 N*N
 	각 칸은 구역을 의미하며, 좌표는 (r행, c열)
 	구역을 다섯 개의 선거구로 나눠야함
 	선거구는 구역을 최소 하나 포함하고, 같은 선거구는 모두 연결될 것
 		연결 O : 구역 A에서 인접 구역을 통해 구역 B로 이동 가능
				이떄 이동 경로에 포함된 구역은 모두 같은 선거구
	선거구를 나누는 법
	- 기준점 (x, y)와 경계 길이 d1, d2 정하기
		1 <= d1, d2
		1 <= x < x + d1 + d2 <= N
		1 <= y - d1 < y < y + d2 <= N
	- 경계선
		(x, y), (x + 1, y - 1) ... (x + d1, y - d1)
		(x, y), (x + 1, y + 1) ... (x + d2, y + d2)
		(x + d1, y - d1), (x + d1 + 1, y - d1 + 1) ... (x + d1 + d2, y - d1 + d2)
		(x + d2, y + d2), (x + d2 + 1, y + d2 - 1) ... (x + d2 + d1, y + d2 - d1)
		=> 해당 경계와 경계 안에 포함된 구역은 5번 선거구
	- 1번 선거구 : (1 ~ (x + d1 - 1), 1 ~ y)
	- 2번 선거구 : (1 ~ (x + d2), (y + 1) ~ N)
	- 3번 선거구 : ((x + d1) ~ N, 1  ~ (y - d1 + d2 - 1))
	- 4번 선거구 : ((x + d2 + 1) ~ N, (y - d1 + d2) ~ N)
	
	구역의 인구는 A[r][c] -> 입력으로 주어짐
	선거구를 나누는 방법 중 인구가 가장 많은 선거구와 가장 적은 선거구의 인구 차이 최솟값?
	
	[입력]
	크기 N (최대 20)
	A[r][c]정보가 N개의 줄에 주어짐 (A[r][c]는 최대 100)
	
	[출력]
	인구가 가장 많은 선거구와 가장 적은 선거구의 인구 차이의 최솟값을 출력
	
	[로직]
	일단 (x,y) d1, d2값을 우리가 정해줘야함
		=> 문제에 나온 조건을 충족하는지 체크하고 사용
	
	문제에 나온대로 경계선 설정후 5구역 표시 -> 별도의 배열 추가 int[][] ward
	그다음 나머지1~4번 선거구도 범위에 맞춰서 표시 
	
	arr을 탐색하면서 1~5번 선거구 각각의 인구수중 max, min값 갱신하고 차이값 계산 -> 최소가 될 때까지 갱신
 */

// 
public class Main {
	static int N; 
	static int[][] arr;
	static int[][] ward;
	static boolean[][] visited;
	static int[] delR = {-1, 1, 0, 0};
	static int[] delC = {0, 0, -1, 1};
	static int max = 0, min = 1 << 30, minDiff = 1 << 30;
	
	
	static void wardFive(int row, int col, int d1, int d2) {
		
		int x = row, y = col;
		while(true) {
			ward[x][y] = 5;
			x++;
			y--;
			
			if (x > row + d1 || y < col - d1) break;
		}
		
		x = row; y = col;
		while(true) {
			ward[x][y] = 5;
			x++;
			y++;
			
			if (x > row + d2 || y > col + d2) break;
		}
		
		x = row + d1; y = col - d1;
		while(true) {
			ward[x][y] = 5;
			x++;
			y++;
			
			if (x > row + d1 + d2 || y > col - d1 + d2) break;
		}
		
		x = row + d2; y = col + d2;
		while(true) {
			ward[x][y] = 5;
			x++;
			y--;
			
			if (x > row + d2 + d1 || y < col + d2 - d1) break;
		}
		
	}
	
	static void wardFour(int row, int col, int d1, int d2) {
		for (int r = row + d2 + 1; r <= N; r++) {
			for (int c = col - d1 + d2; c <= N; c++) {
				if (ward[r][c] == 5) continue;
				ward[r][c] = 4;
			}
		}
	}
	
	static void wardThree(int row, int col, int d1, int d2) {
		for (int r = row + d1; r <= N; r++) {
			for (int c = 1; c < col - d1 + d2; c++) {
				if (ward[r][c] == 5) break;
				ward[r][c] = 3;
			}
		}
	}
	
	static void wardTwo(int row, int col, int d1, int d2) {
		for (int r = 1; r <= row + d2; r++) {
			for (int c = col + 1; c <= N; c++) {
				if (ward[r][c] == 5) continue;
				ward[r][c] = 2;
			}
		}
	}
	
	static void wardOne(int row, int col, int d1, int d2) {
		for (int r = 1; r < row + d1; r++) {
			for (int c = 1; c <= col; c++) {
				if (ward[r][c] == 5) break;
				ward[r][c] = 1;
			}
		}
	}
	
	static void bfs(int row, int col) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {row, col});
		visited[row][col] = true;
		
		int sum = 0, cr, cc, mr, mc, wardNo = ward[row][col];
		while(!q.isEmpty()) {
			cr = q.peek()[0];
			cc = q.poll()[1];
			
			sum += arr[cr][cc];
			
			for (int dir = 0; dir < 4; dir++) {
				mr = cr + delR[dir];
				mc = cc + delC[dir];
				
				if (mr < 1 || mc < 1 || N < mr || N < mc || visited[mr][mc]) continue;
				if (wardNo != ward[mr][mc]) continue;
				
				visited[mr][mc] = true;
				q.offer(new int[] {mr, mc});
			}
		}
		
		max = Math.max(sum, max);
		min = Math.min(sum, min);
	}
	
	static void exterior() {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {0, 0});
		visited[0][0] = true;
		
		int cr, cc, mr, mc;
		while(!q.isEmpty()) {
			cr = q.peek()[0];
			cc = q.poll()[1];
			
			for (int dir = 0; dir < 4; dir++) {
				mr = cr + delR[dir];
				mc = cc + delC[dir];
				
				if (mr < 0 || mc < 0 || N + 1 < mr || N + 1 < mc || visited[mr][mc]) continue;
				if (ward[mr][mc] == 5) continue;
				
				visited[mr][mc] = true;
				q.offer(new int[] {mr, mc});
				ward[mr][mc] = -1;
			}
		}
		
		// -1이 아닌공간 모두 5로
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (ward[i][j] != -1) ward[i][j] = 5;
			}
		}
		
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st;
		
		N = Integer.parseInt(sc.nextLine());
		
		arr = new int[N + 2][N + 2];
		ward = new int[N + 2][N + 2];
		visited = new boolean[N + 2][N + 2];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(sc.nextLine());
			for (int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 기준점, d1, d2 정하기
		// 160000개 연산
		for (int row = 1; row <= N; row++) {
			for (int col = 1; col <= N; col++) {
				for (int d1 = 1; d1 <= N; d1++) {
					for (int d2 = 1; d2 <= N; d2++) {
						if (1 <= row && row < row + d1 + d2 && row + d1 + d2 <= N
								&& 1 <= col - d1 && col - d1 < col && col < col + d2 && col + d2 <= N) {
							wardFive(row, col, d1, d2);
							
							exterior();
							
							wardOne(row, col, d1, d2);
							wardTwo(row, col, d1, d2);
							wardThree(row, col, d1, d2);
							wardFour(row, col, d1, d2);
							
							for (boolean[] v : visited) {
								Arrays.fill(v, false);
							}
							
							for (int i = 1; i <= N; i++) {
								for (int j = 1; j <= N; j++) {
									if (visited[i][j]) continue;
									
									bfs(i, j);
								}
							}
							
							minDiff = Math.min(minDiff, max - min);
							
							for (boolean[] v : visited) {
								Arrays.fill(v, false);
							}
							for (int[] w : ward) {
								Arrays.fill(w, 0);
							}
							max = 0; min = 1 << 30;
						}
					}
				}
			}
		}
		
		System.out.println(minDiff);
	}
}
