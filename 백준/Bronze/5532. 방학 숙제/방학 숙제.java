import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int L = Integer.parseInt(br.readLine());
        int A = Integer.parseInt(br.readLine());
        int B = Integer.parseInt(br.readLine());
        int C = Integer.parseInt(br.readLine());
        int D = Integer.parseInt(br.readLine());

        int aDay = A / C;
        if (A % C > 0) aDay++;
        int bDay = B / D;
        if (B % D > 0) bDay++;

        System.out.println(L - Math.max(aDay, bDay));
    }
}

