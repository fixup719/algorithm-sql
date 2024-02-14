import java.io.*;
import java.security.Key;
import java.util.*;

public class Main {
    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();

        int[] arr = new int[str.length() + 1];
        int[] prefix = new int[str.length() + 1];
        for (int i = 1; i <= str.length() ; i++) {
            if (str.charAt(i - 1) == '(') {
                arr[i] = 1;
                prefix[i] = prefix[i - 1] + arr[i];
            } else if(str.charAt(i - 1) == ')'){
                arr[i] = -1;
                prefix[i] = prefix[i - 1] + arr[i];
            }
        }

        boolean[] suffix = new boolean[str.length() + 2];
        for (int i = str.length(); i >= 1 ; i--) {
            suffix[i] = suffix[i + 1];
            if(prefix[i] < 2) suffix[i] = true;
        }

        int answer = 0;
        if (prefix[str.length()] < 0) {
            // 음수인 경우 닫힌 괄호 -> 열린 괄호 (+2)
            for (int i = 1; i <= str.length(); i++) {
                if (arr[i] < 0) answer++;
                if (prefix[i] < 0) break;
            }
        } else if (prefix[str.length()] > 0) {
            // 양수인 경우 열린 괄호 -> 닫힌 괄호 (-2) -> 이게 어렵다 ㅠㅠ
            for (int i = 1; i <= str.length(); i++) {
                if (arr[i] > 0 && !suffix[i]) answer++;
            }
        }

        bw.write(String.valueOf(answer));
        bw.flush();
        bw.close();
        br.close();
    }
}