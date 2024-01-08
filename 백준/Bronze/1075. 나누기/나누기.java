import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int F = Integer.parseInt(br.readLine());

        int h = N/100;
        for(int i=0; i<99; i++){
            if((h*100 + i/10*10 + i%10)%F == 0){
                if(i/10*10 == 0) System.out.print(0);
                System.out.println((i/10*10) + (i%10));
                break;
            }
        }
    }
}