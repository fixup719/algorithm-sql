import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        boolean[] isPrime = new boolean[4000010];
        Arrays.fill(isPrime, true);
        isPrime[1] = false;
        for (int i = 2; i <= 4000000 ; i++) {
            if(!isPrime[i]) continue;

            if ((long)i * i <= 4000000) {
                for (int j = i * i; j <= 4000000; j += i) {
                    isPrime[j] = false;
                }
            }

        }

        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 1; i <= 4000000; i++) {
            if(isPrime[i]) primes.add(i);
        }

        int s = 0, e = 0, cnt = 0, sum = primes.get(0);
        while (s <= e) {

            if (sum < N) {
                e++;
                if(e == primes.size()) break;
                sum += primes.get(e);
            } else if (sum >= N) {
                if(sum == N) cnt++;
                sum -= primes.get(s);
                s++;
            }

        }

        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }
}