import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String X = st.nextToken();
        String Y = st.nextToken();

        String RX = "", RY = "";
        for (int i = X.length() - 1; i >= 0; i--) {
            RX += String.valueOf(X.charAt(i));
        }
        for (int i = Y.length() - 1; i >= 0; i--) {
            RY += String.valueOf(Y.charAt(i));
        }

        String sum = String.valueOf(Integer.parseInt(RX) + Integer.parseInt(RY));
        String RV = "";
        for (int i = sum.length() - 1; i >= 0; i--) {
            RV += String.valueOf(sum.charAt(i));
        }
        System.out.println(Integer.parseInt(RV));
    }
}

