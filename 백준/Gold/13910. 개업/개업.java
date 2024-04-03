import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr;
    static boolean[] visited;

    static int bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);

        int cnt = 0, cur, nxt, size, s, e;
        while (!q.isEmpty()) {
            size = q.size();

            for (int i = 0; i < size; i++) {
                cur = q.poll();

                if (cur == N) return cnt;

                // 웍을 동시에 사용할 수 있음 -> 주의
                for (int j = 0; j < M; j++) {
                    for (int k = 0; k < M; k++) {
                        nxt = j == k ? cur + arr[j] : cur + arr[j] + arr[k];

                        if (10000 < nxt || visited[nxt]) continue;
                        if (N < nxt) continue;

                        q.offer(nxt);
                        visited[nxt] = true;
                    }
                }
            }
            cnt++;
        }

        return -1;
    }

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[10010];

        Arrays.sort(arr);

        bw.write(String.valueOf(bfs(0)));
        bw.flush();
        bw.close();
        br.close();
    }
}