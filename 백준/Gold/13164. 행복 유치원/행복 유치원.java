import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dist = new int[N - 1];
        int sum = 0;
        for (int i = 0; i < N - 1; i++) {
            dist[i] = arr[i + 1] - arr[i];
            sum += dist[i];
        }

        Arrays.sort(dist);

        int idx = N - 2, minusSum = 0;
        while (idx >= N - K) {
            minusSum += dist[idx];
            idx--;
        }

        System.out.println(sum - minusSum);
    }
}