class Solution {
    public int solution(int sticker[]) {
        int answer = Integer.MIN_VALUE;
        int[][][] record = new int[sticker.length][2][2];
        record[0][0][0] = 0;
        record[0][1][1] = sticker[0];
        
        record[0][0][1] = -1;
        record[0][1][0] = -1;
        
        if(sticker.length == 1)return record[0][1][1];
        
        
        for(int i=1;i<sticker.length;i++){

            //안 뜯는 경우는 앞의 두 케이스 다 상관 없음
            record[i][0][0] = Math.max(record[i-1][0][0], record[i-1][1][0]);
            // 뜯는 경우는 앞에서 안뜯은 경우에만 가능함
            record[i][1][0] = record[i-1][0][0] + sticker[i];
            
            //첫번째 껄 안 뜯은 경우도 해줌
            record[i][0][1] = Math.max(record[i-1][0][1], record[i-1][1][1]);
            record[i][1][1] = record[i-1][0][1] + sticker[i];
         
        }
        
        //마지막 스티커를 안뜯었으면 첫번째랑 상관 없음
        answer = Math.max(answer, record[sticker.length-1][0][0]);
        answer = Math.max(answer, record[sticker.length-1][0][1]);
        
        //뜯었으면 첫번째를 안뜯은 것만 가능
        answer = Math.max(answer, record[sticker.length-1][1][0]);
        
        return answer;
    }
    
    
}