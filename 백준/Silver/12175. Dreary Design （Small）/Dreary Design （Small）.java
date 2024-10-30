import java.io.*;
import java.util.*;

public class Main {
    static int K, V;
    static int[] arr = new int[3];

    static int recur(int cur) {
        if (cur == 3) return 1;

        int ret = 0;
        boolean flag;
        for (int i = 0; i <= K; i++) {
            flag = true;
            for (int j = 0; j < cur; j++) {
                if (Math.abs(arr[j] - i) > V) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                arr[cur] = i;
                ret += recur(cur + 1);
            }
        }
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        int ret;
        for (int tc = 1; tc <= T; tc++) {
            sb.append("Case #").append(tc).append(": ");
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            V = Integer.parseInt(st.nextToken());

            ret = recur(0);

            sb.append(ret).append("\n");
        }

        System.out.println(sb);
    }
}