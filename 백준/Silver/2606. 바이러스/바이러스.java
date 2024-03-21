import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, E;
    static int[][] arr;
    static boolean[] visited;
    static int cnt;

    static void dfs(int node) {
        visited[node] = true;

        for (int i = 1; i <= N; i++) {

            if (visited[i]) continue;

            if (arr[node][i] == 1 && arr[i][node] == 1) {
                cnt++;
                dfs(i);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());

        arr = new int[N + 10][N + 10];
        int node1, node2;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            node1 = Integer.parseInt(st.nextToken());
            node2 = Integer.parseInt(st.nextToken());

            arr[node1][node2] = 1;
            arr[node2][node1] = 1;
        }

        visited = new boolean[N + 1];

        dfs(1);

        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }
}