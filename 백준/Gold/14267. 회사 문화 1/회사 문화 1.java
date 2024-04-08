import java.io.*;
import java.util.*;

public class Main {
    static int N, M; // 노드 개수, 칭찬 횟수
    static ArrayList<Integer>[] list;
    static int[] check;
    static int[] score; // 칭찬 점수

    /*
    1
    |
    2
    |
    3
    |
    4
    |
    5
    * */

    // {0,0,2,4,0,6}
    static void dfs(int cur, int prv, int total) {
        if (check[cur] > 0) {
            total += check[cur];
        }

        score[cur] += total;

        for (Integer nxt : list[cur]) {
            if (prv == nxt) continue;

            dfs(nxt, cur, total);
        }
    } 

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        int parent;
        for (int c = 1; c <= N; c++) {
            parent = Integer.parseInt(st.nextToken());

            if (parent != -1) list[parent].add(c);
        }

        score = new int[N + 1];
        check = new int[N + 1];
        int start, c;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            check[start] += c;
        }

        dfs(1, 0, 0);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(score[i] + " ");
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}