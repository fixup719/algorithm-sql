
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 	[문제 요약]
 	N * N 크기 땅을 구매
 	땅을 1*1 크기 카칸으로 나누었으며, 각 칸은 (r,c)
 	땅의 양분을 조사하는 로봇은 모든 칸의 양분을 조사
 	이때, 가장 처음에는 모든 칸에 양분이 5씩 들어있음
 	
 	M개의 나무를 구매해 땅에 심었다.
 	이때 한 칸에 여러 나무가 심어질 수 있음
 	
 	나무들은 사계절을 보내면 아래 과정 반복
 	1. 봄 : 자신의 나이만큼 양분을 먹고, 나이 1 증가 => 속해 있는 칸의 양분만 먹음
 			만약 여러 나무가 있다면, 어린 나이부터 먹음
			만약 양분이 부족하면, 못 먹는 나무는 죽음
	2. 여름 : 봄에 죽은 나무가 양분으로 변함
			(죽은 나무의 나이 / 2) 한 값이 양분으로 추가 됨 (몫만 취급)
	3. 가을 : 나무가 번식한다. 
			번식하는 나무는 나이가 5의 배수인 경우만 번식
			이때, 인접한 8개 칸에 나이가 1인 나무가 생김
			경계 안에서만 번식
	4. 겨울 : 땅에 양분을 추가 A[r][c] => 입력으로 주어짐 
	
	이때 K년이 지난 후 살아있는 나무 개수 구하기 
	
	[입력]
	N, M, K
	N개 줄에 A배열 값
	M개 줄에 심은 나무의 정보인 x, y, z가 주어짐
		(x,y), z나이를 의미
		
	[출력]
	K년이 지난 후 살아있는 나무 개수
	
	[로직]
	int[][] add -> 추가할 양분의 양 저장(입력값)
	int[][] map -> 현재 양분 상태 저장(처음에는 5로 초기화)
	ArrayList<Integer>[] trees -> 현재 칸 번호(i*N + j)에 속해 있는 나무들 나이 저장
	ArrayList<Integer>[] diedTrees -> 죽은 나무들 나이 저장(양분 추가후 제거)
	
	1년에 아래 4가지 과정 진행 ( 최대 1000년까지 진행)
	for i = 1 ~ k
	spring
		trees[칸 번호] 오름차순 정렬
		양분 먹기
			if (map[i][j] >= trees[칸 번호].get(k))
				map[i][j] -= trees[칸 번호].get(k);
				나이 먹기
				trees[칸 번호].get(k)++;
		만약 양분이 부족하면
			if (map[i][j] < trees[칸 번호].get(k))
				죽는다... diedTrees[칸번호].add(죽은 나무 나이);
				trees[칸 번호].remove(k);
	summer
		map[칸 번호 / N][칸 번호 % N] += diedTrees[칸 번호].get(k) / 2
		diedTrees[칸 번호].clear();	
	fall
		칸 번호 1부터 돌면서,,, 해당 칸에 속한 나무들의 나이들 중 5의 배수인 애들만 찾기
		if (trees[칸 번호].get(k) % 5 == 0)
			for dir = 0 ~ 7 -> 8방에 나무 심기
				trees[인접 칸 번호].add(1)		
	winter
		양분 추가 
		map[i][j] += add[i][j] 
 */

// 46분
public class Main {
	static int N, M, K;
	static int[][] add;
	static int[][] map; 
	static ArrayList<Integer>[] trees;
	static ArrayList<Integer>[] diedTrees;
	static int[] delR = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] delC = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	static void spring() {
		
		int cr, cc, age, size;
		for (int num = 0; num < N * N; num++) {
			// 현재 칸에 속한 나무들 나이 오름차순 정렬
			Collections.sort(trees[num]);
			
			cr = num / N;
			cc = num % N;
			size = trees[num].size();
			for (int i = 0; i < size; i++) {
				age = trees[num].get(i);
				if (map[cr][cc] >= age) {
					// 양분이 충분히 있다면
					// 양분 섭취
					map[cr][cc] -= age;
					// 나이 먹기
					trees[num].set(i, age + 1);
				} else {
					// 양분이 부족하다면 
					// 죽은 나무에 추가
					diedTrees[num].add(age);
					trees[num].remove(i);
					i--;
					size--;
				}
			}
		}
	}
	
	static void summer() {
		int cr, cc, size;
		for (int num = 0; num < N * N; num++) {
			cr = num / N;
			cc = num % N;
			size = diedTrees[num].size();
			for (int i = 0; i < size; i++) {
				// 죽은 나무 나이가 양분으로,,, 
				map[cr][cc] += diedTrees[num].get(i) / 2;
			}
			// 죽은 나무 모두 제거 
			diedTrees[num].clear();
		}
	}
	
	static void fall() {
		int cr, cc, size, age, mr, mc;
		for (int num = 0; num < N * N; num++) {
			cr = num / N;
			cc = num % N;
			size = trees[num].size();
			for (int i = 0; i < size; i++) {
				age = trees[num].get(i);
				
				if (age % 5 == 0) {
					for (int dir = 0; dir < 8; dir++) {
						mr = cr + delR[dir];
						mc = cc + delC[dir];
						
						if (mr < 0 || mc < 0 || N <= mr || N <= mc) continue;
						
						trees[mr * N + mc].add(1);
					}
				}
			}
		}
	}
	
	static void winter() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] += add[i][j];
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st;
		
		st = new StringTokenizer(sc.nextLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 배열 선언
		add = new int[N][N];
		map = new int[N][N];
		trees = new ArrayList[N * N];
		for (int i = 0; i < N * N; i++) {
			trees[i] = new ArrayList<>();
		}
		diedTrees = new ArrayList[N * N];
		for (int i = 0; i < N * N; i++) {
			diedTrees[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(sc.nextLine());
			for (int j = 0; j < N; j++) {
				add[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = 5;
			}
		}
		
		int r, c, age, num;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(sc.nextLine());
			r = Integer.parseInt(st.nextToken()) - 1;
			c = Integer.parseInt(st.nextToken()) - 1;
			age = Integer.parseInt(st.nextToken());
			
			num = r * N + c;
			trees[num].add(age);
		}

		for (int i = 1; i <= K; i++) {
			spring();
			summer();
			fall();
			winter();
		}
		
		// 살아 남은 나무수 
		int ans = 0;
		for (int i = 0; i < N * N; i++) {
			ans += trees[i].size();
		}
		
		System.out.println(ans);
	}
}
