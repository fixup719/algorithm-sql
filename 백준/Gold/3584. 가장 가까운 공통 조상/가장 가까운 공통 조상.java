import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception{
		// 가장 가까운 공통 조상: 두 노드를 모두 자손으로 가지면서 깊이가 가장 깊은 노드
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine()); // 노드 개수
		
			// A B => A가 B의 부모
			
			// 부모가 누군지 담는 배열
			int[] parent = new int[N+1];
			for (int i = 1; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				parent[b] = a; 
			}
			
			// 가장 가까운 조상 찾을 노드
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			// 깊이가 깊다 == 밑에서 부터 부모 노드 차례대로 찾기
			boolean[] visited = new boolean[N+1];
			
			// 부모 노드 방문 체크
			while(node1 != 0) {
				visited[node1] = true;
				node1 = parent[node1];
			}
			
			while(node2 != 0 && !visited[node2]) {
				node2 = parent[node2];
			}
			
			System.out.println(node2);
			
			// 자기 자신도 자손
			
		}
	}
}
