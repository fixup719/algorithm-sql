import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int Q = Integer.parseInt(br.readLine());
        int ta, tb, na, nb, sum;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            ta = Integer.parseInt(st.nextToken());
            tb = Integer.parseInt(st.nextToken());
            na = Integer.parseInt(st.nextToken());
            nb = Integer.parseInt(st.nextToken());
            sum = 0;
            // 상혁이는 a만, 도훈이는 a,b둘다 수행 가능
            // b기준 완료하는 시간 동안 도훈이가 a처리
            sum += nb * tb;
            na -= sum / ta;

            if (na < 0) {
                sb.append(sum).append("\n");
            } else {
                if (na % 2 == 0) {
                    sb.append(sum + (na / 2) * ta).append("\n");
                } else {
                    sum += ta - sum % ta;
                    na--;
                    sb.append(sum + (na / 2) * ta).append("\n");
                }
            }
        }
        System.out.println(sb);
    }
}