

import java.io.*;
import java.util.*;

public class Main {
    static boolean[] primes;
    static int MAX = 1000000;

    static void makePrime(){
        for(int i=2; i<= Math.sqrt(MAX); i++){
            if(!primes[i]){
                for(int j=i*i; j<=MAX; j+=i){
                    primes[j] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        primes = new boolean[MAX+1];
        primes[0]=primes[1]=true;
        makePrime();

        while(N-->0){
            long num = Long.parseLong(br.readLine());

            boolean check = true;

            for(int i=2; i<=MAX; i++){
                if(!primes[i] && num%i == 0) {
                    check = false;
                    break;
                }
            }

            if(check) sb.append("YES\n");
            else sb.append("NO\n");

        }
        System.out.println(sb);
    }
}