import java.io.*;
import java.util.*;

//

/*
    학생의 코딩 실력 -10000부터 10000사이로 주어질 때, 합이 0이되는 3인조를 만들 수 있는 경우의 수 구하기
    학생수는 최대 10000명까지 가능하다
 */

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] std = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            std[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(std);

        int s, e, ts, te, sum, target;
        long answer = 0;
        for (int i = 0; i < N; i++) {
            target = std[i];

            s = 0;
            e = N - 1;
            while (s < e) {
                sum = std[s] + std[e];

                if (s == i) {
                    s++;
                    continue;
                } else if (e == i) {
                    e--;
                    continue;
                }

                if (target + sum == 0) {
                    if (s > i) {
                        ts = s;
                        te = e;

                        int sCnt = 1;
                        while (s + 1 <= te && std[s + 1] + std[e] + target == 0) {
                            sCnt++;
                            s++;
                        }
                        s++;

                        int eCnt = 1;
                        while (ts < e - 1 && std[s - 1] + std[e - 1] + target == 0) {
                            eCnt++;
                            e--;
                        }
                        e--;

                        if (std[ts] != std[te]) {
                            answer += (long) sCnt * eCnt;
                        } else {
                            answer += (long) sCnt * eCnt / 2;
                        }
                    } else {
                        s++;
                    }
                } else if (target + sum > 0) {
                    e--;
                } else {
                    s++;
                }
            }
        }

        System.out.println(answer);

        br.close();
    }
}