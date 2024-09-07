import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static boolean[] visited;

    static int comb(int cur, int cnt, int kind) {
        if (cnt > M) return 0;

        if (cur == N) {
            if (cnt == M && (kind == (1 << N) - 1)) {
                return 1;
            } else {
                return 0;
            }
        }

        int ret = 0;

        ret += comb(cur + 1, cnt + 1, kind | (1 << cur));
        ret += comb(cur, cnt + 1, kind | (1 << cur));

        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        System.out.println(comb(0, 0, 0));
    }
}

