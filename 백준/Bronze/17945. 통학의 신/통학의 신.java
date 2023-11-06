


import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        double x1 = -A + Math.sqrt(A*A-B);
        double x2 = -A - Math.sqrt(A*A-B);

        if(x1 == x2) System.out.println((int)x1);
        else if(x1<x2) System.out.println((int)x1 + " " + (int)x2);
        else System.out.println((int)x2 + " " + (int)x1);
    }
}