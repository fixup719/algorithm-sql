import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {


    static long getLCM(long a, long b, long gcd){
        return  a/gcd*b;
    }

    static long getGCD(long a, long b){
        long tmp = 0;
        while (b != 0) {
            tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long gcd = Integer.parseInt(st.nextToken());
        long lcm = Integer.parseInt(st.nextToken());

        // gcd * lcm = a * b;
        // => gcd * lcm의 약수 먼저 구하기
        // 그다음 LCM, GCD 구하고 일치하면 출력

        long sum  = Long.MAX_VALUE;
        long n = gcd * lcm;
        long[] answer = new long[2];
        for (long i = 1; i * i <= n; i++) {
            if ((gcd * lcm) % i == 0) {
                if(getGCD(i, n / i) == gcd && getLCM(i, n / i, gcd) == lcm){
                    if (sum > i + n / i) {
                        sum = i + n / i;
                        answer[0] = i;
                        answer[1] = n / i;
                    }
                }
            }
        }

        Arrays.sort(answer);
        System.out.println(answer[0] + " " + answer[1]);

    }
}