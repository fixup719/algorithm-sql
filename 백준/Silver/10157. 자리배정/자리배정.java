import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int C, R, K;
    static int[][] hall;
    // 상, 우, 하, 좌
    static int[] delR = {-1,0,1,0};
    static int[] delC = {0,1,0,-1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        K = Integer.parseInt(br.readLine());

        hall = new int[R+1][C+1];

        int nth = 1, dir = 0, row = R, col = 1, nextR, nextC;
        while (nth <= R*C) {

            hall[row][col] = 1;

            if(nth == K){
                System.out.println(col + " " + (R - row + 1));
                break;
            }

            nextR = row + delR[dir];
            nextC = col + delC[dir];

            if (nextR <= 0 || R < nextR || nextC <= 0 || C < nextC || hall[nextR][nextC] > 0) {
                dir = (dir + 1) % 4;
                nextR = row + delR[dir];
                nextC = col + delC[dir];
            }

            row = nextR;
            col = nextC;

            nth++;
        }


        if(nth > R*C) System.out.println(0);

    }
}