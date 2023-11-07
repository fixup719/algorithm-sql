

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int cnt=0;
        // (A-B)*(A+B) = N

        for(int a=1; a<=500; a++){
            for(int b=1; b<=a; b++){
                if((a-b)*(a+b)==N){
                    cnt++;
                }
            }
        }

        System.out.println(cnt);

    }
}