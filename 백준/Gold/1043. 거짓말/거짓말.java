import java.io.*;
import java.util.*;


public class Main {
    static int N, M;
    static boolean[] know;
    static int[] parent;
    static int[] party;

    static int find(int x) {
        if (parent[x] == x) return x;

        parent[x] = find(parent[x]);
        return parent[x];
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return;

        if (know[b]) parent[a] = b;
        else parent[b] = a;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        know = new boolean[N + 1];
        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }
        party = new int[M];

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        for (int i = 0; i < k; i++) {
            know[Integer.parseInt(st.nextToken())] = true;
        }

        int prv, cur;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());

            prv = Integer.parseInt(st.nextToken());
            party[i] = prv;

            for (int j = 0; j < k - 1; j++) {
                cur = Integer.parseInt(st.nextToken());
                union(prv, cur);
                prv = cur;
            }

        }

        int cnt = 0;
        for (int i = 0; i < M; i++) {
            if (!know[find(party[i])]) cnt++;
        }

        System.out.println(cnt);


    }
}
