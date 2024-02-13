import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        boolean[] check = new boolean[1000010];
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            check[arr[i]] = true;
        }

        int num;
        int[] score = new int[1000010];
        for (int i = 0; i < N; i++) {
            num = arr[i];

            // 약수 구하기
            for (int j = 1; j * j <= num; j++) {
                if (num % j == 0) {

                    if (check[j]) {
                        score[num] += -1;
                        score[j] += +1;
                    }

                    if (j * j != num) {
                        // 중근이 아닐 경우
                        if (check[num / j]) {
                            score[num] += -1;
                            score[num / j] += +1;
                        }
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(score[arr[i]]+" ");
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}