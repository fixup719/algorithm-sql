import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static StringBuilder sb = new StringBuilder();

    static boolean isPrime(int number) {

        if(number == 1 || number == 0) return false;

        int cnt = 0;
        for (int i = 2; i * i <= number ; i++) {
            if (number % i == 0) {
                cnt++;
            }
        }

        if (cnt == 0) return true;
        else return false;
    }
    static void recur(int cur, int number) {

        if (cur == N) {
            sb.append(number + "\n");
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (cur == 0 && i == 0) continue;

            if(cur > 0) number = number * 10;
            number += i;

            if (!isPrime(number)){
                number -= i;
                if(cur > 0) number /= 10;
                continue;
            }

            recur(cur + 1, number);

            if(cur > 0) number -= i;
            number /= 10;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        recur(0, 0);

        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
        br.close();
    }
}