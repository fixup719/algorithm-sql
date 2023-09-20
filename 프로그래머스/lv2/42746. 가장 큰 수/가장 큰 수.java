import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        
        String[] strArr = new String[numbers.length];
        
        for(int i=0; i<numbers.length; i++){
            strArr[i] = numbers[i]+"";
        }
        
        Arrays.sort(strArr, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return (o2 + o1).compareTo(o1 + o2);
            }
        });
            
      
        for (int i=0; i<strArr.length; i++)
            answer += strArr[i];

        return answer.charAt(0) == '0' ? "0" : answer;
    }
}