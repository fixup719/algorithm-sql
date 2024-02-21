import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] nums;
    static int[] answer;
    static boolean[] visited;
    static Set<String> set = new LinkedHashSet<>();
    static StringBuilder sb = new StringBuilder();

    public static void recur(int cur) {
        if (cur == M) {
            sb.setLength(0);
            for (int i = 0; i < M; i++) {
                sb.append(answer[i] + " ");
            }
            set.add(String.valueOf(sb));
            return;
        }

        for (int i = 0; i < N; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            answer[cur] = nums[i];
            recur(cur + 1);
            visited[i] = false;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[N];
        answer = new int[M];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
        recur(0);

        String[] ans = set.toArray(new String[set.size()]);
        for (String e : ans) {
            System.out.println(e);
        }

        br.close();
    }
}