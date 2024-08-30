import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ansCnt = 0, cnt, x1, x2, y1, y2, x3, y3;
        double a, b;
        boolean flag;
        for (int i = 1; i <= N; i++) {
            x1 = i;
            y1 = arr[i];
            cnt = 0;

            for (int j = 1; j <= N; j++) {
                if (i == j) continue;

                flag = true;
                x2 = j;
                y2 = arr[j];
                // 기울기
                a = (double) (y1 - y2) / (x1 - x2);
                // 절편
                b = -a * x1 + y1;

                for (int k = Math.min(i, j) + 1; k < Math.max(i, j); k++) {
                    x3 = k;
                    y3 = arr[k];
                    if (a*x3 + b <= y3) flag = false;
                }

                if (flag) cnt++;
            }

            ansCnt = Math.max(ansCnt, cnt);
        }

        System.out.println(ansCnt);
    }
}

