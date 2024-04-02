
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main{
	static int N;
	static int[][] map;
	static int money;
	static boolean[][] visited;
	static int[][] deltas = {{0,1}, {0,-1}, {1,0}, {-1,0}};
	
	static class Link implements Comparable<Link>{
		int row,col,money;

		public Link(int row, int col, int money) {
			super();
			this.row = row;
			this.col = col;
			this.money = money;
		}

		@Override
		public int compareTo(Link o) {
			// TODO Auto-generated method stub
			return this.money-o.money;
		}
		
		
	}
	
	static int bfs(int row, int col, int money) {
		int ans = 0;
		
		PriorityQueue<Link> q = new PriorityQueue<>();
		q.offer(new Link(row,col,money));
		visited[row][col] = true;
		
		while(!q.isEmpty()) {
			Link temp = q.poll();
			
			if(temp.row==N-1 && temp.col==N-1) {
				// 도착지점에 도달했을 때
//				System.out.println(temp.money);
				ans = temp.money;
				break;
			}
			
//			System.out.println(temp.money);
			for (int dir = 0; dir < 4; dir++) {
				int mrow = temp.row + deltas[dir][0];
				int mcol = temp.col + deltas[dir][1];
			
				if(mrow<0 || mcol<0 || mrow>=N || mcol>=N) continue;
				
				if(!visited[mrow][mcol]) {
					q.offer(new Link(mrow, mcol, temp.money+map[mrow][mcol]));
					visited[mrow][mcol] = true;
				}
			}
		}
		
		return ans;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// input
		// 동굴의 크기 N
		// N개의 줄에 걸쳐
			// 각 칸의 도둑 루피 크기가 주어 짐
			// 도둑 루피 크기가 k면 이 칸을 지날 때 k루피 잃음
		
		// output
			// 정답을 형식에 맞춰 출력 => 링크가 잃을 수밖에 없는 최소 금액?
		
		// (0,0) 에서 (N-1,N-1)로 이동해야 함
		// 링크를 잃는 금액을 최소로 해서 지나가야함 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		
		int index=1;
		while(true) {
			 N = Integer.parseInt(br.readLine()); 
			 
			 if(N<=0) break;
			 
			 map = new int[N][N];
			 for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			money=map[0][0];
			visited = new boolean[N][N];
			int answer = bfs(0,0,money);
			sb.append("Problem "+ (index++) + ": "+answer+"\n");
		}
		System.out.println(sb);
		
	}
}
