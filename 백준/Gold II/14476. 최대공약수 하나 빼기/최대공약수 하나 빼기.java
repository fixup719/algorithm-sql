import java.io.*;
import java.util.*;


public class Main {

    static int getGCD(int a, int b) {

        int tmp;
        while (b != 0) {
            tmp = a % b;
            a = b;
            b = tmp;
        }

        return a;
    }

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] prefix = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            prefix[i] = getGCD(Math.max(prefix[i - 1], arr[i]), Math.min(prefix[i - 1], arr[i]));
        }

        int[] suffix = new int[N + 2];
        for (int i = N; i >= 1; i--) {
            suffix[i] = getGCD(Math.max(suffix[i + 1], arr[i]), Math.min(suffix[i + 1], arr[i]));
        }

        int k, gcd, ansGcd = -1, ansK = 0;
        for (int i = 1; i <= N; i++) {
            k = arr[i];
            gcd = getGCD(Math.max(prefix[i - 1], suffix[i + 1]), Math.min(prefix[i - 1], suffix[i + 1]));
            if (k % gcd != 0) {
                ansGcd = Math.max(ansGcd, gcd);
                ansK = k;
            }
        }

        if (ansGcd != -1) {
            bw.write(String.valueOf(ansGcd + " " + ansK));
        } else {
            bw.write(String.valueOf(ansGcd));
        }

        bw.flush();
        bw.close();
        br.close();
    }
}