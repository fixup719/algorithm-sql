import java.io.*;
import java.security.Key;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 필드 개수
        int Z = Integer.parseInt(st.nextToken()); // 도착점
        int M = Integer.parseInt(st.nextToken()); // 장애물 개수

        boolean[] isObs = new boolean[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            isObs[Integer.parseInt(st.nextToken())] = true;
        }

        int cur;
        int ans = -1;
        boolean[] visited = new boolean[N + 1];
        for (int k = 1; k < N; k++) {
            cur = 1;
            Arrays.fill(visited, false);
            while (true) {
                cur += k;
                if (cur > N) cur -= N;

                // 방문한 곳 또 방문하면 끝내버리기
                if (visited[cur]) break;
                // 장애물에 걸리면 끝내버리기
                if (isObs[cur]) break;
                if (cur == Z) {
                    ans = k;
                    break;
                }
                visited[cur] = true;
            }

            if (ans != -1) break;
        }

        System.out.println(ans);
    }
}

