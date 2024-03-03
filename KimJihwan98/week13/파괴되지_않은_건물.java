class 파괴되지_않은_건물 {
    static int N, M;
    public int solution(int[][] board, int[][] skill) {
        N = board.length;
        M = board[0].length;
        int answer = 0;
        int[][] sum_board = new int[N+1][M+1];
        for(int[] sk : skill) {
            int type = sk[0]==1 ? -1 : 1;
            int r1 = sk[1];
            int c1 = sk[2];
            int r2 = sk[3];
            int c2 = sk[4];
            int degree = type*sk[5];
            
            sum_board[r1][c1] += degree;
            sum_board[r1][c2+1] -= degree;
            sum_board[r2+1][c1] -= degree;
            sum_board[r2+1][c2+1] += degree;
        }
        
        for(int i = 0; i < N+1; i++) {
            for(int j = 1; j< M+1; j++) {
                sum_board[i][j] += sum_board[i][j-1];
            }
        }
        for(int j = 0; j < M+1; j++) {
            for(int i = 1; i< N+1; i++) {
                sum_board[i][j] += sum_board[i-1][j];
            }
        }
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j< M; j++) {
                if(board[i][j]+sum_board[i][j]>0) answer++;
                board[i][j] +=sum_board[i][j];
            }
        }
        // for(int i = 0; i < N; i++) {
        //     System.out.println(Arrays.toString(board[i]));
        // }
        return answer;
    }
}