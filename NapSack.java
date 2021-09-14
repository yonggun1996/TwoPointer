package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
배열을 반으로 나눠 조합을 구한다
조합을 구할 땐 c의 값을 넘지 않아야 한다
a리스트를 토대로 b리스트에 합할 수 있는 최대 인덱스를 찾아 누적한다

ex)
5 20
2 3 7 8 9

a_list = {0,2,3,5}
b_list = {0,7,8,9,15,16,17}

a리스트를 선형으로 돌면서
b리스트를 정렬 후 20을 넘지 않는 최대 원소 찾기
0 -> 7 / 2 -> 7 / 3 -> 7 / 5 -> 5 총 26
 */

public class NapSack {

    static int[] arr;//주어진 숫자들을 담는 배열
    static ArrayList<Integer> a_list = new ArrayList<>();//절반으로 나눈 조합의 리스트1
    static ArrayList<Integer> b_list = new ArrayList<>();//절반으로 나눈 조합의 리스트2

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        make_alist(0, n / 2, 0, c);//arr절반의 조합들을 구하는 메소드1
        make_blist(n / 2, n, 0, c);//arr절반의 조합들을 구하는 메소드2

        Collections.sort(b_list);//이분탐색을 위한 배열
        int answer = 0;
        for(int i = 0; i < a_list.size(); i++){
            int now = a_list.get(i);
            answer += binary_search(now, c);
        }

        System.out.println(answer);
    }
    public static void make_alist(int now, int limit, int sum, int c){//왼쪽 원소들의 경우의 수를 구하는 메서드
        if(sum > c){//더해온 값이 주어진 값을 넘어서면 더이상 확인하지 않는다
            return;
        }

        if(now == limit){//배열의 반을 다 확인하면 리스트에 삽입
            a_list.add(sum);
        }else{
            make_alist(now + 1, limit, sum + arr[now], c);//해당 인덱스에 값을 누적 후 확인
            make_alist(now + 1, limit, sum, c);//해당 인덱스의 값을 누적하지 않고 확인
        }
    }

    public static void make_blist(int now, int limit, int sum, int c){//오른쪽 원소들의 경우의 수를 구하는 메서드
        if(sum > c){//더해온 값이 주어진 값을 넘어서면 더이상 확인하지 않는다
            return;
        }

        if(now == limit){//배열의 반을 다 확인하면 리스트에 삽입
            b_list.add(sum);
        }else{
            make_blist(now + 1, limit, sum + arr[now], c);//해당 인덱스에 값을 누적 후 확인
            make_blist(now + 1, limit, sum, c);//해당 인덱스의 값을 누적하지 않고 확인
        }
    }

    //a리스트의 값 중 더했을 때 c를 넘지 않는 최대  b의 원소를 구하는 메서드
    public static int binary_search(int num, int c){
        int start = 0;
        int end = b_list.size() - 1;

        while(start <= end){
            int mid = (start + end) / 2;

            if(b_list.get(mid) + num <= c){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }

        return end + 1;
    }
}
