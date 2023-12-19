
import java.io.*;
import java.util.*;

public class Main {
    static int N; // 목표 채널
    static int M; // 고장난 버튼 개수
    static boolean[] isBroken; // 버튼 고장 여부
    static int toMove; // 이동횟수


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        isBroken = new boolean[10];
        if(M>0){
            // 고장난 버튼이 있다면 버튼 고장 여부 배열에 체크
            st = new StringTokenizer(br.readLine());
            while(M-->0){
               isBroken[Integer.parseInt(st.nextToken())] = true;
            }
        }

        // 이동 횟수 초기값은 100번버튼에서 +또는 -를 눌렀을 때 이동한 횟수로 최화한다.
        toMove = Math.abs(100-N);

        for(int i=0; i<=999843; i++){
            String number = String.valueOf(i);

            for(int j=0; j<number.length(); j++) {
                int digit = number.charAt(j) - 48;

                if (isBroken[digit]) {
                    // 고장난 버튼이라면
                    break;
                }

                if (j == number.length() - 1) {

                    toMove = Math.min(toMove, number.length() + Math.abs(N - i));
                }
            }
        }

        System.out.println(toMove);

    }
}