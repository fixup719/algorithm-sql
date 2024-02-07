import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
    static int T, N, M;
    static int[] A;
    static int[] B;

    static int binarySearch(int s, int e, int target) {
        int mid;
        while (s <= e) {
            mid = (s + e) / 2;
            if (target > A[mid]) {
                s = mid + 1;
            } else if (target < A[mid]) {
                e = mid - 1;
            } else {
                return 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());

            A = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            M = Integer.parseInt(br.readLine());
            B = new int[M];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                B[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(A);
            for (int i = 0; i < M; i++) {
                sb.append(binarySearch(0, N - 1, B[i])+"\n");
            }
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}