import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int L = Integer.parseInt(br.readLine());

        boolean[] isSetNum = new boolean[12000];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<L; i++){
            isSetNum[Integer.parseInt(st.nextToken())] = true;
        }

        int n = Integer.parseInt(br.readLine());

        int A=0, B=0;

        int tmp = n;
        while (tmp > 0) {
            if(isSetNum[tmp]) break;
            A++;
            tmp--;
        }

        tmp = n;
        while (tmp < 12000){
            if(isSetNum[tmp]) break;
            B++;
            tmp++;
        }

        if(A==0) System.out.println(0);
        else System.out.println(A*B-1);

    }
}