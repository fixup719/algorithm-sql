import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int J = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        if(J>H){
            int tmp = J;
            J = H;
            H = tmp;
        }

        int round = 0, a = J - 1, b = H - J - 1;
        while(N-- > 1){

            if (H - J == 1 && J % 2 != 0 && H % 2 == 0) {
                round++;
                break;
            }

            if (J % 2 == 0) {
                if (0 < a) {
                    a--;
                }
            } else {
                if (0 < b) {
                    b--;
                }
            }

            if (H % 2 == 0) {
                if (0 < b) {
                    b--;
                }
            }

            if (a > 0) {
                a /= 2;
            }

            if (b > 0) {
                b /= 2;
            }

            J = a + 1;
            H = J + b + 1;

            round++;

        }

        if(round ==0 ) System.out.println(-1);
        else System.out.println(round);
        
    }
}