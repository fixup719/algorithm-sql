import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int R = Integer.parseInt(br.readLine());
        int S = Integer.parseInt(br.readLine());

        int ans = R * 8 + S * 3 - 28;
        
        if (ans < 0) System.out.println("0");
        else System.out.println(ans);

    }
}
