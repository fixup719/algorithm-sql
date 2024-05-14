import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static int[] arr;
    static int[] prefix;
    static int[] suffix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        prefix = new int[N + 1];
        suffix = new int[N + 2];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            prefix[i] = prefix[i - 1] + arr[i];
        }

        for (int i = N; i >= 1; i--) {
            suffix[i] = suffix[i + 1] + arr[i];
        }

        int answer = 0, sum;

        // 꿀벌 - 꿀통 - 꿀벌
        for (int i = 2; i <= N - 1; i++) {
            sum = prefix[i] - prefix[1] + suffix[i] - suffix[N];
            answer = Math.max(answer, sum);
        }

        // 꿀벌1,2 - 꿀통
        for (int i = 2; i < N; i++) {
            sum = prefix[N] - prefix[1];
            sum += (prefix[N] - prefix[i]) - arr[i];
            answer = Math.max(answer, sum);
        }

        // 꿀통 - 꿀벌1,2
        for (int i = N - 1; i >= 2; i--) {
            sum = suffix[1] - suffix[N];
            sum += (suffix[1] - suffix[i]) - arr[i];
            answer = Math.max(answer, sum);
        }

        System.out.println(answer);

    }
}