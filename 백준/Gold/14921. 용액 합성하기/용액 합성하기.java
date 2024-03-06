import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int s = 0, e = N - 1, sum;
        int answer = Math.abs(0 - (arr[s] + arr[e]));
        int water1 = arr[s];
        int water2 = arr[e];
        while (s < e && e < N) {
            sum = arr[s] + arr[e];

            if (answer > Math.abs(0 - sum)) {
                answer = Math.abs(0 - sum);
                water1 = arr[s];
                water2 = arr[e];
            }


            if (sum < 0) {
                s++;
            } else {
                e--;
                if(sum == 0) break;
            }
        }


        bw.write(String.valueOf(water1 + water2));
        bw.flush();
        bw.close();
        br.close();
    }
}