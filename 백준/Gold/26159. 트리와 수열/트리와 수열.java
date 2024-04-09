import java.io.*;
import java.util.*;

public class Main {
    static final int DIVIDE_NUM = 1_000_000_007;
    static int N;
    static Integer[] arr;
    static long[] size;
    static List<Long> ans;
    static List<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        size = new long[N+1];
        arr = new Integer[N-1];
        ans = new ArrayList<>();
        list = new ArrayList[N+1];

        for(int i = 1 ; i <= N ; i ++){
            list[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < N-1 ; i ++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N-1 ; i ++)
            arr[i] = Integer.parseInt(st.nextToken());

        dfs(1, -1);

        Collections.sort(ans);
        Arrays.sort(arr, Collections.reverseOrder());

        long res = 0;
        for(int i = 0 ; i < N-1 ; i ++){
            res += (arr[i] * ans.get(i)) % DIVIDE_NUM;
        }

//        System.out.println(Arrays.toString(size));
//        for (int i = 0; i < ans.size(); i++) {
//            System.out.print(ans.get(i) + " ");
//        }
//        System.out.println();
//
//        System.out.println(Arrays.toString(arr));

        System.out.println(res % DIVIDE_NUM);
    }

    private static void dfs(int cur, int prev) {
        size[cur] = 1;

        for(int next: list[cur]){
            if(next == prev) continue;

            dfs(next, cur);

            size[cur] += size[next];
            ans.add((size[next] * (N -  size[next])));
        }
    }
}