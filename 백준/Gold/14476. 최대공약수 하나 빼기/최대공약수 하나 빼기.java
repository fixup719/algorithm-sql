import java.io.*;
import java.util.StringTokenizer;


public class Main {

    public static int getGcd(int a, int b) {
        // a > b
        int tmp;
        while (b != 0) {
            tmp = a % b;
            a = b;
            b = tmp;
        }

        return a;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] prefix = new int[N];
        prefix[0] = arr[0];
        for (int i = 1; i < N; i++) {
            prefix[i] = getGcd(Math.max(arr[i], prefix[i - 1]), Math.min(arr[i], prefix[i - 1]));
        }

        int[] suffix = new int[N];
        suffix[N - 1] = arr[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            suffix[i] = getGcd(Math.max(arr[i], suffix[i + 1]), Math.min(arr[i], suffix[i + 1]));
        }


        int k, answer = -1, delK = -1, gcd;
        for (int i = 0; i < N; i++) {
            k = arr[i];

            if (0 <= i - 1 && i + 1 < N) {
                gcd = getGcd(Math.max(prefix[i - 1], suffix[i + 1])
                        ,Math.min(prefix[i - 1], suffix[i + 1]));
            } else if (i == 0) {
                gcd = suffix[i + 1];
            } else {
                gcd = prefix[i - 1];
            }

            if (k % gcd != 0) {
                if (answer < gcd) {
                    answer = gcd;
                    delK = k;
                }
            }
        }

        if (answer != -1) {
            bw.write(String.valueOf(answer + " " + delK));
        } else {
            bw.write(String.valueOf(answer));
        }

        bw.flush();
        bw.close();
        br.close();
    }
}