import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NumberSum {

    /*
    투 포인터 문제
    두 수를 가지고 해당 값이 나올 수 있는 경우의 수는?
    정렬 -> 앞 부분과 뒷 부분을 포인터로 두고 목표값보다 크면 뒷 포인터를 줄이고 목표값보다 작으면 앞 포인터를 늘리는 방식
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int num = Integer.parseInt(br.readLine());

        int front = 0;//앞 포인터
        int end = n - 1;//뒷 포인터
        int answer = 0;//num이 나오는 갯수

        while(front < end){
            if(arr[front] + arr[end] > num){
                end--;
            }else if(arr[front] + arr[end] < num){
                front++;
            }else{
                answer++;
                end--;
                front++;
            }
        }

        System.out.println(answer);
    }

}
