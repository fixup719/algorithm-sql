


import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] mushrooms = new int[10];
        for(int i=0; i<10; i++){
            mushrooms[i] = Integer.parseInt(br.readLine());
        }

        int sum=0;
        int ans=0;
        for(int i=0; i<10; i++){
            sum+=mushrooms[i];
            if(sum==100){
                ans = sum;
                break;
            }
            else if(sum>100){
                int a = 100-(sum-mushrooms[i]);
                int b = sum-100;
                if(a>=b){
                    ans = sum;
                }else{
                    ans = sum-mushrooms[i];
                }
                break;
            }
        }

        if(ans==0) System.out.println(sum);
        else System.out.println(ans);
    }
}