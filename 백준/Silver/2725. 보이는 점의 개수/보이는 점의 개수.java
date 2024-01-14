import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 미리 서로 좌표 개수 담기
        int[] memo = new int[1001];
        int cnt;
        for (int i = 2; i <= 1000; i++) {
            cnt = 0;
            for (int j = 1; j <= i ; j++) {
                int tmp = 0, a = i, b = j;
                while(b != 0){
                    tmp = a % b;
                    a = b;
                    b = tmp;
                }
                if(a == 1) cnt++;
            }
            memo[i] = memo[i-1]+cnt*2;
        }

        int C = Integer.parseInt(br.readLine());
        int n, sum;
        while (C-- > 0) {
            n = Integer.parseInt(br.readLine());
            sum = 0;
            if (n == 0) {
                sb.append(0+"\n");
            } else if (n == 1) {
                sb.append(3 + "\n");
            } else {
                sb.append(memo[n]+3 + "\n");
            }
        }
        System.out.println(sb);

    }
}