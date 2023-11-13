
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long K = Long.parseLong(br.readLine());
        long sqrtK = (long)Math.sqrt(K);

        long i=2;
        ArrayList<Long> list = new ArrayList<>();
        while(K>1){

            if(i>sqrtK+1){
                list.add(K);
                break;
            }

            if(K%i == 0){
                list.add(i);
                K /= i;
            }else{
                i++;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(list.size()+"\n");
        for(Long x : list){
            sb.append(x+" ");
        }
        System.out.println(sb);


    }
}