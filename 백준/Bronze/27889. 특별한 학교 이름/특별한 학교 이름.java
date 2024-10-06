import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String name = br.readLine();
        if (name.equals("NLCS")) {
            System.out.println("North London Collegiate School");
        } else if (name.equals("BHA")) {
            System.out.println("Branksome Hall Asia");
        } else if (name.equals("KIS")) {
            System.out.println("Korea International School");
        } else {
            System.out.println("St. Johnsbury Academy");
        }
    }
}

