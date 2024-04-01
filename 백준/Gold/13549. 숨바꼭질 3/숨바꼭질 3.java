import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static boolean[] visited;
    static int[] add = {0, -1, 1};
    static int[] mul = {2, 1, 1};
    static ArrayList<Integer> answer = new ArrayList<>();


    static void bfs(int node) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{node, 0});
        visited[node] = true;

        int cur, time, nxt;
        while (!q.isEmpty()) {
            cur = q.peek()[0];
            time = q.poll()[1];

            if (cur == K) { answer.add(time);}
            if (!answer.isEmpty() && time > answer.get(0)) continue;


            // 방문 처리하기 때문에 곱하기를 가장 우선으로 한다.
            for (int i = 0; i < 3; i++) {
                nxt = mul[i] * cur + add[i];

                if (nxt < 0 || 200000 < nxt || visited[nxt]) continue;

                if (0 < i) q.offer(new int[]{nxt, time + 1});
                else q.offer(new int[]{nxt, time});

                visited[nxt] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited = new boolean[200020];

        bfs(N);

        Collections.sort(answer);

        bw.write(String.valueOf(answer.get(0)));
        bw.flush();
        bw.close();
        br.close();
    }
}