import java.io.*;
import java.util.*;


public class Main {
    static int N, C;
    static int[] x;
    static int answer = Integer.MIN_VALUE;
    static void binarySearch(int s, int e) {
        int mid, cnt, installed;
        while (s <= e) {
            mid = (s + e) / 2;

            installed = x[0];
            cnt = 1;

            for (int i = 1; i < N; i++) {
                if (x[i] - installed >= mid) {
                    cnt++;
                    installed = x[i];
                }
            }
            
            if (cnt >= C) {
                answer = Math.max(answer, mid);
                s = mid + 1;
            } else {
                e = mid - 1;
            }


        }
    }

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        x = new int[N];
        for (int i = 0; i < N; i++) {
            x[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(x);

        binarySearch(0, x[N - 1] - x[0]);

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}