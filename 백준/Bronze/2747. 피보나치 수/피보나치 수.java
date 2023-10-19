

import java.io.*;
import java.util.*;


public class Main {
    static int[] memo;

    static int fibo(int n){
        if(n==0 || n==1) return n;

        if(memo[n] == 0) memo[n] = fibo(n-1)+fibo(n-2);

        return memo[n];

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        memo = new int[N+1];

        System.out.println(fibo(N));
    }
}