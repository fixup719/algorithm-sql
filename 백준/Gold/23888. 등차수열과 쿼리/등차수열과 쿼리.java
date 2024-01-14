import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long func1(int a1, int d, int l, int r){
        int n = r - l + 1;
        long al = a1 + (l - 1) * d;
        return n * (2 * al + (n - 1) * d) / 2;
    }

    static long func2(int a1, int d, int l, int r){
        long answer = 0;

        if(l == r || d == 0){
            answer = a1 + (l - 1) * d;
        } else if (a1 % d == 0) {
            answer = d;
        }else {
            // (첫항 - 공차)와 공차의 최대 공약수 구해야함
            int tmp = 0, a = Math.abs(a1 - d), b = d;
            while (b != 0) {
                tmp = a % b;
                a = b;
                b = tmp;
            }

            answer = a;
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int a1 = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        int q = Integer.parseInt(br.readLine());
        int type, l, r;
        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            type = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());

            if(type==1){
                sb.append(func1(a1, d, l, r) + "\n");
            }else{
                // 무조건 공차의 약수중 왼쪽에 있는 수
                // 만약 20이면 (1, 2, 4)는 해당 수열의 모든 원소의 공약수가 됨
                // 즉 왼쪽에 올 수 있는 수중 최댓값이 모든 수의 최대공약수가 되는것 히히
                sb.append(func2(a1, d, l, r) + "\n");
            }
        }

        System.out.println(sb);

    }
}
