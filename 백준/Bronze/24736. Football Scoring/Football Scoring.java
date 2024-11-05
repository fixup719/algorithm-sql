import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[] score = {6, 3, 2, 1, 2};
        int[] a = new int[5];
        int[] b = new int[5];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 5; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 5; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        int s1 = 0, s2 = 0;
        for (int i = 0; i < 5; i++) {
            s1 += a[i] * score[i];
            s2 += b[i] * score[i];
        }

        System.out.println(s1 + " " + s2);
    }
}