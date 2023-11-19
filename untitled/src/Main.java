import java.util.Scanner;
import java.util.*;
import java.io.FileInputStream;

class Solution
{
    public static void main(String args[]) throws Exception
    {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++)
        {
            int N = sc.nextInt();
            int[] arr = new int[N];
            int sum = sc.nextInt();
            int max1 = sc.nextInt();
            int max2 = sc.nextInt();
            int max3 = sc.nextInt();
            int max4 = sc.nextInt();
            for (int i = 0; i < N; i++) {
                sum = 0; // 합
                max1 = 0;
                max2 = 0;
                max3 = 0;
                max4 = 0;
                if(arr[i]>max1){
                    max1 = arr[i];
                    if(arr[i] < max1 && arr[i]>max2){
                        max2 = arr[i];
                        if(arr[i]<max1 && arr[i]<max2 && arr[i]>max3){
                            max3 = arr[i];
                            if(arr[i]<max1 && arr[i]<max2 && arr[i]<max3 && arr[i]>max4){
                                max4 = arr[i];
                                sum = max1 + max3;
                                if(max1 == max2){
                                    sum = max1 + max2;
                                    System.out.println("#" + test_case + " " + sum);
                                }
                                if(max3 == max4){
                                    sum = max1 + max3 + max4;
                                    System.out.println("#" + test_case + " " + sum);
                                }
                                System.out.println("#" + test_case + " " + sum);
                            }
                        }
                    }
                }
            }
            System.out.println("#" + test_case + " " + sum);
        }

        sc.close(); // 사용이 끝난 스캐너 객체를 닫습니다.
    }
}
