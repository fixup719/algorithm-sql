import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static int[][] board;
	static int[] delR = {-1,0,1,0}; // 행 이동 상우하좌
	static int[] delC = {0,1,0,-1}; // 열 이동 상우하좌
	static ArrayList<int[]> snake = new ArrayList<>();
	static int curDir, time=0;
	static boolean flag;
	

	
	static boolean onMoveSnake() {
		int mHeadR=0, mHeadC=0;
		
		mHeadR = snake.get(snake.size()-1)[0] + delR[curDir];
		mHeadC = snake.get(snake.size()-1)[1] + delC[curDir];
		
		
		// 게임 종료 조건 : 벽에 부딪힐 때
		if(mHeadR<0 || mHeadC<0 || N<=mHeadR || N<=mHeadC) return false;
		
		// 게임 종료 조건 : 자기 몸에 부딪힐 떼
		for(int i=0; i<snake.size(); i++) {
			if(snake.get(i)[0] == mHeadR && snake.get(i)[1] == mHeadC) return false; 
		}
		
		
		if(board[mHeadR][mHeadC] == 1) {
			// 사과가 있다면
			// 머리추가
			snake.add(new int[] {mHeadR, mHeadC});
			// 사과 먹었으면 치워야지..
			board[mHeadR][mHeadC] = 0;
		}else{
			// 사과 없다면
			// 머리 추가 && 꼬리 제거
			snake.add(new int[] {mHeadR, mHeadC});
			snake.remove(0);
		}
		
		
		return true;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine()); // 보드 크기
		K = Integer.parseInt(br.readLine()); // 사과 개수

		board = new int[N][N]; // 보드 선언
		
		// 1 : 사과 있는 곳 
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			board[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 1;
		}
		
		int L  = Integer.parseInt(br.readLine()); // 방향 전환 갯수
		Queue<int[]> cmd = new LinkedList<>();
		for(int i=0; i<L; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken()); // 게임 시작시간으로 부터 X초
			char dir = st.nextToken().charAt(0); // D : 우 , L : 좌
			cmd.offer(new int[] {X, dir});
		}
		snake.add(new int[] {0,0});
		
		curDir = 1; // 현재 뱀이 바라보는 방향 : 우측
		int X=cmd.peek()[0], dir=0;
		while(true) {
			time++;
			if(time == X+1) {
				// 바라보는 방향 => D우, L좌 시 이동 방향 +1 %4 / +3 %4 
				// 상 => 우, 좌 0 => 1, 3  
				// 하 => 좌, 우 2 => 3, 1 
				// 좌 => 상, 하 3 => 0, 2
				// 우 => 하, 상 1 => 2, 0
				if(!cmd.isEmpty()) {
					dir = (char) cmd.poll()[1];	
					if(!cmd.isEmpty()) X = cmd.peek()[0]; // 앞에서 poll 했기 때문에 비어있을 수 있음
				}
				
				// 명령어에 따라 바라보는 방향 셋팅
				if(dir=='D'){
					// 오른쪽
					curDir = (curDir+1)%4;
				}else if(dir=='L') {
					// 왼쪽 
					curDir = (curDir+3)%4;
				}		
			}
			
			// 뱀은 매초마다 이동한다
			// 반환값이 false면 게임 종료이므로 break
			boolean flag = onMoveSnake();
			if(!flag) break ;
			
			
		}
		
		System.out.println(time);
	}

}