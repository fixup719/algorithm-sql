import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        boolean[] isPrime = new boolean[4000010];
        Arrays.fill(isPrime, true);
        isPrime[1] = false;
        for (int i = 2; i <= 4000000; i++) {
            if(!isPrime[i]) continue;

            if ((long)i * i <= 4000000) {
                for (int j = i * i; j <= 4000000; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        int cnt = 0, tmp;
        boolean check;
        for (int i = A; i <= B ; i++) {
            if (isPrime[i]) {
                tmp = i;
                check = false;
                while (tmp > 0) {
                    if(tmp % 10 == D){
                        check = true;
                        break;
                    }
                    tmp /= 10;
                }
                if(check) cnt++;
            }
        }

        // 자릿값을 계산하는게 아니라 ㅠㅠㅠㅠ
        // 저 숫자를 포함하는 소수 개수를 구하는거얌,,, 문제 잘읽자...ㅠㅠㅠ

        System.out.println(cnt);
    }
}