import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        long[] prefix = new long[N + 1];
        Map<Integer, Integer> log = new HashMap<>();

        st = new StringTokenizer(br.readLine());
        long max = 0, diffSum; int cnt = 0;
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            prefix[i] = prefix[i - 1] + arr[i];
            if (!log.containsKey(arr[i])) {
                log.put(arr[i], i);
                diffSum = arr[i];
            }
            else diffSum = prefix[i] - prefix[log.get(arr[i]) - 1];

            if (max < diffSum) {
                max = diffSum;
                cnt = 1;
            } else if (max == diffSum) {
                cnt++;
            }
        }

        System.out.println(max + " " + cnt);
    }
}