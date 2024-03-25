import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int targetA, targetB;
    static int M;
    static boolean[] visited;
    static int[][] arr;
    static int answer = -1;

    static void dfs(int node, int cnt) {

        if (node == targetB) {
            answer = cnt;
            return;
        }

        visited[node] = true;

        for (int i = 1; i <= N; i++) {
            if (!visited[i] && arr[node][i] == 1) {
                dfs(i, cnt + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        targetA = Integer.parseInt(st.nextToken());
        targetB = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(br.readLine());

        arr = new int[N + 1][N + 1];
        int from, to;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());

            arr[from][to] = 1;
            arr[to][from] = 1;
        }

        visited = new boolean[N + 1];
        dfs(targetA, 0);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}