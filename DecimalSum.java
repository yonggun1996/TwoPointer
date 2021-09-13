package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DecimalSum {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        boolean[] deciaml = new boolean[4000002];
        deciaml[0] = true;
        deciaml[1] = true;

        //에라토스테네스의 체로 n까지의 소수를 구하는 과정
        for(int i = 2; i <= Math.sqrt(n); i++){
            for(int j = 2; j * i <= n; j++){
                if(!deciaml[j * i]){
                    deciaml[j * i] = true;
                }
            }
        }

        int back = 2;//뒤쪽 포인터
        int front = 2;//앞쪽 포인터
        int sum = 0;//소수들의 합계
        int answer = 0;//n이 나온 수

        while(back <= n || front <= n){//두 포인터가 주어진 n까지 살펴봐야 한다
            if(back == front){//두 포인터가 같으면
                //하나의 포인터 값이 sum값이 된다
                sum = back;

                if(sum == n){
                    answer++;
                }

                while(front <= n){//다음 소수까지 찾을 때 까지 front를 늘린다
                    front++;

                    if(!deciaml[front]){
                        break;
                    }
                }
            }else if(sum >= n || front >= n){//합계가 n보다 크거나 front 포인터가 주어진 n을 넘어서면
                //back 값을 뺀다
                sum -= back;
                if(sum == n){
                    answer++;
                }

                //back 포인터 조정
                while(back <= n){
                    back++;

                    if(!deciaml[back]){
                        break;
                    }
                }
            }else{//합계가 n보다 작은 경우
                //front 값을 더한 후
                sum += front;
                if(sum == n){
                    answer++;
                }

                //front 포인터를 조정한다
                while(front <= n){
                    front++;

                    if(!deciaml[front]){
                        break;
                    }
                }
            }
        }

        System.out.println(answer);
    }

}
