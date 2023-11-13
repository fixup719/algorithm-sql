
import java.io.*;
import java.util.*;

public class Main {

    static boolean isPrime(long num){

        if(num == 1) return false;
        for(long k=2; k*k <= num; k++){
            if(num % k == 0){
                return false;
            }
        }
        return true;

    }

    static boolean check(long num){
        while(num>0){
            long tmp = num%10;

            if(tmp==3 || tmp==4 || tmp==7){
                return false;
            }

            num/=10;
        }
        return true;
    }

    static long onFlip(long num){
        long ans = 0;
        while(num>0){
            if(num%10 == 6) ans = ans*10 + 9;
            else if(num%10 == 9) ans = ans*10 + 6;
            else ans = ans*10 + num%10;
            num /= 10;
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Long num = Long.parseLong(br.readLine());

        if(!isPrime(num)){
            System.out.println("no");
        }else{
            if(!check(num)){
                System.out.println("no");
            }else {
                long reversedNum = onFlip(num);
                if(!isPrime(reversedNum)) System.out.println("no");
                else System.out.println("yes");
            }
        }
    }
}