import java.io.*;
import java.util.*;

public class Main {
    static int N, R, Q; // 정점의 수, 루트 번호, 쿼리 수
    static ArrayList<Integer>[] list; // 그래프 정보
    static int[] subSize;

    static void dfs(int cur, int prv) {
        subSize[cur] = 1;

        for (Integer nxt : list[cur]) {
            if (nxt == prv) continue;

            dfs(nxt, cur);
            subSize[cur] += subSize[nxt];
        }
    }

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }

        int node1, node2;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            node1 = Integer.parseInt(st.nextToken());
            node2 = Integer.parseInt(st.nextToken());

            list[node1].add(node2);
            list[node2].add(node1);
        }

        subSize = new int[N + 1];
        dfs(R, 0);

        int U;
        StringBuilder sb = new StringBuilder();
        while (Q-- > 0) {
            U = Integer.parseInt(br.readLine());
            sb.append(subSize[U] + "\n");
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}