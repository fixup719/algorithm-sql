import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N+1];
        st = new StringTokenizer(br.readLine());
        int s = -1, e = -1;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());

            if (s == -1 && arr[i] % 2 == 0) {
                s = i;
                e = i;
            }
        }

        int delCnt = 0, len = 1, answer = 0;
        while (0 <= e && e < N) {

            if (delCnt > K) {
                if (arr[s] % 2 == 0) len--;
                else delCnt--;
                s++;
            } else {
                answer = Math.max(answer, len);
                e++;
                if (arr[e] % 2 == 0) len++;
                else delCnt++;
            }

        }


        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}