import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[] isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        ArrayList<Integer> lst = new ArrayList<>();
        for (int i = 2; i < N + 1; i++) {
            if (!isPrime[i]) continue;
            lst.add(i);
            for (int j = i * i; j <= N; j += i) {
                if (!isPrime[j]) continue;
                lst.add(j);
                isPrime[j] = false;
            }
        }

        System.out.println(lst.get(K - 1));
    }
}

