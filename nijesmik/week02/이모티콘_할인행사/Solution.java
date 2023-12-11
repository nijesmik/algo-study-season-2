package nijesmik.week02.이모티콘_할인행사;

class Solution {
    int[][] users;
    int[] emoticons;
    int[][] total;
    int emoticonPlus, revenue;

    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;
        total = new int[users.length][emoticons.length];
        emoticonPlus = 0; revenue = 0;
        dfs(0);
        return new int[]{emoticonPlus, revenue};
    }

    void dfs(int idx) {
        if (idx == emoticons.length) {
            calculate();
            return;
        }
        int[] discount = {10, 20, 30 ,40};
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < users.length; j++) {
                total[j][idx] = 0;
                if (discount[i] >= users[j][0]) {
                    total[j][idx] = emoticons[idx] * (100 - discount[i]) / 100;
                }
            }
            dfs(idx+1);
        }
    }

    void calculate() {
        int ep = 0;
        int r = 0;
        for (int i = 0; i < users.length; i++) {
            int price = 0;
            for (int j = 0; j < emoticons.length; j++) {
                price += total[i][j];
            }
            if (price >= users[i][1]) {
                ep++;
            } else {
                r += price;
            }
        }
        if (ep > emoticonPlus) {
            emoticonPlus = ep;
            revenue = r;
        } else if (ep == emoticonPlus && r > revenue) {
            revenue = r;
        }
    }
}