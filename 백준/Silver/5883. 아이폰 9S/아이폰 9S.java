import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] bytes = new int[N];

        for (int i = 0; i < N; i++) {
            bytes[i] = Integer.parseInt(br.readLine());
        }

        int b, len, maxLen = 0, compare;
        for (int i = 0; i < N; i++) {

            b = bytes[i];
            len = 0;
            compare = bytes[0];
            
            for (int j = 1; j < N; j++) {

                if (bytes[j] == b) continue;

                if(compare == bytes[j]) len++;
                else{
                    maxLen = Math.max(maxLen, len);
                    len = 1;
                    compare = bytes[j];
                }

            }

            maxLen = Math.max(maxLen, len);

        }

        System.out.println(maxLen);
    }
}