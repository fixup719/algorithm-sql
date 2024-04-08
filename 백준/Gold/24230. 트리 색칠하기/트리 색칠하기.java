import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<Integer>[] list;
    static int[] color;
    static int answer;


    // 0 0 2 0 1 2 2
    static void dfs(int cur, int prv, int paint) {

        if (paint != color[cur]) answer++;

        if (color[cur] > 0){
            paint = color[cur];
        }


        for (Integer nxt : list[cur]) {
            if (nxt == prv) continue;

            dfs(nxt, cur, paint);
        }
    }

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        list = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }

        color = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            color[i] = Integer.parseInt(st.nextToken());
        }

        int node1, node2;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            node1 = Integer.parseInt(st.nextToken());
            node2 = Integer.parseInt(st.nextToken());

            list[node1].add(node2);
            list[node2].add(node1);
        }

        dfs(1, 0, 0);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}