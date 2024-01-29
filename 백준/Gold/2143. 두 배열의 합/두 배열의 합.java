import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        int n = Integer.parseInt(br.readLine());
        long[] accA = new long[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            accA[i] = accA[i - 1] + Integer.parseInt(st.nextToken());
        }


        int m = Integer.parseInt(br.readLine());
        long[] accB = new long[m + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            accB[i] = accB[i - 1] + Integer.parseInt(st.nextToken());
        }

        ArrayList<Long> subA = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                subA.add(accA[j] - accA[i - 1]);
            }
        }

        ArrayList<Long> subB = new ArrayList<>();
        for (int i = 1; i <= m; i++) {
            for (int j = i; j <= m; j++) {
                subB.add(accB[j] - accB[i - 1]);
            }
        }

        Collections.sort(subA);
        Collections.sort(subB);

        int s = 0, e = subB.size() - 1;
        long cnt = 0, sCnt, eCnt;
        while (s < subA.size() && 0 <= e) {
            if (subA.get(s) + subB.get(e) < T) {
                s++;
            } else if (subA.get(s) + subB.get(e) > T) {
                e--;
            } else {
                sCnt = 1;
                while (s + 1 < subA.size() && subA.get(s + 1) + subB.get(e) == T) {
                    sCnt++;
                    s++;
                }
                s++;

                eCnt = 1;
                while (0 < e && subA.get(s - 1) + subB.get(e - 1) == T) {
                    eCnt++;
                    e--;
                }
                e--;

                cnt += sCnt * eCnt;
            }
        }

        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }
}