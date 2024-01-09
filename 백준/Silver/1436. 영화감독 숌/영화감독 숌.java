import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] ableNum = new int[N+1];

        String numStr;
        boolean check;
        for (int i = 1, num = 1; i <= N; i++) {

            check = false;
            while(!check){
                numStr = String.valueOf(num);

                for (int j = 0; j < numStr.length(); j++) {
                    if(numStr.charAt(j) == '6'){
                        if(j+2 < numStr.length()
                                && numStr.charAt(j+1) == '6'
                                && numStr.charAt(j+2) == '6'){
                            check = true;
                            ableNum[i] = num;
                            break;
                        }
                    }
                }

                num++;
            }
        }

        System.out.println(ableNum[N]);

    }
}