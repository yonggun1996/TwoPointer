package Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No2003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //투 포인터 알고리즘을 토대로 해당 범위의 값을 구하면서 m과 맞는 값이 몇 개가 나오는지 확인하는 코드
        int i = 0; int j = 0;
        int result = arr[0];//초기값은 맨 처음 입력받은 값
        int count = 0;
        while(j < n && i < n){//i와 j를 다 살펴본다
            if(result == m) count++;//m에 해당하는 값이 나오면 count를 증가한다

            if(j < n - 1 && (i == j || result <= m)) {//j가 다다르기 직전이고 result보다 작거나 같고 와 j가 같으면 j를 늘린 후 값을 더한다
                j++;
                result += arr[j];
            }else{//그 외의 경우는 값을 빼고 i를 늘린다
                result -= arr[i];
                i++;
            }
        }

        System.out.println(count);
    }
}
