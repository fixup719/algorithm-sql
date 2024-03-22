import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        /*
        * 매순간 1위인 애한테서 뺏기
        * */
        int cnt = 0, topIdx = 0, max = 0;

        while (true) {
            max = 0;
            for (int j = 0; j < N; j++) {
                if (max <= arr[j]) {
                    max = arr[j];
                    topIdx = j;
                }
            }

            if (topIdx != 0) {
                arr[0]++;
                arr[topIdx]--;
                cnt++;
            } else break;
        }

        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }
}