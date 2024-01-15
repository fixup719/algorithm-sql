

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int cntTwo(long n){
        int answer = 0;

        long i = 2;

        while (i <= n) {
            answer += n / i;
            i *= 2;
        }

        return answer;
    }

    static int cntFive(long n){
        int answer = 0;

        long i = 5;

        while (i <= n) {
            answer += n / i;
            i *= 5;
        }

        return answer;
    }

//    static BigInteger factorial(int n){
//        if(n == 1) return new BigInteger(String.valueOf(1));
//        return new BigInteger(String.valueOf(factorial(n-1)))
//                .multiply(new BigInteger(String.valueOf(n)));
//    }
//
//    static BigInteger factorial2(int n, int r){
//        if(n == n-r+1) return new BigInteger(String.valueOf(n));
//        return new BigInteger(String.valueOf(factorial2(n-1, r)))
//                .multiply(new BigInteger(String.valueOf(n)));
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());
        long m = Long.parseLong(st.nextToken());

        // n!에서 2,5 개수 - ((n-r)!에서 2,5 개수 + r!에서 2,5개수)
        // => 2의개수끼리 빼고, 5의개수끼리 뺸다음 최솟값만큼... 이게 왜 아닐까,,,ㅠㅠㅠㅠ


        System.out.println( Math.min(cntTwo(n)-cntTwo(m)-cntTwo(n-m)
                , cntFive(n)-cntFive(m)-cntFive(n-m)));
//
//        System.out.println(cntTwo(n) + " " + cntFive(n));
//        System.out.println(cntTwo(m) + " " + cntFive(m));
//        System.out.println(cntTwo(n-m) + " " + cntFive(n-m));

    }
}
