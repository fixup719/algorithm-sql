import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<Integer>[] list;
    static long[] arr;
    static long[] subSize;
    static final int MOD = 1000000007;

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

        N = Integer.parseInt(br.readLine());

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

        st = new StringTokenizer(br.readLine());
        arr = new long[N - 1];
        for (int i = 0; i < N - 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        subSize = new long[N + 1];
        dfs(1, 0);

        for (int i = 0; i < N + 1; i++) {
            subSize[i] = subSize[i] * (N - subSize[i]);
        }
        Arrays.sort(subSize);

        long sum = 0;
        for (int i = 0, j = N; i < N - 1 ; i++, j--) {
            sum += (arr[i] * subSize[j]) % MOD;
        }

        /*
        1
        | \
        2  5
        | \
        3  4

        3*2  1*4  1*4  1*4
        * */

        bw.write(String.valueOf(sum % MOD));
        bw.flush();
        bw.close();
        br.close();
    }
}