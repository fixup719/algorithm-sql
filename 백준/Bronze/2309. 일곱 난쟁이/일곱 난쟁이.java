

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] dwarfs = new int[9];
        int sum = 0;
        for(int i=0; i<9; i++){
            int h = Integer.parseInt(br.readLine());
            sum += h;
            dwarfs[i] = h;
        }

        Arrays.sort(dwarfs);
        int idx1=0, idx2=0;
        f: for(int i=0; i<9 ;i++){
            for(int j=i+1; j<9; j++){
                int tmp = sum;
                tmp = tmp - (dwarfs[i] + dwarfs[j]);
                if(tmp == 100){
                    idx1 = i;
                    idx2 = j;
                    break f;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<9; i++){
            if(i!=idx1 && i!=idx2){
                sb.append(dwarfs[i]+"\n");
            }
        }
        System.out.println(sb);



    }
}