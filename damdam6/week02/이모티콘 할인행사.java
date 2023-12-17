
class Solution {
    public int[] solution(int[][] users, int[] emoticons) {
        userSta = users;
        emoSta = emoticons;
        emoSale = new int[emoSta.length];

        
        finalMoney=  0;
        maxCnt = 0;
        
        dfs(0);
        int[] answer = new int[]{maxCnt, finalMoney};
        return answer;
    }
    static int[][] userSta;
    static int[] emoSta;
    static int[] per = new int[]{10,20,30,40};
    static int[] emoSale;
    
    static int finalMoney;
    static int maxCnt;
    
    public void chkBuy(){
        int buy = 0;
        int cnt = 0;
        for(int i=0;i<userSta.length;i++){
            int money = 0;
        //사용자마다
            for(int j=0;j<emoSale.length;j++){
              if(emoSale[j] < userSta[i][0])continue;
                money += emoSta[j]/100*(100-emoSale[j]);
            }
            
            if(money >= userSta[i][1]){
                cnt+=1;
                
            }else{
                buy += money;
            }
        }
        
        if(cnt > maxCnt){
            maxCnt = cnt;
            finalMoney = buy;
        }else if(cnt == maxCnt){
            finalMoney = Math.max(finalMoney, buy);
        }
    }
    public void dfs(int idx){
        
        if(idx == emoSta.length ){
            chkBuy();
            return;
        }
        
        for(int i=0;i<4;i++){
            emoSale[idx] = per[i];
            dfs(idx+1);
        }
        
    }
}