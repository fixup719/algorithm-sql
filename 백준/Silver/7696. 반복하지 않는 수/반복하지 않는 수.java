
import java.io.*;
import java.util.Arrays;

public class Main {
    static int[] ableNum = new int[1000001];
    static boolean[] visited = new boolean[10];

    static void makeCase() {

        int temp, remain;
        boolean check;
        for(int num=1, idx=1; idx<=1000000; num++){

            Arrays.fill(visited, false);
            temp=num;
            check = true;

            while(temp>0){
                remain = temp % 10;
                temp /= 10;

                if(visited[remain]) {
                    check = false;
                    break;
                }

                visited[remain] = true;
            }

            if(check) ableNum[idx++] = num;

        }

    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N;
        boolean[] check;
        makeCase();

        while(true){
            N = Integer.parseInt(br.readLine());

            if(N==0) break;

            System.out.println(ableNum[N]);

        }


    }
}
