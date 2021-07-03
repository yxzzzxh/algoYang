package probs.samsung01;

import java.util.*;

public class Review {
    /* input */
    static int N; //2N은 '길'의 전체 갯수 	(2 <= N <= 100)
    static int L; //경사로 '밑변'의 길이 	(1 <= L <=  N )
    static int[][] road; //

    /* output */
    static int ans; //최종적으로 지나갈 수 있는 길의 개수 출력

    static Stack<Integer> stack = new Stack();
    static Stack<Integer> sSlope = new Stack();
    static Queue<Integer> qSlope = new LinkedList(); //왜 Queue만 연결리스트를 생성자로 해야 하는 걸까?

    static boolean flag = false;
    static int sub_L = 0;

    static int checkRow(){
        int cnt = 0;
        int row = 0;

        while(row < N){
            //row가 바뀔 때마다 Clear
            stack.clear();
            sSlope.clear();
            qSlope.clear();
            sub_L = L;
            flag = false;

            int copy_sub_L = sub_L;

            checkEachRow(road[row], sub_L);

            for(int col = 1; col < N-1; col++) {
                // 바로 이전에 보도블럭 stack에 넣기
                // Stack 목적
                // 1) 보행 가능한 보도블럭인지 확인하기 위해
                // 2) 검사가 끝난 보도블럭에 추후 경사로 세울 수 있는지 확인하기 위해
                stack.push(road[row][col-1]);

                // case 1. 이전 보도블럭 a와 현재 보도블럭 b의 높이가 같다면, 보행가능하므로
                if(stack.peek().equals(road[row][col])){
                    // ㄴ 주의
                    // ㄴ Object 자체를 비교하는지, 값만 비교되는지 꼭 확인하기.
                    stack.push(road[row][col-1]); //stack에 쌓는다.

                    // case 2. a와 b의 높이가 같지 않다면, 그 자체로는 보행이 불가능하므로
                }else{
                    // 경사로 사용하여 길을 만들 수 있는지 알아봐야 한다.
                    // 따라서 우선 상황에 맞게 경사로를 지을 수 있는지 알아봐야 한다.
                    //////////////////////////////////////////////////////////////////
                    // case 2-1. (경사로 불가능)
                    // 이전 보도 블럭의 높이 a, 현재 보도 블럭의 높이 b라고 할 때,
                    // a, b 의 차가 1보다 큰 경우는 경사로 사용이 불가하므로,
                    if(stack.peek()-road[row][col] > 1 || stack.peek()-road[row][col] < -1){
                        // JYP 위조건문 == if (Math.abs(stack.peek()-road[row][col]) > 1)
                        break; // for 문 탈출하여 다음 줄로 넘어간다.
                    } else {
                        if (!flag) {
                            copy_sub_L = sub_L;
                        }
                        // JYP sub_L을 감소시킨걸 나중에도 써먹음 원본 훼손시키지말아야함

                        // JYP N - col >= sub_L 무의미

                        // N - col = (1)
                        // 3221 N = 4, L = 2,
                        // col = 1, (1) = 3 >= 2
                        // col = 2, (1) = 2 >= 2

                        // N-1 까지니까 안옴 col = 3, (1) = 1 >= 2
                        // 322111 N = 6, L = 2,
                        if(road[row][col]==road[row][col+1]){


                            flag = true; // 경사 표시 플래그 시작
                            copy_sub_L--;	 // sub_L 1 감소

                            continue; 	 //

                        }else{
                            break;
                        }

                        //////////////////////////////////////////////////////////////////
                        //////////////////////////////////////////////////////////////////
                    }

                    // 1. a > b 인 경우는,
                    // 오른쪽으로 하강하는 slope를 만들어야 함으로
                    // 이제 나올 블럭 중에서 L만큼의 경사를 만들 수 있는지 확인하기.
//                        if(stack.peek() > road[row][col]) {
//                            // L 이상의 사이즈 있으면
//                            // 꺼내면서 연속적으로 큐에 넣으면서 경사로 가능한지 보기
//                            qSlope.add(stack.pop());
//
//                            for(int i = 0; i < L; i++) {
//                                qSlope.add(stack.pop());
//                                // ex) 3 > 2 L이 3일 때, 3 뒤에 2가 연속으로 3번 나와야 한다.
//
//                            }
//
//                            //그대로 1차이 나는 Slope가 있는지, 그리고 그것이 L만큼 있는지.
//                            //만약 경사로가 만들어지기 전에 값이 바뀌면 break.
//                        }
                }
            }
            row++;
        }

        return cnt;

    }

    private static boolean checkEachRow(int[] eachRow, int argSub_L) {
        int copy_sub_L = argSub_L;

        for(int col = 1; col < N-1; col++) {
            // 바로 이전에 보도블럭 stack에 넣기
            // Stack 목적
            // 1) 보행 가능한 보도블럭인지 확인하기 위해
            // 2) 검사가 끝난 보도블럭에 추후 경사로 세울 수 있는지 확인하기 위해
            stack.push(eachRow[col-1]);

            // case 1. 이전 보도블럭 a와 현재 보도블럭 b의 높이가 같다면, 보행가능하므로
            if(stack.peek().equals(eachRow[col])){
                // ㄴ 주의
                // ㄴ Object 자체를 비교하는지, 값만 비교되는지 꼭 확인하기.
                stack.push(eachRow[col-1]); //stack에 쌓는다.

                // case 2. a와 b의 높이가 같지 않다면, 그 자체로는 보행이 불가능하므로
            }else{
                // 경사로 사용하여 길을 만들 수 있는지 알아봐야 한다.
                // 따라서 우선 상황에 맞게 경사로를 지을 수 있는지 알아봐야 한다.
                //////////////////////////////////////////////////////////////////
                // case 2-1. (경사로 불가능)
                // 이전 보도 블럭의 높이 a, 현재 보도 블럭의 높이 b라고 할 때,
                // a, b 의 차가 1보다 큰 경우는 경사로 사용이 불가하므로,
                if(stack.peek()-eachRow[col] > 1 || stack.peek()-eachRow[col] < -1){
                    // JYP 위조건문 == if (Math.abs(stack.peek()-eachRow[col]) > 1)
                    break; // for 문 탈출하여 다음 줄로 넘어간다.
                } else {
                    if (!flag) {
                        copy_sub_L = argSub_L;
                    }
                    // JYP sub_L을 감소시킨걸 나중에도 써먹음 원본 훼손시키지말아야함

                    // JYP N - col >= argSub_L 무의미

                    // N - col = (1)
                    // 3221 N = 4, L = 2,
                    // col = 1, (1) = 3 >= 2
                    // col = 2, (1) = 2 >= 2

                    // N-1 까지니까 안옴 col = 3, (1) = 1 >= 2
                    // 322111 N = 6, L = 2,
                    if(eachRow[col]==eachRow[col+1]){


                        flag = true; // 경사 표시 플래그 시작
                        copy_sub_L--;	 // argSub_L 1 감소

                        continue; 	 //

                    }else{
                        break;
                    }

                    //////////////////////////////////////////////////////////////////
                    //////////////////////////////////////////////////////////////////
                }

                // 1. a > b 인 경우는,
                // 오른쪽으로 하강하는 slope를 만들어야 함으로
                // 이제 나올 블럭 중에서 L만큼의 경사를 만들 수 있는지 확인하기.
//                        if(stack.peek() > eachRow[col]) {
//                            // L 이상의 사이즈 있으면
//                            // 꺼내면서 연속적으로 큐에 넣으면서 경사로 가능한지 보기
//                            qSlope.add(stack.pop());
//
//                            for(int i = 0; i < L; i++) {
//                                qSlope.add(stack.pop());
//                                // ex) 3 > 2 L이 3일 때, 3 뒤에 2가 연속으로 3번 나와야 한다.
//
//                            }
//
//                            //그대로 1차이 나는 Slope가 있는지, 그리고 그것이 L만큼 있는지.
//                            //만약 경사로가 만들어지기 전에 값이 바뀌면 break.
//                        }
            }
        }

        return false;
    }

    static int checkCol(){
        stack.clear();
        sSlope.clear();
        qSlope.clear();

        int cnt = 0;


        return cnt;
    }

    static int is(){

        return checkRow() + checkCol();
    }
    public static void main(String[] args) {
// TODO Auto-generated method stub
//
//        Scanner sc = new Scanner(System.in);
//        N = sc.nextInt();
//        L = sc.nextInt();
//
//        road = new int[N][N];
//
//        for(int i = 0; i < N; i++){
//            for(int j = 0; j < N; j++){
//                road[i][j] = sc.nextInt();
//            }
//
//
//        }
//
//        ans = is();


//        int[] eachRow = new int[] {
//                2, 3, 3, 3, 3, 3
//        };

        int[] eachRow = new int[] {
                2, 2, 3, 3, 3, 3
        };

        System.out.println(checkEachRow(eachRow, 2));


    }

}
