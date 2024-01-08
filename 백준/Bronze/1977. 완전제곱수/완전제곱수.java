import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());

        int min = -1;
        int sum = 0;
        for(int i=1; i<=N; i++){
            if(i*i < M) continue;
            if(N < i*i) break;

            if(min == -1) min = i*i;

            sum += i*i;
        }

        if(min == -1) System.out.println(-1);
        else{
            System.out.println(sum);
            System.out.println(min);
        }

    }
}