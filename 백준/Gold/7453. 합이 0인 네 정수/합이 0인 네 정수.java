import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        long[][] arr = new long[n][4];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
            arr[i][3] = Integer.parseInt(st.nextToken());
        }

        long[] sumArr1 = new long[n*n];
        long[] sumArr2 = new long[n*n];
        for (int i = 0, idx = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sumArr1[idx] = arr[i][0] + arr[j][1];
                sumArr2[idx++] = arr[i][2] + arr[j][3];
            }
        }

        Arrays.sort(sumArr1);
        Arrays.sort(sumArr2);

        int s = 0, e = n * n - 1;
        long sCnt, eCnt, cnt = 0, sum = 0;
        while (s < n * n && 0 <= e) {
            sum = sumArr1[s] + sumArr2[e];

            if (sum < 0) {
                s++;
            } else if (sum > 0) {
               e--;
            } else {
                sCnt = 1;
                while (s + 1 < n * n && sumArr1[s] == sumArr1[s + 1]) {
                    sCnt++;
                    s++;
                }

                eCnt = 1;
                while (0 <= e - 1 && sumArr2[e] == sumArr2[e - 1]) {
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