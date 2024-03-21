import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, E;
    static int[][] arr;

    static int cnt;
    static boolean[] visited;


    static void dfs(int node) {
        visited[node] = true;

        for (int i = 1; i <= N; i++) {
            if (visited[i] || arr[node][i] == 0) continue;

            dfs(i);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][N + 1];
        int node1, node2;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            node1 = Integer.parseInt(st.nextToken());
            node2 = Integer.parseInt(st.nextToken());

            arr[node1][node2] = 1;
            arr[node2][node1] = 1;
        }

        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {

            if (visited[i]) continue;
            dfs(i);
            cnt++;
        }

        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }
}