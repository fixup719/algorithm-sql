import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;  // 학생 수 N, 친구 관계 수 M, 가지고 있는 돈 K
    static int[] money; // 친구비
    static int[] parent;

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        } else {
            parent[x] = find(parent[x]);
            return parent[x];
        }
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return;

        parent[b] = a;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        money = new int[N + 1];
        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            money[i] = Integer.parseInt(st.nextToken());
        }

        int a, b;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            union(a, b);
        }

        int p, answer = 0;
        int[] cost = new int[N + 1];
        Arrays.fill(cost, 1 << 30);
        for (int i = 1; i <= N; i++) {
            p = find(i);
            cost[p] = Math.min(cost[p], money[i]);
        }

        for (int i = 1; i <= N ; i++) {
            if (cost[i] != 1 << 30) {
                answer += cost[i];
            }
        }

        if (answer > K) {
            bw.write("Oh no");
        } else {
            bw.write(String.valueOf(answer));
        }
        bw.flush();
        bw.close();
        br.close();
    }
}