import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        String str = br.readLine();
        int[] ch = new int[26];

        int ans = 0;
        int s = 0, e = 0, cnt = 1;
        ch[str.charAt(0)-'a']++;
        while (s <= e) {

            if (cnt <= N) {
                ans = Math.max(ans, e - s + 1);
                e++;

                if( e == str.length()) break;

                if(ch[str.charAt(e)-'a'] == 0){
                    cnt++;
                }
                ch[str.charAt(e)-'a']++;
            } else {
                if (ch[str.charAt(s)-'a'] <= 1) {
                    cnt--;
                }
                ch[str.charAt(s)-'a']--;
                s++;
            }


        }

        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }
}