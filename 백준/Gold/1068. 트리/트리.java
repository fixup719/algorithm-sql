//
import java.io.*;
import java.util.*;


public class Main {
    static int N, root, removed;
    static ArrayList<Integer>[] lst;
    static int cnt;

    static void dfs(int cur) {
        if (cur != root && lst[cur].isEmpty()) cnt++;

        for (Integer nxt : lst[cur]) {
            if (nxt == removed) {
                if (lst[cur].size() == 1) cnt++;
                continue;
            }

            dfs(nxt);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        lst = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            lst[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        int v;
        for (int i = 0; i < N; i++) {
            v = Integer.parseInt(st.nextToken());

            if (v == -1) {
                root = i;
            } else {
                lst[v].add(i);
            }
        }

        removed = Integer.parseInt(br.readLine());

        if (removed != root)  dfs(root);

        System.out.println(cnt);

        br.close();
    }
}