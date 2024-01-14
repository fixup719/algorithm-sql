import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long func(long num){
        long sum = 0;

        // 먼저 홀수개만큼 더하기
        if (num % 2 == 0) {
            sum += num / 2;
        } else {
            sum += num / 2 + 1;
        }

        long i = 2;
        while ((num / i) != 0) {
            sum += i * ((num - i) / (i * 2) + 1);
            i *= 2;
        }

        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());


        // A부터 B까지 모든 합계를 구하면 안된다!!!!!
        // 홀수는 무조건 1이 나옴
        // 2 : 2 6 10 14 18 22 26 30 ... (4)
        // 4 : 4 12 20 28 36 ... (8)
        // 8 : 8 24 .. (16)
        // sum = 1*(n/2+1(홀수로 끝날때) or n/2(짝수로끝날 때)) + 2*((n-2)/4+1) + 4*((n-4)/8 + 1) + ...

        System.out.println(func(B) - func(A - 1));
    }
}