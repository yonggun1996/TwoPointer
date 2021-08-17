
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class TwoWater {

    /*
    절댓값이 0이랑 가까워야 하며 두 합의 값이 0보다 크면 back pointer를
    두 합의 값이 0보다 작으면 front pointer를 늘리고 줄인다
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> list = new ArrayList<>();

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            list.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(list);

        int front = 0;
        int back = n - 1;

        int min = Integer.MAX_VALUE;
        int[] point_arr = new int[2];
        while(front < back){
            int sum = list.get(front) + list.get(back);
            int sum_abs = Math.abs(sum);

            if(min > sum_abs){
                min = sum_abs;
                point_arr[0] = list.get(front);
                point_arr[1] = list.get(back);
            }

            if(sum < 0){
                front++;
            }else if(sum > 0){
                back--;
            }else{
                break;
            }

        }

        StringBuilder answer = new StringBuilder();
        answer.append(point_arr[0]);
        answer.append(" ");
        answer.append(point_arr[1]);
        System.out.println(answer);
    }

}
