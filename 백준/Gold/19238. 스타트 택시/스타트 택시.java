
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 	N*N 크기 격자
 	M명의 승객을 태워야 함 
 	각 칸은 비어있거나 벽이 있음
 	택시는 상하좌우로 인접한 빈칸으로 이동 가능
 	이때, 항상 최단 경로로 이동
 	승객은 한 명씩만 태울 수 있음
 	한 승객을 태워 목적지까지 데려다주는 일을 총 M번 해야 함
 	
 	승객 선택 방법 : 현재 위치에서 가장 가까운 승객 선택
 				  만약 여러 명이면 (1) 행 번호가 가장 작은 승객 (2) 열 번호가 가장 작은 승객 
 				  
  	연료는 한 칸에 하나씩 소모
  	한 승객을 목적지로 성공적으로 이동시키면, 해당 승객을 태워이동하며 소모 한 연료*2만크 충전
  	
  	이동하는 도중에 연료가 바닥나면 끝 -> 실패 
  		단 목적지 이동시킨 동시에 바닥난경우는 실패 아님 
  		
	[입력]
	N(크기, 2~20) M(승객 수, 1~N*N) F(초기 연료양 1~50만)
	지도 정보 (1 -> 벽)
	시작 위치 r c
	M개 줄에 승객 출발지 & 도착지 
	행과 열번호는 1~N
	
	[출력]
	모든 손님을 이동시키고 난 후 연료를 충전했을 때 남은 연료 양 출력
		만약 실패하먄거나 모든 손님을 이동시킬 수 없다면 -1 출력
		
	[로직]
	최단 거리로 이동 -> bfs
	승객 태우기
		pq -> 행우선, 열우선 
		만약 승객을 발견하면, 목적지 태워주는 함수 호출
	승객 목적지로 태워주기
  		목적지에 달성하면 종료
	중간에 체크할 것 => 연료
		한 칸씩 이동할 때마다 1 감소
		이때, 승객을 태우면, 태우면서 소모한 연료양도 계산 
		만약 연료양이 음수가 되면 종료 
		
	**** 주의  : 승객 목적지 같을 수 있음;;;
 */	

// 
public class Main {
	static int N, M, fuel, remain;
	static ArrayList<Integer>[][] startMap;
	static ArrayList<Integer>[][] destMap;
	static int startR, startC;
	static Guest[] guests;
	static PriorityQueue<int[]> pq = new PriorityQueue<>(
			new Comparator<int[]>() {
				public int compare(int[] o1, int[] o2) {
					if (o1[0] == o2[0]) return o1[1] - o2[1];
					else return o1[0] - o2[0];
				}
			}
	);
	static Queue<int[]> q = new LinkedList<int[]>();
	static boolean[][] visited = new boolean[20][20];
	static int[] delR = {-1, 1, 0, 0};
	static int[] delC = {0, 0, -1, 1};
	
	static class Guest{
		int gsr;
		int gsc;
		int ger;
		int gec;
		
		Guest(int gsr, int gsc, int ger, int gec){
			this.gsr = gsr;
			this.gsc = gsc;
			this.ger = ger;
			this.gec = gec;
		}
	}
	
	// 승객 태우러 가기 
	static void toGuest(int sr, int sc) {
		q.clear();
		pq.clear();
		for(boolean[] v : visited) {
			Arrays.fill(v, false);
		}
		
		q.offer(new int[] {sr, sc});
		visited[sr][sc] = true;
		
		int cr, cc, mr, mc, size, gno;
		while(!q.isEmpty()) {
			size = q.size();
			
			for (int s = 0; s < size; s++) {
				cr = q.peek()[0];
				cc = q.poll()[1];
								
				if (0 < startMap[cr][cc].get(startMap[cr][cc].size() - 1) 
						&& startMap[cr][cc].get(startMap[cr][cc].size() - 1) <= M) {
					// 승객이 있는 위치 -> 여러명 있을 수 있으니 일단 pq에 담기
					pq.offer(new int[] {cr, cc});
				}
				
				for (int dir = 0; dir < 4; dir++) {
					mr = cr + delR[dir];
					mc = cc + delC[dir];
					
					if (mr < 0 || mc < 0 || N <= mr || N <= mc 
							|| visited[mr][mc] || startMap[mr][mc].get(0) == -1) continue;
				
					visited[mr][mc] = true;
					q.offer(new int[] {mr, mc});
				}
			}
			
			// 태울 수 있는 승객이 있다면
			if (!pq.isEmpty()) {
				cr = pq.peek()[0];
				cc = pq.poll()[1];
				
				// 승객 번호 저장
				gno = startMap[cr][cc].get(startMap[cr][cc].size() - 1);
				
				// 승객 태움 
				startMap[cr][cc].remove(Integer.valueOf(gno));
				
				// 승객 도착지로 모시기 
				toDest(cr, cc, gno);
				return; 
			}
			
			fuel--;
			// 태우러 가는 데 연료 바닥남 
			if (fuel == 0) return;
		}
	}
	
	static void toDest(int sr, int sc, int gno) {
		
		q.clear();
		for(boolean[] v : visited) {
			Arrays.fill(v, false);
		}
		
		q.offer(new int[] {sr, sc});
		visited[sr][sc] = true;
		
		int cr, cc, mr, mc, size, use = 0;
		while(!q.isEmpty()) {
			size = q.size();
			
			for (int s = 0; s < size; s++) {
				cr = q.peek()[0];
				cc = q.poll()[1];
								
				if (destMap[cr][cc].contains(gno + M)) {
					// 태운 승객의 도착지라면
					
					// 해당 도착 정보 삭제
					destMap[cr][cc].remove(Integer.valueOf(gno + M));
					
					// 승객 내려주기 
					remain--;
					
					// 연료 충전
					fuel += use*2;
					
					// 택시 위치 갱신
					startR = cr;
					startC = cc;
					return;
				}
				
				for (int dir = 0; dir < 4; dir++) {
					mr = cr + delR[dir];
					mc = cc + delC[dir];
					
					if (mr < 0 || mc < 0 || N <= mr || N <= mc 
							|| visited[mr][mc] || destMap[mr][mc].get(0) == -1) continue;
				
					visited[mr][mc] = true;
					q.offer(new int[] {mr, mc});
				}
			}
			
			// 소모한 연료양
			use++;
			
			// 현재 연료 상태
			fuel--;
			if (fuel < 0) return;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st;
		
		st = new StringTokenizer(sc.nextLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		remain = M;
		fuel = Integer.parseInt(st.nextToken());
		
		startMap = new ArrayList[N][N];
		destMap = new ArrayList[N][N];
		int input;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(sc.nextLine());
			for (int j = 0; j < N; j++) {
				input = Integer.parseInt(st.nextToken());
				// 벽은 -1로 바꾸기
				if (input == 1) input = -1;
				
				startMap[i][j] = new ArrayList<Integer>();
				destMap[i][j] = new ArrayList<Integer>();
				startMap[i][j].add(input);
				destMap[i][j].add(input);
				
			}
		}
		
		st = new StringTokenizer(sc.nextLine());
		startR = Integer.parseInt(st.nextToken()) - 1;
		startC = Integer.parseInt(st.nextToken()) - 1;
		
		guests = new Guest[M + 1];
		
		int gsr, gsc, ger, gec;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(sc.nextLine());
			gsr = Integer.parseInt(st.nextToken()) - 1;
			gsc = Integer.parseInt(st.nextToken()) - 1;
			ger = Integer.parseInt(st.nextToken()) - 1;
			gec = Integer.parseInt(st.nextToken()) - 1;
			
			// 출발지 ex) 1번 승객 -> 출발지 1, 도착지 (1 + M)
			startMap[gsr][gsc].add(i);
			destMap[ger][gec].add(i + M);			
			
			guests[i] = new Guest(gsr, gsc, ger, gec);
		}
		
		for (int i = 0; i < M; i++){
			toGuest(startR, startC);
		}
		
		if (remain > 0) System.out.println(-1);
		else System.out.println(fuel);
	}
}
