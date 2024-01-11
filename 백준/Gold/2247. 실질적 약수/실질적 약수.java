import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    /*
        2부터 자기자신을 제외한 배수를 만날 때마다 1개씩 증가.
        3.. 4.. 5..도 마찬가지

        50까지 2 자기자신을 제외한 2의 배수는? 24개
            => 합계 1*2 + 2*2 + 3*2 + .... 23*2 + 24*1
        50까지 3 자기자신을 제외한 3의 배수는? 15개
            => 합계 1*3 + 2*3 + .... + 15*3
         50까지 25자기자신을 제외한 25의 배수는? 1개
            => 1*25

         N/2까지 각각 숫자가 몇개 등장하는지 체크
        ....
        * */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()) ;

        long sum = 0;
        for (int i = 2; i <= n/2; i++) {
            sum += (n/i-1)*i;
        }

        System.out.println(sum % 1000000);
    }
}