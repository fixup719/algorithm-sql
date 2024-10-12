import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int s = 0, e = str.length() - 1;
        boolean flag = true;
        while (s <= e) {
            if (str.charAt(s) != str.charAt(e)) {
                flag = false;
                break;
            }

            s++;
            e--;
        }

        if (flag) System.out.println(1);
        else System.out.println(0);
    }
}

