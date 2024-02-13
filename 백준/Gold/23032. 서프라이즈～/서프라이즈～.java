import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static int[] arr;
    static int ansDiff = Integer.MAX_VALUE;
    static int ansSum;

    static void binarySearch(int s, int e) {
        int mid, g1, g2, diff;
        int start = s, end = e;
        while (s <= e) {
            mid = (s + e) / 2;

            g1 = arr[mid] - arr[start - 1];
            g2 = arr[end] - arr[mid];

            diff = Math.abs(g1 - g2);

            if(ansDiff > diff){
                ansDiff = diff;
                ansSum = g1 + g2;
            } else if (ansDiff == diff) {
                if(ansSum < g1 + g2) ansSum = g1 + g2;
            }

            if (g1 > g2) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
    }

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N  = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N - 1; i++) {
            for (int j = i + 1; j <= N; j++) {
                binarySearch(i, j);
            }
        }

        bw.write(String.valueOf(ansSum));
        bw.flush();
        bw.close();
        br.close();
    }
}