import java.io.*;
import java.security.Key;
import java.util.*;

public class Main {
    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int N;
        StringTokenizer st;
        String[] arr;
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            if (32 < N) {
                sb.append(0+"\n");
            } else {
                arr = new String[N];
                for (int i = 0; i < N; i++) {
                    arr[i] = st.nextToken();
                }
                int diff = 0;
                int ans = Integer.MAX_VALUE;
                for (int i = 0; i < N; i++) {
                    for (int j = i + 1; j < N; j++) {
                        for (int k = j + 1; k < N; k++) {
                            diff = 0;
                            for (int l = 0; l < 4; l++) {
                                if (arr[i].charAt(l) != arr[j].charAt(l)) diff++;
                                if (arr[i].charAt(l) != arr[k].charAt(l)) diff++;
                                if (arr[j].charAt(l) != arr[k].charAt(l)) diff++;
                            }
                            ans = Math.min(ans, diff);
                        }
                    }
                }
                sb.append(ans+"\n");
            }
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}