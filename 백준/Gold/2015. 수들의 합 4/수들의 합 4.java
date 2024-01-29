import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] prefix = new long[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            prefix[i] = prefix[i-1] + Integer.parseInt(st.nextToken());
        }

        Map<Long, Long> cntMap = new TreeMap<>();
        cntMap.put(0L, 1L);
        long ans = 0;
        for (int i = 1; i <= N; i++) {
            if(cntMap.containsKey(prefix[i]-K)) ans += cntMap.get(prefix[i]-K);

            if(cntMap.containsKey(prefix[i])) cntMap.replace(prefix[i], cntMap.get(prefix[i])+1);
            else cntMap.put(prefix[i], 1L);
        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}