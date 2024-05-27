import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        if (input.equals("SONGDO")) System.out.println("HIGHSCHOOL");
        else if (input.equals("CODE")) System.out.println("MASTER");
        else if (input.equals("2023")) System.out.println("0611");
        else System.out.println("CONTEST");


        br.close();
    }
}