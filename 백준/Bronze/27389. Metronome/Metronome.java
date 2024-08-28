import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        double ans = Integer.parseInt(br.readLine()) / 4.0;
        System.out.printf("%.2f", ans);
    }
}

