import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        long answer = 0;
        int max = arr[0];
        int prv = arr[0];
        for (int i = 1; i < N; i++) {
             if (prv >= arr[i]) {
                answer += prv - arr[i];
             } else {
                if (max < arr[i]) answer += arr[i] - max;
             }
            prv = arr[i];
            max = Math.max(max, arr[i]);
        }
        System.out.println(answer);
    }
}