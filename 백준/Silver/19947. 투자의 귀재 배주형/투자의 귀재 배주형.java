import java.io.*;
import java.util.*;

public class Main {
    static int H, Y;
    static double[] log;
    static double[] invest = {0, 0.05, 0, 0.2, 0, 0.35};

    static int recur(int cur, int select, int sum) {
        if (cur > Y ) return Integer.MIN_VALUE;
        if (cur == Y) return (int) (sum + sum * invest[select]);

        int ret = Integer.MIN_VALUE;
        ret = Math.max(ret, recur(cur + 1, 1, (int) (sum + sum * invest[select])));
        ret = Math.max(ret, recur(cur + 3, 3, (int) (sum + sum * invest[select])));
        ret = Math.max(ret, recur(cur + 5, 5, (int) (sum + sum * invest[select])));

        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        log = new double[Y + 10];
        int ret = recur(0, 0, H);
        System.out.println(ret);
    }
}

