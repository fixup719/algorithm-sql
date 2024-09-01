import java.io.*;
import java.util.*;

public class Main {
    static boolean[] isPrime = new boolean[10_000_000];
    static String input;
    static int len;
    static boolean[] visited = new boolean[20];
    static Set<Integer> set = new TreeSet<>();

    //  소수판정 배열 만들기
    static void findPrime() {
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i <= 9999999; i++) {
            if (!isPrime[i]) continue;


            for (long j = (long) i * i; j <= 9999999; j += i) {
                isPrime[(int) j] = false;
            }
        }
    }

    static void recur(int cur, int num) {
        if (cur == len) {
            if (isPrime[num]) {
                set.add(num);
            }
            return;
        }

        // 선택
        for (int i = 0; i < len; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            recur(cur + 1,
                    num * 10 + input.charAt(i) - '0');
            visited[i] = false;
        }

        // 선택 X
        recur(len, num);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 소수판정 배열 만들기
        findPrime();

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            input = br.readLine();
            len = input.length();

            recur(0, 0);
            sb.append(set.size()).append("\n");

            set.clear();
        }

        System.out.println(sb);
    }
}

