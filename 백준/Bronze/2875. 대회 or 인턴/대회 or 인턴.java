

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int ans = 0;
        for(int i=0; i<=N; i++){
            for(int j=0; j<=M; j++){
                if(i+j==K){
                    int girls = N-i;
                    int boys = M-j;
                    if(girls/2<boys) ans = Math.max(ans, girls/2);
                    else {
                        ans = Math.max(ans, boys);
                    }
                }
            }
        }

        System.out.println(ans);
    }
}