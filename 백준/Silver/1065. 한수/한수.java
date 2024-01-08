import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        boolean check;
        int diff, nextDigit, remain, curDigit, cnt=0;
        for (int i = 1; i <= N; i++) {
            check = true;
            curDigit = i%10;
            remain = i/10;
            nextDigit = remain%10;
            diff = nextDigit-curDigit;

            while(remain > 0 && remain/10 > 0){
                curDigit = remain%10;
                remain /= 10;
                nextDigit = remain%10;

                if(diff != nextDigit - curDigit){
                    check = false;
                    break;
                }
            }

            if(check) cnt++;
        }

        System.out.println(cnt);
    }
}