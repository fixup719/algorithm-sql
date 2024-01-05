
import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] cases = new int[N][3];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            cases[i][0] = Integer.parseInt(st.nextToken());
            cases[i][1] = Integer.parseInt(st.nextToken());
            cases[i][2] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;
        int first, second, third, num, tmpFirst, tmpSecond, tmpThird, strike, ball;
        for(int i=123; i<999; i++){
            first = i/100;
            second = i/10%10;
            third = i%10;

            if( second == 0 || third == 0 ) continue;

            if( first == second || second == third || first == third) continue;

            boolean check = true;
            for(int j=0; j<N; j++){
                num = cases[j][0];
                tmpFirst = num/100;
                tmpSecond = num/10%10;
                tmpThird = num%10;
                strike = 0;
                ball =0;

                if(first == tmpFirst) strike++;
                if(second == tmpSecond) strike++;
                if(third == tmpThird) strike++;

                if(first!=tmpFirst && (first == tmpSecond || first == tmpThird)) ball++;
                if(second!=tmpSecond && (second == tmpFirst || second == tmpThird)) ball++;
                if(third!=tmpThird && (third == tmpFirst || third == tmpSecond)) ball++;

                if(strike != cases[j][1] || ball != cases[j][2]){
                    check = false;
                    break;
                }
            }

            if(check) {
                cnt++;
            }
        }

        System.out.println(cnt);


    }
}
