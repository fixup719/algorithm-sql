import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static boolean isPrime(long number){

        if (number == 1) return false;

        long cnt = 0;
        for (long i = 2; i * i < number + 1; i++) {
            if (number % i == 0) {
                cnt++;
            }
        }

        if ( cnt == 0 ) return true;
        else return false;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long N = Long.parseLong(br.readLine());

        long tmp = N;
        int value = 0, digit = 0; // 자릿값, 자릿수
        ArrayList<Integer> values = new ArrayList<>();
        while (tmp > 0) {
            value = (int) (tmp % 10);

            if (value == 3 || value == 4 || value == 7) {
                System.out.println("no");
                System.exit(0);
            }else if (value == 6) values.add(9);
            else if (value == 9) values.add(6);
            else values.add(value);

            tmp /= 10;
            digit++;
        }

        long reverseN = 0;
        for (int i = 0; i < values.size(); i++) {
            reverseN += values.get(i) * Math.pow(10, --digit);
        }

        if(isPrime(N) && isPrime(reverseN)) System.out.println("yes");
        else System.out.println("no");
    }
}
