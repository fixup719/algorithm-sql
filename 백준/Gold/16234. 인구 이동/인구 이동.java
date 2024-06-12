
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 
 [로직] 
 하루동안 일어나는 일 
 1. 인접한 나라끼리 인구 수 비교
 	=> 위, 오른쪽에 위치한나라끼리 비교할 것 -> 상하좌우하면 중복으로 비교하는 경우 발생함 
 	=> L <= Math.abs(A[cr][cc]-A[mr][mc]) <= R -> 국경선 개방 
    => 이때, 개방하고 바로 인구이동 X -> 모든 나라 국경선 개방 여부 체크 후 이동할 것
    => 그럼 개방 여부 어떻게 체크하지 ? -> 2차원 좌표를 정수로 바꾸고(N*row + col), 같은 연결요소로 묶어버리기
 2. 이제 다시 지도를 돌면서 해당 칸의 부모 노드가 같은 애들끼리 인구 이동 시작 (dfs)
 */

public class Main {
	static int N, L, R;
	static int[][] map;
	static int[] parent;
	static int[][] population;
	static int[] delR = {-1, 0, 1, 0};
	static int[] delC = {0, 1, 0 , -1};
	
	static void initArr() {
		for (int i = 0; i < N * N; i++) {
			parent[i] = i;
		}
		
		for (int i = 0; i < N * N; i++) {
			population[i][0] = 1;
			population[i][1] = map[i / N][i % N];
		}
	}
	
	static int find (int x) {
		if (parent[x] == x) return parent[x];
		
		parent[x] = find(parent[x]);
		return parent[x];
	}
	
	static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a == b) return;
		
		parent[b] = a;
		population[a][0] += population[b][0];
		population[a][1] += population[b][1];
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st;
		
		st  = new StringTokenizer(sc.nextLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(sc.nextLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		parent = new int[N * N];
		population = new int[N * N][2];
		
		// 국경선 개방
		int mr, mc, diff, cNum, mNum, open = 0, days = 0;
		// 개방된 국경선이 있을 때만 돈다
		while (true) {
			// 부모 배열 / 인구 이동후 인구 배열 초기화 (매일 연합국은 달라지기 때문) 
			initArr();
			
			// 국경선 개방하기
			for (int cr = 0; cr < N; cr++) {
				for (int cc = 0; cc < N; cc++) {
					// 위, 오른쪽 인접한 나라랑 비교
					for (int dir = 0; dir < 2; dir++) {
						mr = cr + delR[dir];
						mc = cc + delC[dir];
						
						if (mr < 0 || mc < 0 || N <= mr || N <= mc) continue;
						
						// 인구 차이 L이상 R이하인지 체크
						diff = Math.abs(map[cr][cc] - map[mr][mc]);
						if (L <= diff && diff <= R) {
							// 국경선 개방 & 연합하기
							// 2차원을 정수화 
							cNum = cr * N + cc;
							mNum = mr * N + mc;
							// 연합하기 
							union(cNum, mNum);
							// 개방된 국경선 개수 
							open++;
						}
					}
				}
			}
			
			// 개방된 국경선이 없으면 종료
			if (open == 0) break;
			
			// 인구 이동 후 배분 하기
			int num;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					num = i * N + j;
					map[i][j] = population[find(num)][1] / population[find(num)][0];
				}
			}
			
			days++;
			open = 0;
		}
		
		System.out.println(days);
		
	}
}
