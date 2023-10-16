

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    // 이렇게 매개변수 r,c,r2,c2로 구분하는 이유는 위아래 다 교환해야 하니까(?)
    // 아예 교환할 index들을 매개변수로 받으면 하나의 메소드로 처리 가능!
    static void swapCandy(char[][] map, int r, int c, int r2, int c2){
        char temp = map[r][c];
        map[r][c] = map[r2][c2];
        map[r2][c2] = temp;
    }

    static int findMaxRow(char[][] map){
        int maxRow = 0;
        int N = map.length;
        for(int r = 0; r < N; r++){
            int len = 1;
            for(int c = 0; c < N-1; c++){
                if(map[r][c] == map[r][c+1]) len++;
                else{
                    // 마지막 연속된 부분은 여기를 안 거칠 수 있으므로 밑에서 한번더 max값 갱신하기
                    maxRow = Math.max(maxRow, len);
                    len = 1;
                }
            }
            maxRow = Math.max(maxRow, len);
        }
        return maxRow;
    }

    static int findMaxCol(char[][] map){
        int maxCol = 0;
        int N = map.length;
        for(int c = 0; c < N; c++){
            int len = 1;
            for(int r = 0; r < N-1; r++){
                if(map[r][c] == map[r+1][c]) len++;
                else{
                    // 마지막 연속된 부분은 여기를 안 거칠 수 있으므로 밑에서 한번더 max값 갱신하기
                    maxCol = Math.max(maxCol, len);
                    len = 1;
                }
            }
            maxCol = Math.max(maxCol, len);
        }
        return maxCol;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        // 1. 배열 입력 받기! => toCharArray 사용
        char[][] map = new char[N][N];
        for(int i=0; i<N; i++){
            String str= br.readLine();
            map[i] = str.toCharArray();
        }

        int ans = 0;
        for(int r=0; r<N; r++){
            for(int c=0; c<N; c++){
                // 2. 사탕 교환하기 (중복 교환 방지를 위해 증가하는 방향으로만 교환)
                // 3. 연속된 개수 구하기
                // 4. 사탕 원복하기

                // 행 기준(열 교환) = 경계 체크, 색이 다른지 체크
                if(c+1<N && map[r][c] != map[r][c+1]){
                    swapCandy(map, r, c, r, c+1);
                    ans = Math.max(ans, Math.max(findMaxCol(map), findMaxRow(map)));
                    swapCandy(map, r, c, r, c+1);
                }

                // 열 기준(행 교환) = 경계 체크, 색이 다른지 체크
                if(r+1<N && map[r][c] != map[r+1][c]){
                    swapCandy(map, r, c, r+1, c);
                    ans = Math.max(ans, Math.max(findMaxCol(map), findMaxRow(map)));
                    swapCandy(map, r, c, r+1, c);
                }

            }
        }

        System.out.println(ans);
    }
}