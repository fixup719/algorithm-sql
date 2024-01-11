import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long K = Long.parseLong(br.readLine());

        // 소인수분해
        ArrayList<Long> jewels = new ArrayList<>();
        long tmp = K;
        for(long i=2; i * i < K + 1; i++){
            while( tmp % i == 0) {
                jewels.add(i);
                tmp /= i;
            }
        }

        if(tmp != 1) jewels.add(tmp);

        System.out.println(jewels.size());
        for (Long x : jewels){
            System.out.print(x + " ");
        }

    }
}