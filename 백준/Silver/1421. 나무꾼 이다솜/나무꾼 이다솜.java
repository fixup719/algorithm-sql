import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[] length = new int[N];
        int max = 0;
        for (int i = 0; i < N; i++) {
            length[i] = Integer.parseInt(br.readLine());

            max = Math.max(max, length[i]);
        }

        int cutCnt, k;
        long benefit, answer = 0;
        for (int i = 1; i <= max; i++) {

            benefit = 0;
            for (int j = 0; j < N; j++) {
                cutCnt = 0;
                k = 0;
                if (length[j] >= i) {
                    k = length[j] / i;
                    cutCnt = length[j] / i;
                    if(length[j] % i == 0){
                        cutCnt--;
                    }

                    if ((long)k * W * i - (long)cutCnt * C >= 0) {
                        benefit += (long)k * W * i - (long)cutCnt * C;
                    }

                }
            }

            answer = Math.max(answer, benefit);
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}