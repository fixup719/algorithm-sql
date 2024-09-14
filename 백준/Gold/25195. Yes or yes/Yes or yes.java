import java.io.*;
import java.util.*;

public class Main {
    static int N, M, S;
    static ArrayList<Integer>[] lst;
    static boolean[] fans = new boolean[100_010];

    static int dfs(int cur) {
        if (fans[cur]) return 1;
        if (lst[cur].isEmpty()) return 0;

        int ret = 1 << 30;
        for (Integer nxt : lst[cur]) {
            ret = Math.min(ret, dfs(nxt));
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        lst = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            lst[i] = new ArrayList<>();
        }

        int a, b;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            lst[a].add(b);
        }

        S = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < S; i++) {
            fans[Integer.parseInt(st.nextToken())] = true;
        }

        int ret = dfs(1);

        if (ret > 0) System.out.println("Yes");
        else System.out.println("yes");
    }
}

