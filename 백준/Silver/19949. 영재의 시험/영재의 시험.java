
import java.io.*;
import java.util.*;

public class Main {
    static int[] answer = new int[10];
    static int[] guess = new int[10];
    static int cnt = 0;

    static void perm(int depth){

        if(depth == 10){
            boolean dup = false;
            for(int i=2; i<10; i++){
                if(guess[i-2] == guess[i-1] && guess[i-1] == guess[i]) {
                    dup = true;
                    break;
                }
            }

            if(!dup) {
                int score = 0;
                for(int i=0; i<10; i++){
                    if(answer[i] == guess[i]) score++;
                }

                if(score >=5) cnt++;
            }
            return;
        }

        for(int i=1; i<=5; i++){
            guess[depth] = i;
            perm(depth+1);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0; i<10; i++){
            answer[i] = Integer.parseInt(st.nextToken());
        }

        perm(0);
        System.out.println(cnt);

    }
}