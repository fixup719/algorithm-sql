

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int check(int[] arr, int K, int size){
        int answer = 0;

        f: for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                for(int k=0; k<size; k++){
                    int sum = arr[i] + arr[j] + arr[k];
                    if(sum == K){
                        answer = 1;
                        break f;
                    }
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        int[] arr;
        for(int t=0; t<tc; t++){
            int K = Integer.parseInt(br.readLine());

            int tValue = 0;
            int i=1;
            arr = new int[50];
            while(true){
                tValue = i*(i+1)/2;
                if(tValue >= K) break;
                arr[i-1] = tValue;
                i++;
            }
            sb.append(check(arr,K, i-1)+"\n");
        }

        System.out.println(sb);
    }
}