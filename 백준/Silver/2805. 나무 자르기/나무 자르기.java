import java.io.*;
import java.util.*;


public class Main {
    static int N, M;
    static int[] arr;
    static int answer = 0;

    static void binarySearch(int s, int e) {

        int mid;
        long sum;
        while (s <= e) {
            mid = (s + e) / 2;

            sum = 0;
            for (int i = 0; i < N; i++) {
                if(arr[i] >= mid) sum += arr[i] - mid;
            }

            if (sum < M) {
                e = mid - 1;
            } else {
                s = mid + 1;
                answer = Math.max(answer, mid);
            }
        }

    }

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        int max = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }

        binarySearch(0, max);


        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}