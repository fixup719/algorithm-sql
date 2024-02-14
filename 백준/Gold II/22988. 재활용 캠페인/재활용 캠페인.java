import java.io.*;
import java.security.Key;
import java.util.*;

public class Main {
    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long X = Long.parseLong(st.nextToken());

        long[] arr = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        boolean[] used = new boolean[N];
        int cnt = 0;
        int s = 0, e = N - 1;
        while (0 <= e) {
            if (arr[e] == X) {
                cnt++;
                used[e--] = true;
            }else break;
        }

        while (s < e) {
            if (arr[s] + arr[e] + (X / 2) >= X) {
                cnt++;
                used[e--] = true;
                used[s++] = true;
            } else s++;
        }

        int notUsed = 0;
        for (int i = 0; i < N; i++) {
            if(!used[i]) notUsed++;
        }


        bw.write(String.valueOf(cnt + (notUsed / 3)));
        bw.flush();
        bw.close();
        br.close();
    }
}