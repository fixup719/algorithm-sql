import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 에라토스테네스 체 초기화
        boolean[] isPrime = new boolean[2000010];
        Arrays.fill(isPrime, true);

        isPrime[1] = false;
        for (int i = 2; i <= 2000000 ; i++) {
            if(!isPrime[i]) continue;

            if((long)i * i <= 2000000)
            {
                for (int j = i * i; j <= 2000000; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        // 따로 소수만 리스트에 담기
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= 2000000; i++) {
            if(isPrime[i]) primes.add(i);
        }

        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        long A = 0, B = 0, sum = 0;
        boolean check = false;
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            A = Long.parseLong(st.nextToken());
            B = Long.parseLong(st.nextToken());
            sum = A + B;

            if (sum % 2 == 0) {
                if(sum == 2) sb.append("NO\n"); // 2를 간과하고 있었따 ^^
                else sb.append("YES\n");
            }else{
                sum -= 2;
                if(sum <= 1){
                    sb.append("NO\n");
                }else{
                    // 홀수일 경우 소수인지 안닌지 판단해야 함
//                    for (long i = 2; i * i <= sum ; i++) {
//                        if(sum % i == 0){
//                            cnt++;
//                            break;
//                        }
//                    }

                    if(sum <= 2000000){
                        if(isPrime[(int)sum]) check = true;
                        else check = false;
                    }else{
                        check = true;
                        for (int i = 0; i < primes.size(); i++) {
                            if(sum % primes.get(i) == 0){
                                check = false;
                                break;
                            }
                        }
                    }

                    if(check) sb.append("YES\n");
                    else sb.append("NO\n");
                }
            }
        }

        System.out.println(sb);
    }
}