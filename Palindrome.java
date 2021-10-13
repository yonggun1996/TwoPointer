package String;
import java.io.*;
import java.util.*;

public class Palindrome {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());

        for(int i = 0; i < testcase; i++){
            String s = br.readLine();
            System.out.println(check_palindrom(s));
        }
    }

    public static int check_palindrom(String s){
        int leftidx = 0;//왼쪽인덱스
        int rightidx = s.length() - 1;//오른쪽인덱스

        //바로 팰린드롬인지 확인하는 과정
        while(leftidx < rightidx){
            if(s.charAt(leftidx) != s.charAt(rightidx)){
                break;
            }

            leftidx++;
            rightidx--;
        }

        if(leftidx >= rightidx){//만약 break하지 않은 상황이면 글자 자체가 펠린드롬이기 때문에 0 반환
            return 0;
        }

        //각 포인터와 문자를 제거했는지 데이터를 큐에 담는다
        Queue<PallindromeData> queue = new LinkedList<>();
        queue.offer(new PallindromeData(0, s.length() - 1, 0));

        while(!queue.isEmpty()){
            PallindromeData pd = queue.poll();
            int left = pd.left;//왼쪽포인터
            int right = pd.right;//오른쪽포인터
            int delete = pd.deletecount;//문자삭제횟수

            if(left >= right){//회문임을 확인한 경우
                return 1;
            }

            if(s.charAt(left) == s.charAt(right)){//각 포인터 문자가 같으면 둘 다 한 칸씩 옮김
                queue.offer(new PallindromeData(left + 1, right - 1, delete));
            }else if(delete == 0){
                //그렇지 않으면 right, left를 각각 한칸씩 옮기고 삭제 횟수도 늘린다.
                queue.offer(new PallindromeData(left, right - 1, delete + 1));
                queue.offer(new PallindromeData(left + 1, right, delete + 1));
            }
        }

        return 2;//큐를 다 돌았는데 그 사이 반환하지 않았다면 회문이 아니다.
    }
}

class PallindromeData{
    int left;
    int right;
    int deletecount;

    public PallindromeData(int left, int right, int deletecount){
        this.left = left;
        this.right = right;
        this.deletecount = deletecount;
    }
}
