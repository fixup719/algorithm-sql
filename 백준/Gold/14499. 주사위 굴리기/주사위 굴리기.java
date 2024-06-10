
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int x, y;
	static int[][] map;
	static int[] dice = {0, 4, 3, 5, 2};
	static int[] diceNum = new int[7];
	static int[] delR = {0, 0, 0, -1, 1};
	static int[] delC = {0, 1, -1, 0, 0};
	
	/*
	 주사위의 윗면이 뭐냐에 따라 
	 동, 서, 북, 남으로 굴렸을 떄 
	 윗면이 달라진다.
	 
	 그냥 모든 케이스를 다 고려해서 미리 배열로 만들어놓으면 안되나?? 
	 ex) 
	 arr[1][1] : 윗면이 1일 때 동쪽으로 굴렀을 때 윗면
	 arr[1][2] : 윗면이 1일 때 서쪽으로 굴렀을 때 윗면
	 arr[1][3] : 윗면이 1일 때 북쪽으로 굴렀을 때 윗면
	 arr[1][4] : 윗면이 1일 때 남쪽으로 굴렀을 때 윗면
	 => 안 됨..
	 어떻게 굴렸느냐에 따라 옆면들의 번호가 다르게(?) 보일 수 있다...
	 이거때문에 시간 너무 잡아먹음 ㅠㅠ
	 
	
	 */
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(sc.nextLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(sc.nextLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		StringBuilder sb = new StringBuilder();
		st = new StringTokenizer(sc.nextLine());
		int cmd, beforeTop, top = 1, bottom = 6, mx, my;
		while(K-- > 0) {
			cmd = Integer.parseInt(st.nextToken());
			
			beforeTop = top;
			top = dice[cmd];
			bottom = 7 - top;
			
			mx = x + delR[cmd];
			my = y + delC[cmd];
						
			// 경계 벗어나면 pass -> 출력도 X
			if (mx < 0 || my < 0 || N <= mx || M <= my) {
				top = beforeTop;
				continue;
			}
			
			// 다이스 보이는 면 재정비
			// 동 서 북 남
			// 동쪽으로 굴려라
			if (cmd == 1) {
				// 동쪽으로 굴릴 경우 보일 면
				dice[1] = 7 - beforeTop;
				// 서쪽으로 굴릴 경우 보일 면
				dice[2] = beforeTop;
			}
			// 서쪽으로 굴려라
			else if (cmd == 2) {
				// 동쪽으로 굴릴 경우 보일 면
				dice[1] = beforeTop;
				// 서쪽으로 굴릴 경우 보일 면
				dice[2] = 7 - beforeTop;
			}
			// 북쪽으로 굴려라
			else if (cmd == 3) {
				// 북쪽으로 굴릴 경우 보일 면
				dice[3] = 7 - beforeTop;
				// 남쪽으로 굴릴 경우 보일 면
				dice[4] = beforeTop;
			}
			// 남쪽으로 굴려라
			else if (cmd == 4) {
				// 북쪽으로 굴릴 경우 보일 면
				dice[3] = beforeTop;
				// 남쪽으로 굴릴 경우 보일 면
				dice[4] = 7 - beforeTop;
			}
			
			if (map[mx][my] == 0) {
				// 만약 지도에 적힌 칸의 수가 0이면 -> 주사위 바닥면 수를 칸으로 복사
				map[mx][my] = diceNum[bottom];
			} else {
				// 그게 아니면 칸의 수를 주사위 바닥면으로 복사
				diceNum[bottom] = map[mx][my];
				// 칸에 쓰여 있는 수는 0
				map[mx][my] = 0;
			}
			
			x = mx;
			y = my;
			sb.append(diceNum[top]).append("\n");
		}
		
		System.out.println(sb);
	}
}
