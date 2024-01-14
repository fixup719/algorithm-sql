import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static boolean isPrime(int num){
        int tmp = num;
        int cnt = 0;
        for (int j = 2; j * j <= num; j++) {
            while (tmp % j == 0) {
                tmp /= j;
                cnt++;
            }
        }
        if(cnt==0) return true;
        else return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 1. 팰린드롬인지 체크 -> 2. 소수인지 체크

        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        boolean check;
        for (int i = a; i <= b; i++) {

            // 팰린드롬
            check = false;
            // 팰린드롬 체크
            if (i < 10) {
                check = true;
            } else if (10 <= i && i <= 99) {
                if (i / 10 == i % 10) {
                    check = true;
                }
            } else if (100 <= i && i <= 999) {
                if (i / 100 == i % 10) {
                    check = true;
                }
            } else if (1000 <= i && i <= 9999) {
                if (i / 1000 == i % 10
                        && i / 100 % 10 == i % 100 / 10) {
                    check = true;
                }
            } else if (10000 <= i && i <= 99999) {
                if (i / 10000 == i % 10
                        && i / 1000 % 10 == i % 100 / 10) {
                    check = true;
                }
            } else if (100000 <= i && i <= 999999) {
                if (i / 100000 == i % 10
                        && i / 10000 % 10 == i % 100 / 10
                        && i / 1000 % 10 == i % 1000 / 100) {
                    check = true;
                }
            } else if (1000000 <= i && i <= 9999999) {
                if (i / 1000000 == i % 10
                        && i / 100000 % 10 == i % 100 / 10
                        && i / 10000 % 10 == i % 1000 / 100) {
                    check = true;
                }
            } else {
                if (i / 10000000 == i % 10
                        && i / 1000000 % 10 == i % 100 / 10
                        && i / 100000 % 10 == i % 1000 / 100
                        && i / 10000 % 10 == i % 10000 / 1000) {
                    check = true;
                }
            }

            // 소수 판정
            if(check && isPrime(i)) sb.append(i + "\n");

        }

        sb.append(-1);
        System.out.println(sb);


    }
}