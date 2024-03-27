import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        long sum = 0;
        for (int i = 0; i < N - 1; i++) {
            sum += arr[i];
        }

        if (sum <= arr[N - 1]) bw.write(String.valueOf(arr[N - 1] - sum));
        else bw.write(String.valueOf((arr[N - 1] + sum) % 2));

        bw.flush();
        bw.close();
        br.close();
    }
}