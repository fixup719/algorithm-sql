
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 [문제 요약]
 R * C 격자판이 있다.
 낚시왕은 1번 열의 왼쪽에 존재 (0,0)
 낚시왕이 가장 오른쪽 열의 오른쪽 칸에 도착하면 이동 멈춤 (0, C + 1)
  
 1초동안 아래 동작
 1. 낚시왕이 오른쪽 한 칸 이동 (r,c) -> (r,c+1)
 2. 현재 칸의 열에 있는 상어 중 제일 가까운 상어 잡기 -> 잡으면 해당 상어는 사라짐
 3. 상어는 이동 
	상어는 속도만큼 이동(칸/초)
	만약 이동 중에 경계를 벗어나면 방향을 반대로 바꿔서 이동
 4. 상어가 이동을 마친 후 칸에 상어가 두 마리 이상 존재하면, 가장 큰 상어가 나머지 모두 잡아먹음
  	
 낚시왕이 낚시를 하고 나서, 잡은 상어 크기 합을 출력해라
 
 [입력]
 R(2~100) C(2~100) 상어수 M(0~R*C)
 M개 줄에 상어정보 r c s d z
 	(r,c) 속력 s, 이동방향 d, 크기 z
 		d : 1 위, 2 아래, 3 오른쪽, 4 왼쪽
	같은 크기 상어 존재 X, 한 칸에 둘 이상 존재X
	
 [로직]
 로직은 낚시왕의 열 위치가 C+1이 되기 전까지 반복
 1. 가장 가까운 상어 잡기
 	row값 1 ~ R까지 이동하면 상어 찾으면 해당 상어 잡고 ans += 상어 크기, 제거
 2. 상어 이동
 	상어는 동시에 이동하니까..
 	4*6격자 
   	(1, 3) 속도 5 방향 2
   		(1, 3) -> (2, 3) -> (3, 3) -> (4, 3) -> (5, 3) 경계 벗어나므로 방향 바꾸고 (3, 3) -> (2,3)
	* 방향 바꾸기
		1 <-> 2 / 3 <-> 4
		%2==0이면 -1 / %2==1이면 +1
	
	* 이동 후 맵 정보 -> 나중에 낚시할 떄 사용
	int[][] map = new int[R + 1][C + 1];
		이동 하기 전 위치, 잡혔을 경우는 다 0으로 바꿀 것
	
	* 상어들 -> 이동할 때 사용
	pq에 담기 {row, col, 속도, 방향, 크기, 죽음 여부} -> 크기 순 내림차순 정렬
		이동 후 map[row][col]에 추가 -> 근데 이미 상어가 존재한거면 뒤늦게 온 상어는 죽음
	 	
 */	

// 
public class Main {
	static int R, C, M;
	static int[][] map;
	static PriorityQueue<Shark> pq = new PriorityQueue<>();
	static Queue<Shark> tempQ = new LinkedList<>();
	static int[] delR = {0, -1, 1, 0, 0};
	static int[] delC = {0, 0, 0, 1, -1};
	
	static class Shark implements Comparable<Shark>{
		int row;
		int col;
		int vel;
		int dir;
		int size;
		int isDead; // 0 : 살아있음, 1 : 죽음
		
		Shark(int row, int col, int vel, int dir, int size, int isDead){
			this.row = row;
			this.col = col;
			this.vel = vel;
			this.dir = dir;
			this.size = size;
			this.isDead = isDead;
		}
		
		// 크기 기준 내림 차순 정렬
		@Override
		public int compareTo(Shark o) {
			return o.size - this.size;
		}
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st;
		
		st =  new StringTokenizer(sc.nextLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[R + 1][C + 1];
		
		int row, col, vel, dir, size;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(sc.nextLine());
			row = Integer.parseInt(st.nextToken());
			col = Integer.parseInt(st.nextToken());
			vel = Integer.parseInt(st.nextToken());
			dir = Integer.parseInt(st.nextToken());
			size = Integer.parseInt(st.nextToken());
			
			pq.offer(new Shark(row, col, vel, dir, size, 0));
			map[row][col] = size;
		}
		
		
		int manCol = 1, tr, mrow, mcol, total = 0, deadR, deadC, tempV;
		Shark shark;
		while(manCol <= C) {
			// 1. 현재 열에서 가장 가까운 낚시 잡기
			tr = 1;
			deadR = 0;
			deadC = 0;
			while(tr <= R) {
				
				// 가장 가까운 상어 발견 -> 잡고 끝내기
				if (map[tr][manCol] != 0) {
					total += map[tr][manCol];
					// 해당 상어는 map에서 지우고
					map[tr][manCol] = 0;
					// 죽은 상태로 남겨두기 위해 죽은 상어 위치를 임시로 저장
					deadR = tr;
					deadC = manCol;
					break;
				}
				
				tr++;
			}
			
			// 2. 상어 이동하기 
			for (int i = 0, ps = pq.size(); i < ps; i++) {
				shark = pq.poll();
				
				row = shark.row;
				col = shark.col;
				vel = shark.vel;
				dir = shark.dir;
				size = shark.size;
				
				// 죽은 상어는 pass
				if (shark.isDead == 1) continue;
				// 이미 앞에서 잡힌 상어 였다면
				if (row == deadR && col == deadC) continue;
				
				// 이전 위치는 지우기 -> 이때 앞에서 큰 상어가 먼저 차지했을 수도 있으니까, 크기 비교후 갱신해야함 
				if (map[row][col] == size) map[row][col] = 0;
				
				/*
				 상어 이동하기 -> 여기서 최적화 해야함 (속도가 1만개까지 가능하니까)
				 격자판 4 * 6
				 아래(2), 속도 13, 
				 현재 위치 (2,3) => 최종 목적지 (3, 3)
					 (2,3) -> (3,3) -> (4,3) 
						 일단 아래로 2칸
					 -> 경계벗어남, 위로, (3,3) -> (2,3) -> (1,3) 
						 앞으로 11칸 더 움직여야함 근데 방향 바꾸는거 생각하면 3칸씩 이동하다 바뀜
						 11 / 3 = 3번 더 방향 바꿈, 11 % 3 = 마지막으로 방향바꾸고 2칸 더 이동 
					 -> 경계 벗어남, 아래로, (2,3) -> (3,3) -> (4,3) 
					 -> 경계 벗어남, 위로, (3,3) -> (2,3) -> (1,3) 
				 	 -> 경계 벗어남, 아래로, (2,3) -> (3,3) 
					 최종적으로 4번 방향 바꾸니까 처음 방향 그대로(짝수)
					 결국 현재 위치에서 아래 방향으로 2번 더 이동하고 끝
				 현재 위치 (2,3), 속도 3, 위 => 최종 목적지(3,3)
					 (2,3) -> (1,3)
					 -> 방향 바꿈 아래로 (2,3) -> (3,3)
					 일단, 위로 1칸 이동하고 방향 바꿈
					 나머지는 2 / 3 = 0번 더 방향 바꿈, 2 % 3 = 마지막으로 방향 바꾸고 2칸 더 이동

				 체크할 것 : 현재 위치 (r,c)
					1. 방향이 위 또는 아래, 왼쪽 또는 오른쪽
						 - 위, 아래 => R크기
						 	- 위 : 위로 r칸 이동 후 방향 바꿈
						 	- 아래 : 아래로 R-r칸 이동 후 방향 바꿈
						 - 왼쪽, 오른쪽 => C크기 
						 	- 왼쪽 : 왼쪽으로 c칸 이동 후 방향 바꿈
						 	- 오른쪽 : 오른쪽으로 C-c칸 이동후 방향 바꿈 
				 	2. 최종적으로 방향 바꾸기 전 위치
				 	 	민약 최종 방향이 
				 	 		위 (R, c)
				 	 		아래 (1, c)
				 	 		왼쪽 (r, C)
				 	 		오른쪽 (r, 1)
				 
				 */
				
				int change, remain;
				if (dir == 1) {
					// 만약 위인 경우
					if (row - 1 >= vel) {
						// 위로 이동할 수 있는 남은 칸보다 속도가 더 작은 경우, 그냥 이동
						mrow = row + delR[dir] * vel;
						mcol = col + delC[dir] * vel;
					} else {
						// 그게 아니라면 몇번 경계를 바뀌어야 할 수 있음
						// remain 변수에 담기
						remain = vel - (row - 1);
						change = remain / (R - 1);
						remain %= (R - 1);
						
						if (change % 2 == 0) {
							// 최종 위치 반대 방향
							dir++;
							row = 1;
						} else {
							row = R;
						}
						
						mrow = row + delR[dir] * remain;
						mcol = col + delC[dir] * remain;
					}
					
				} else if (dir == 2) {
					// 아래인 경우
					if (R - row >= vel) {
						mrow = row + delR[dir] * vel;
						mcol = col + delC[dir] * vel;
					} else {
						remain = vel - (R - row);
						change = remain / (R - 1);
						remain %= (R - 1);
						
						if (change % 2 == 0) {
							// 최종 위치 반대 방향
							dir--;
							row = R;
						} else {
							row = 1;
						}
						
						mrow = row + delR[dir] * remain;
						mcol = col + delC[dir] * remain;
					}
				} else if (dir == 3) {
					// 오른쪽인 경우
					if (C - col >= vel) {
						mrow = row + delR[dir] * vel;
						mcol = col + delC[dir] * vel;
					} else {
						remain = vel - (C - col);
						change = remain / (C - 1);
						remain %= (C - 1);
												
						if (change % 2 == 0) {
							// 최종 위치 반대 방향
							dir++;
							col = C;
						} else {
							col = 1;
						}
						
						mrow = row + delR[dir] * remain;
						mcol = col + delC[dir] * remain;
					}
				} else {
					// 왼쪽인 경우
					if (col - 1 >= vel) {
						mrow = row + delR[dir] * vel;
						mcol = col + delC[dir] * vel;
					} else {
						remain = vel - (col - 1);
						change = remain / (C - 1);
						remain %= (C - 1);
						
						if (change % 2 == 0) {
							// 최종 위치 반대 방향
							dir--;
							col = 1;
						} else {
							col = C;
						}
						
						mrow = row + delR[dir] * remain;
						mcol = col + delC[dir] * remain;
					}
				}
				
								
				if (map[mrow][mcol] <= size) {
					// 아직 아무 상어도 없다면
					map[mrow][mcol] = size;
					tempQ.offer(new Shark(mrow, mcol, vel, dir, size, 0));
				}
			}
			
			// 상어 이동 후 tempQ에 있는 상어들 pq에 옮겨 주기
			while(!tempQ.isEmpty()) {
				pq.offer(tempQ.poll());
			}
			
			// 낚시꾼 이동
			manCol++;
		}
		
		System.out.println(total);
	}
}
