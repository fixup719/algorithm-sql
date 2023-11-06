

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        // 택희 t => 홀수X
        // 남규 g >= 영훈 y + 2
        // 남은 사탕 X
        // t,g,y > 0

        int cnt = 0;
        for(int t=2; t<N-1; t+=2){
            int remain = N-t;
            for(int y=1; y<N-1; y++){
                if(remain - y >= y + 2) cnt++;
            }
        }

        System.out.println(cnt);
    }
}