import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, R1, R2;
    static int[][] arr;
    static boolean[] visited;
    static int sum = 1 << 30;
    static int road;

    static void dfs(int node, int total, int max) {

        if (node == R2){
            sum = Math.min(total, sum);
            road = max;
        }


        for (int i = 1; i <= N; i++) {
            if (visited[i] || arr[node][i] == 0) continue;

            visited[i] = true;
            dfs(i, total + arr[node][i], Math.max(max, arr[node][i]));
            visited[i] = false;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R1 = Integer.parseInt(st.nextToken());
        R2 = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][N + 1];
        int node1, node2, cost;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            node1 = Integer.parseInt(st.nextToken());
            node2 = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());

            arr[node1][node2] = cost;
            arr[node2][node1] = cost;
        }

        visited = new boolean[N + 1];
        visited[R1] = true;
        dfs(R1, 0, 0);

        bw.write(String.valueOf(sum - road));
        bw.close();
        br.close();
    }
}