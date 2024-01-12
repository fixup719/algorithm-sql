import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine()) ;

        // 제곱수 찾기

        long number = 1;
        long tmp = number*number;
        long cnt = 0;
        while(tmp <= n){
            cnt++;
            tmp = (++number)*(number);
        }

        System.out.println(cnt);

    }
}