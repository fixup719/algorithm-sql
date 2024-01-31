import java.io.*;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] prefixA = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            prefixA[i] = prefixA[i - 1] + Integer.parseInt(st.nextToken());
        }

        int[] prefixB = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            prefixB[i] = prefixB[i - 1] + Integer.parseInt(st.nextToken());
        }

        Map<Long, Long> diffMap = new TreeMap<>(); // <A-B 차이값, 개수>
        diffMap.put(0L, 1L);
        long answer = 0;

        long diff;
        for (int j = 1; j <= N; j++) {
            diff = prefixA[j] - prefixB[j];
            if (diffMap.containsKey(diff)) {
                answer += diffMap.get(diff);
                diffMap.replace(diff, diffMap.get(diff) + 1);
            } else {
                diffMap.put(diff, 1L);
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}