

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int f = Integer.parseInt(st.nextToken());

        f: for(int x=-999; x<=999; x++){
            for(int y=-999; y<=999; y++){
                if((b*d-a*e)*y == (c*d-a*f)){
                    if(a!=0) x = (c-b*y)/a;
                    else if(d!=0) x= (f-e*y)/d;
                    System.out.println(x + " " + y);
                    break f;
                }
            }
        }
    }
}