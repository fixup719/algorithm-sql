
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        sb.append(":fan::fan::fan:\n");
        String input = br.readLine();
        sb.append(":fan::"+input+"::fan:\n");
        sb.append(":fan::fan::fan:");

        System.out.println(sb);

    }
}