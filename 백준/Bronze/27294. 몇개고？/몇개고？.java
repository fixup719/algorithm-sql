

import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int hour = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());


        // 320
        //  320, 280
        // 280

        if(s==1)System.out.println(280);
        else if(12<=hour && hour<=16) System.out.println(320);
        else System.out.println(280);

    }
}
