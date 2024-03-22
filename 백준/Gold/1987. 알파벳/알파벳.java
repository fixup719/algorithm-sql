
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// row : R
	// col : C
	// 말 최초 위치 : (1,1) -> (0,0)
	// 상 하 좌 우 이동
	// 방문했던 알파벳은 방문 안 됨 
	// 최대한 몇 칸 이동??

//	static boolean[][] isVisited;
	static char[][] board;
	static int R,C;
	static int[][] deltas = {{0,1},{1,0},{0,-1},{-1,0}};	//우하좌상
	static int move_cnt=1;
	static boolean[] alpha = new boolean[26];
	static int answer;
	static int flag;

	public static void dfs(int row, int col) {
		alpha[board[row][col]-'A'] = true;

		for(int dir=0; dir<4; dir++) {
			int mrow = row + deltas[dir][0];
			int mcol = col + deltas[dir][1];
			
			if(0<=mrow && mrow<R && 0<=mcol && mcol<C) {
				// 경계안에 있다면
				
				if( !alpha[board[mrow][mcol]-'A']) {
					move_cnt++;
//					System.out.printf("%d, %d = %d cnt: %d%n",mrow,mcol,board[mrow][mcol]-'A',move_cnt);
					dfs(mrow, mcol);
				}
			}
			
			if(dir==3) {
				answer = Math.max(answer, move_cnt);
				move_cnt -=1;
				alpha[board[row][col]-'A'] = false;
			}
		}
		
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		board = new char[R][C];
		for(int i=0; i<R; i++) {
			String str = br.readLine();
			for(int j=0; j<C; j++) {
				board[i][j] = str.charAt(j);
			}
		}

		int row=0,col=0;

		dfs(row,col);
		System.out.println(answer);

	}
}
