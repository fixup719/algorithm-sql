import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[] candies;
    static int[] parent;
    static boolean[] visited;
    static ArrayList<int[]> info;
    static int[] dp;

    static int find(int x) {
        if (parent[x] == x) return x;

        parent[x] = find(parent[x]);
        return parent[x];
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return;

        parent[a] = b;
    }

    static int[] getConenctedInfo(int cur) {
        int[] answer = new int[2];

        answer[0] = 1;
        answer[1] = candies[cur];
        for (int i = 1; i <= N; i++) {
            if (cur == i) continue;

            if (find(i) == find(cur)){
                visited[i] = true;
                answer[0]++;
                answer[1] += candies[i];
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        candies = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            candies[i] = Integer.parseInt(st.nextToken());
        }

        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        int v1, v2;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());

            union(v1, v2);
        }

        visited = new boolean[N + 1];
        info = new ArrayList<>();// {연결요소 크기, 캔디 개수}

        // 연결요소 크기 + 연결요소별 캔디 개수 구하기
        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;

            visited[i] = true;

            info.add(getConenctedInfo(i));
        }

        Collections.sort(info, ((o1, o2) -> {
            return o2[1] - o1[1];}));

        dp = new int[K + 10];

        for (int i = 0; i < info.size(); i++) {
            for (int j = K - 1; j >= 0; j--) {
                if (j - info.get(i)[0] < 0) continue;
                dp[j] = Math.max(dp[j], dp[j - info.get(i)[0]] + info.get(i)[1]);
            }
        }

        bw.write(String.valueOf(dp[K - 1]));
        bw.flush();
        bw.close();
        br.close();
    }
}