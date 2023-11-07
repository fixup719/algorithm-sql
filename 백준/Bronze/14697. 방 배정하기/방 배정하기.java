


import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        boolean check=false;
        f: for(int a=0; a<=N/A; a++){
            for(int b=0; b<=N/B; b++){
                for(int c=0; c<=N/C; c++){
                    if(a*A+b*B+c*C == N){
                        check = true;
                        break f;
                    }
                }
            }
        }

        if(check) System.out.println(1);
        else System.out.println(0);
    }
}