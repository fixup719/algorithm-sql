import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int maxGcd, a, b, tmp;
        while (N-- > 0) {
            String[] nums = br.readLine().split(" ");
            Arrays.sort(nums);

            maxGcd = 0; a = 0; b = 0; tmp = 0;
             for (int i = 0; i < nums.length; i++) {
                for (int j = i+1; j < nums.length; j++) {
                    a = Integer.parseInt(nums[j]);
                    b = Integer.parseInt(nums[i]);
                    while(b != 0){
                        tmp = a % b;
                        a = b;
                        b = tmp;
                    }

                    maxGcd = Math.max(maxGcd, a);
                }
            }

             sb.append(maxGcd+"\n");
        }

        System.out.println(sb);

    }
}