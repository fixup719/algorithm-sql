import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int E = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int year = 1;
        while(true){
            if((year % 15 == E || (E == 15 && year % 15 == 0) )
                    && (year % 28 == S || (S == 28 && year % 28 == 0))
                    && (year % 19 == M || (M == 19 && year % 19 == 0))){
                System.out.println(year);
                break;
            }

            year++;
        }
    }
}