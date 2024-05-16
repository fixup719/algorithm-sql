import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        int input;
        while (n-- > 0) {
            input = Integer.parseInt(br.readLine());

            sb.append(input + " " + input +"\n");
        }

        System.out.println(sb);
        br.close();
    }
}