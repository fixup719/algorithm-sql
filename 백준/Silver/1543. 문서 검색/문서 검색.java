import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String docs = br.readLine();
        String word = br.readLine();

        int answer = 0, cnt = 0;
        for (int start = 0; start < docs.length(); start++) {
            cnt = 0;
            for (int i = start; i < docs.length();) {
                for (int j = 0; j < word.length(); j++) {

                    if (docs.length() <= i + j || docs.charAt(i + j) != word.charAt(j)) {
                        i += (j + 1);
                        break;
                    }

                    if (j == word.length() - 1) {
                        i += (j + 1);
                        cnt++;
                    }

                }
            }

            answer = Math.max(cnt, answer);
        }

        System.out.println(answer);

    }
}