


import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        boolean[][] paper = new boolean[101][101];
        int cnt = 0;
        while(N-->0){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            for(int i=r; i<r+10; i++){
                for(int j=c; j<c+10; j++){
                    if(!paper[i][j]){
                        paper[i][j] = true;
                        cnt++;
                    }
                }
            }
        }

        System.out.println(cnt);
    }
}