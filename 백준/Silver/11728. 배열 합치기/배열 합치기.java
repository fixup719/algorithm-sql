import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arrA  = new int[N];
        int[] arrB  = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arrA[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            arrB[i] = Integer.parseInt(st.nextToken());
        }

        int[] answer = new int[N+M];
        int a = 0, b = 0, idx = 0;
        while (idx < N + M) {

            if(a >= N || b >= M){

                if (a < N) {
                    for (int i = a; i < N; i++) {
                        answer[idx++] = arrA[i];
                    }
                } else if (b < M) {
                    for (int i = b; i < M; i++) {
                        answer[idx++] = arrB[i];
                    }
                }
                break;
            }


            if (arrA[a] > arrB[b]) {
                answer[idx++] = arrB[b];
                b += 1;
            } else if (arrA[a] <= arrB[b]) {
                answer[idx++] = arrA[a];
                a += 1;
            }

        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N + M; i++) {
            sb.append(answer[i]+" ");
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}