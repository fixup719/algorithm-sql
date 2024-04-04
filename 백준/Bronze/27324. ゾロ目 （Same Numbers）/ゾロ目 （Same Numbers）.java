import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        if (N / 10 == N % 10) bw.write(String.valueOf(1));
        else bw.write(String.valueOf(0));

        bw.flush();
        bw.close();
        br.close();
    }
}