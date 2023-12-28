class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] ans = new int[balls.length];

        for(int i = 0; i< balls.length; i++) {
            int targetX = balls[i][0];
            int targetY = balls[i][1];

            int minDist = Integer.MAX_VALUE;

            if(startY==targetY) {
                if(startX > targetX) {
                    // 1. 위쪽으로
                    minDist = Math.min((int)Math.pow(startX - targetX,2) + (int)Math.pow((n-startY)*2,2),minDist);
                    // 2. 아래로
                    minDist = Math.min((int)Math.pow(startX - targetX,2) + (int)Math.pow(startY*2,2),minDist);
                    // 3. 오른쪽
                    minDist = Math.min((int)Math.pow(m - startX + m - targetX,2), minDist);

                } else {
                    minDist = Math.min((int)Math.pow(targetX - startX,2) + (int)Math.pow((n-startY)*2,2),minDist);
                    minDist = Math.min((int)Math.pow(targetX - startX,2) + (int)Math.pow(startY*2,2),minDist);
                    minDist = Math.min((int)Math.pow(startX + targetX,2), minDist);
                }
            } else if(startX==targetX) {
                if(startY > targetY) {
                    // 1. 위
                    minDist = Math.min((int)Math.pow(n-startY+n-targetY,2),minDist);
                    // 2. 왼
                    minDist = Math.min((int)Math.pow(startY-targetY,2) + (int)Math.pow(startX*2,2) ,minDist);
                    //3. 오
                    minDist = Math.min((int)Math.pow(startY-targetY,2) + (int)Math.pow((m-startX) * 2, 2), minDist);

                } else {
                    // 1. 아래
                    minDist = Math.min((int)Math.pow(startY+targetY,2),minDist);
                    // 2. 왼
                    minDist = Math.min((int)Math.pow(targetY-startY,2) + (int)Math.pow(startX*2,2) ,minDist);
                    //3. 오
                    minDist = Math.min((int)Math.pow(targetY-startY,2) + (int)Math.pow((m-startX) * 2, 2), minDist);
                }
            } else {
                // 1. 위
                minDist = Math.min( (int)Math.pow(Math.abs(startX-targetX),2)+(int)Math.pow(n-startY+n-targetY,2) ,minDist);
                // 2. 아래
                minDist = Math.min( (int)Math.pow(Math.abs(startX-targetX),2)+(int)Math.pow(startY+targetY,2) ,minDist);
                // 3. 왼
                minDist = Math.min( (int)Math.pow(Math.abs(startY-targetY),2)+(int)Math.pow(startX+targetX,2) ,minDist);
                // 4. 오
                minDist = Math.min( (int)Math.pow(Math.abs(startY-targetY),2)+(int)Math.pow(m-startX+m-targetX,2) ,minDist);
            }

            ans[i] = minDist;

        }

        return ans;
    }
}