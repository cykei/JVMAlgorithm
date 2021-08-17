package programmers;

import java.util.Arrays;
class 행렬테두리회전하기 {
    //오후 8시 51분~ 9시 44분
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] board = new int[rows][columns];
        int index=1;
        // rows, columns 바꿔써서 디버깅하는데 13분? 정도 버림. 시험때 이러면 죽고싶겠다 ㅂㄷㅂㄷ
        for (int i=0; i<rows; i++) {
            for (int j=0; j<columns; j++) {
                board[i][j] = index;
                index++;
            }
        }
        // 전체복사 : 시간복잡도 1억 ㅇㅋ
        for (int j=0; j<queries.length; j++){
            int[][] temp = new int[rows][columns];
            for (int t=0; t<rows; t++) {
                temp[t] = Arrays.copyOf(board[t],board[t].length);
            }

            int x1 = queries[j][0]-1;
            int y1 = queries[j][1]-1;
            int x2 = queries[j][2]-1;
            int y2 = queries[j][3]-1;
            int smallest = index;
            for (int i=x1+1; i<=x2; i++) {
                if(temp[i][y1] < smallest) smallest = temp[i][y1];
                if(temp[i-1][y2] < smallest) smallest = temp[i-1][y2];
                board[i-1][y1] = temp[i][y1]; // 아래값을 위로 옮김 14->8
                board[i][y2] = temp[i-1][y2]; // 위의 값을 아래로 옮김 10->16
            }
            for (int i=y1+1; i<=y2; i++) {
                if(temp[x2][i] < smallest) smallest = temp[x2][i];
                if(temp[x1][i-1] < smallest) smallest =temp[x1][i-1];
                board[x2][i-1] = temp[x2][i];
                board[x1][i] = temp[x1][i-1];
            }
            answer[j] = smallest;
        }
        return answer;
    }
}