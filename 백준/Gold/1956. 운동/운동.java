
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		// 문제
		// V개 마을, E개 도로
		// 도로는 마을과 마을 사이에 놓여 있다. 
		// 일방 통행
		// 1번 ~ V번 마을
		
		// 사이클 찾기 
		// 사이클을 이루는 도로 길이의 합이 최소가 되도록 구한다.
		
		// 입력
		// V E
		// E 개의 줄에 a b c가 주어진다.
		// => a번 - b번 거릭 c
		
		// 시작점이 정해져있지 않으므로 다익스트라 보다 플로이드워셜 알고리즘을 사용한다.
		
		// 플로이드 워셜 알고리즘 짜기
		// 1. 플로이드 거리 테이블 int[][] dist 
			// 자기 자신으로 가는 경우 제외하고 INF 값으로 초기화
		// 2. 입력 값으로부터 dist 배열에 거리(비용) 입력 => 만약 길이 여러 개 있다면 최소값으로
		// 3. 노드를 1번 부터 V번 까지 거쳐가는 모든 경우 고려
			// 3중 for문 => dist[i][j] 와 dist[i][k]+dist[k][j] 비교 => 최소값으로 dist 배열 갱신
		
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());	// 마을
		int E = Integer.parseInt(st.nextToken());	// 도로
		
		int[][] graph = new int[V+1][V+1];
		
		// graph 배열 초기화
		for (int i = 0; i < V+1; i++) {
			for (int j = 0; j < V+1; j++) {
				graph[i][j] = INF;
			}
		}
		
		// 거리 정보 입력 받기
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int ex = Integer.parseInt(st.nextToken());
			
			graph[from][to] = ex;
		}
		
		// 3중 for문 돌리기
		for (int k = 1; k <= V; k++) {
			// 거쳐갈 기준점 노드
			for (int i = 1; i <= V; i++) {
				for (int j = 1; j <= V; j++) {
					// i에서 j로 갈 떄 
					if(graph[i][k]!=INF && graph[k][j]!=INF) {
						graph[i][j] = Math.min(graph[i][j], graph[i][k]+graph[k][j]);
					}
				}
			}
		}
		
		int ans = INF;
		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				if(i==j) ans = Integer.min(ans, graph[i][j]);
			}
		}
		
		if(ans!=INF) System.out.println(ans);
		else System.out.println(-1);
	}
}
