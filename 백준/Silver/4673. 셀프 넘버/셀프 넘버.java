import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        boolean[] notSelfNum = new boolean[10040];

        int remain, temp, sum;
        for (int i = 1; i < 10000; i++) {

            temp = i;
            sum = 0;
            while(temp > 0){
                remain = temp%10;
                temp /= 10;
                sum += remain;
            }

            notSelfNum[i+sum] = true;
        }

        for (int i = 1; i < 10000; i++) {
            if(!notSelfNum[i]) System.out.println(i);
        }
    }
}