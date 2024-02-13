import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N  = Integer.parseInt(br.readLine());

        int[] odd = new int[N + 1];
        int[] even = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            if (i % 2 == 1) {
                // 홀수 번째
                odd[i] = odd[i - 1] + Integer.parseInt(st.nextToken());
                even[i] += even[i - 1];
            } else {
                // 짝수 번째
                even[i] = even[i - 1] + Integer.parseInt(st.nextToken());
                odd[i] += odd[i - 1];
            }
        }


        int answer = odd[N - 1];
        for (int i = 1; i < N; i++) {
            if (i % 2 == 1) {
                // 정훈이 차례 밑장 빼기
                answer = Math.max(answer, odd[i - 1] + even[N] - even[i - 1]);
            } else {
                // 상대방 차례 밑장 빼기
                answer = Math.max(answer, odd[i - 1] + even[N - 1] - even[i - 1]);
            }

        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}