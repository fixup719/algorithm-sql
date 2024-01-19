import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int G = Integer.parseInt(br.readLine());

        long x = 2, y = 1;
        while (true) {

            if(x == y) break;

            if(x*x - y*y < G) x++;
            else if(x*x - y*y > G){ y++;}
            else{
                sb.append(x+"\n");
                x++;
            }

        }

        if(sb.isEmpty()) sb.append(-1);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}