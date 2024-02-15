import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] arr;
    static boolean[] visited;

    public static void recur(int cur) {
        if (cur == M) {
            for (int i = 0; i < M; i++) {
                System.out.print(arr[i]+" ");
            }
            System.out.println();
            return;
        }

        for (int i = 1; i <= N; i++) {
            if(visited[i]) continue;

            visited[i] = true;
            arr[cur] = i;
            recur(cur + 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M];
        visited = new boolean[N + 1];

        recur(0);

        br.close();
    }
}