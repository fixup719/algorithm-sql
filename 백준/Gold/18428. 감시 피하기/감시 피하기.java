import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static int N; // 복도 크기
    static String[][] room; // 복도 정보
    static int[][] install; // 설치한 장애물 좌표
    static ArrayList<int[]> teachers = new ArrayList<>();
    static ArrayList<int[]> blanks = new ArrayList<>();
    static int[] delR = {-1,1,0,0};
    static int[] delC = {0,0,-1,1};
    static boolean isAble = false;


    static void installObject(int cnt, int start){

        if(cnt == 3){

            if(canAvoid()) isAble = true;

            return;
        }

        for(int i=start; i<blanks.size(); i++){
            install[cnt][0] = blanks.get(i)[0];
            install[cnt][1] = blanks.get(i)[1];
            room[install[cnt][0]][install[cnt][1]] = "O";
            installObject(cnt+1, i+1);
            room[install[cnt][0]][install[cnt][1]] = "X";
        }
    }

    static boolean canAvoid(){

        for(int i=0; i<teachers.size(); i++){
            int mrow, mcol, row, col;
            for (int dir = 0; dir < 4; dir++) {

                row = teachers.get(i)[0];
                col = teachers.get(i)[1];

                // 0 3
                while (true) {
                    mrow = row + delR[dir];
                    mcol = col + delC[dir];

                    if (mrow < 0 || mcol < 0 || N <= mrow || N <= mcol
                        || room[mrow][mcol].equals("O")) break;

                    if(room[mrow][mcol].equals("S")) return false;

                    row = mrow;
                    col = mcol;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        room = new String[N][N];
        String[] input;
        for (int i = 0; i < N; i++) {
            input = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                room[i][j] = input[j];

                if(input[j].equals("T")) teachers.add(new int[] {i, j});
                else if(input[j].equals("X")) blanks.add(new int[] {i, j});
            }
        }

        install = new int[3][2];

        installObject(0, 0);

        if(isAble) System.out.println("YES");
        else System.out.println("NO");

    }
}