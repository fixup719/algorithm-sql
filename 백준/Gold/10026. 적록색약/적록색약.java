
import java.io.*;
import java.util.*;

public class Main {
    static int N; // 그리드 크기
    static String[][] grid;
    static int a=0, b=0;
    static boolean[][] visited1;
    static boolean[][] visited2;
    static int[] delR = {-1,1,0,0};
    static int[] delC = {0,0,-1,1};

    static void dfs(int row, int col, boolean check){

        if(check) visited1[row][col] = true;
        else visited2[row][col] = true;

        for(int del=0; del<4; del++){
            int mRow = row + delR[del];
            int mCol = col + delC[del];

            if(mRow<0 || mCol<0 || N<=mRow || N<=mCol) continue;

            if(check && !visited1[mRow][mCol]){
                if(grid[row][col].equals(grid[mRow][mCol])){
                    dfs(mRow, mCol, check);
                }
            }
            if(!check && !visited2[mRow][mCol]){
                if(grid[row][col].equals("R") || grid[row][col].equals("G")){
                    if(grid[mRow][mCol].equals("R") || grid[mRow][mCol].equals("G")){
                        dfs(mRow, mCol, check);
                    }
                }else if(grid[row][col].equals(grid[mRow][mCol])){
                    dfs(mRow, mCol, check);
                }
            }
        }

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        grid = new String[N][N];
        for(int i=0; i<N; i++){
            String[] input = br.readLine().split("");
            for(int j=0; j<N; j++){
                grid[i][j] = input[j];
            }
        }

        // 적록색 약 O : R==G, B
        // 적록색 약 X : R, G, B
        visited1 = new boolean[N][N];
        visited2 = new boolean[N][N];
        for(int row=0; row<N; row++){
            for(int col=0; col<N; col++){
                if(!visited1[row][col]) {
                    dfs(row, col, true);
                    a++;
                }
                if(!visited2[row][col]) {
                    dfs(row, col, false);
                    b++;
                }
            }
        }

        System.out.println(a + " " + b);
    }
}