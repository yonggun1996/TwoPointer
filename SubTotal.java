package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SubTotal {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());//수열 길이
        int s = Integer.parseInt(st.nextToken());//부분합

        int[] num_arr = new int[n + 1];
        st = new StringTokenizer(br.readLine());

        //수열을 배열로 담는 과정
        int index = 1;
        while(st.hasMoreTokens()){
            num_arr[index] = Integer.parseInt(st.nextToken());
            index++;
        }

        int back = 1;
        int front  = 1;
        int answer = Integer.MAX_VALUE;

        int num = 0;
        while(front <= n || back <= n){
            if(front == back){//두 포인터가 같은 경우
                if(num_arr[front] >= s){//우선 s이상이면 answer를 1로 선언
                    answer = 1;
                }

                num = num_arr[front];
                front++;
            }else if(front > n){//front 포인터가 모두 확인했다면
                if(num >= s){
                    answer = Math.min(answer, front - back);
                }

                num -= num_arr[back];
                back++;
            } else if(num <= s){//누적값이 s보다 작거나 같다면
                if(num == s){
                    answer = Math.min(answer, front - back + 1);
                }

                num += num_arr[front];//값을 늘리고
                front++;//front 포인터도 늘린다
            }else{//누적값이 s보다 크다면
                answer = Math.min(answer, front - back);//다 작은차이를 비교한다
                num -= num_arr[back];//값을 줄이고
                back++;//back 포인터도 늘린다
            }
        }

        if(answer == Integer.MAX_VALUE) answer = 0;//수열에 s이상의 합을 못찾으면 0으로 초기화
        System.out.println(answer);
    }

}
