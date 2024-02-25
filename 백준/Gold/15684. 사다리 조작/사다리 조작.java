
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	static int[][] map;
	static int N, H;
	//	static int[][] deltas = {{0,-1},{0,1},{1,0}};	// 좌, 우, 하

	// 자기 자신으로 돌아오는 지 체크하는 메소드
	private static boolean ladderCheck() {
		boolean answer = false;

		for(int start=0; start<N; start++) {
			int row = 0, col = start;

			while(row<H) {
				if(map[row][col]==1) {
					col++;
				}else if(map[row][col]==2) {
					col--;
				}
				row++;
			}

			if(start == col) {
				answer = true;
			}else if(start!=col) {
				answer = false;
				break;
			}
		}
		return answer;
	}

	// 가로선 놓는 메소드 
	private static void dfs(int cnt, int max) {

		if(cnt==max) {
			if(ladderCheck()) {
				System.out.println(max);
				System.exit(0);
			}
			return;
		}


		for(int i=0; i<H; i++) {
			for(int j=0; j<N-1; j++) {
				// 두 가로선이 서로 연속하거나 접하면 안 된다는 조건 있음
				if(map[i][j] == 0 &&  map[i][j+1]==0) {
					map[i][j] = 1;
					map[i][j+1] = 2;
					dfs(cnt+1, max);
					map[i][j] = 0;
					map[i][j+1] = 0;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// N : 세로선
		// M : 가로선
		// H : 각각의 세로선마다 가로선을 놓을 수 있는 위치 개수

		// i번 세로선 결과가 i번이 나와야 함
		// 추가해야하는 가로선 개수 최소값?
		// 불가능하거나 3보다 큰 값이면 -1 출력

		// N M H
		// 가로선 정보  a b
		// b, b+1을 a점 위치에서 연결

		// 초기 0으로 셋팅
		// 가로선 연결된 점은 1로 셋팅
		// 처음 1 만나면 1있는 쪽으로 좌우 셋팅
		// 만약 델타가 0 or 1일경우 상하로 셋팅(2,3)
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		map = new int[H][N];
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;

			map[a][b] = 1;
			map[a][b+1] = 2;
		}

		// 가로선 수가 3보다 커지면 -1출력
		for(int i=0; i<=3; i++) {
			dfs(0,i);
		}
		System.out.println(-1);

	}
}
