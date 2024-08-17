import java.io.*;
import java.util.*;

public class Main {
    static String S, T;

    static int makeStr(StringBuilder sb) {
        if (String.valueOf(sb).equals(S)) return 1;
        if (sb.length() <= 0) return 0;

        int ret = 0;

        String org = String.valueOf(sb);

        // 1번 연산 -> 만약 문자열 맨 뒤에 A가 있다면, 해당 글자 제거하기
        if (sb.charAt(sb.length() - 1) == 'A') ret += makeStr(sb.deleteCharAt(sb.length() - 1));

        // 2번 연산 -> 만약 문자열 맨 앞에 B가 있다면, 해당 글자 제거 후 뒤집기
        sb = new StringBuilder(org);
        if (sb.charAt(0) == 'B') ret += makeStr(sb.deleteCharAt(0).reverse());

        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = br.readLine();
        T = br.readLine();

        int ret = makeStr(new StringBuilder(T));

        if (ret > 0) System.out.println(1);
        else System.out.println(0);
    }
}