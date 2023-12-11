
class Solution {
    static void permutation(int[] res, int[][] users, int depth) {

        if (depth == res.length) {
            int caseNum = 0;
            int caseVal = 0;

            for (int i = 0; i < users.length; i++) {
                int tmpVal = 0;
                int dcRate = users[i][0];
                if (dcRate > 30) {
                    dcRate = 3;
                } else if (dcRate > 20) {
                    dcRate = 2;
                } else if (dcRate > 10) {
                    dcRate = 1;
                } else {
                    dcRate = 0;
                }


                for (int j = 0; j < res.length; j++) {
                    if (res[j] >= dcRate) {
                        tmpVal += val[res[j]][j];
                    }
                }
                if (tmpVal >= users[i][1]) {
                    caseNum++;
                } else {
                    caseVal += tmpVal;
                }

            }
            if (caseNum > ans[0]) {
                ans[0] = caseNum;
                ans[1] = caseVal;
            } else if (caseNum == ans[0]) {
                if (caseVal > ans[1]) {
                    ans[1] = caseVal;
                }
            }

            return;


        }


        for (int i = 0; i < 4; i++) {
            res[depth] = i;
            permutation(res, users, depth + 1);
        }


    }


    static int[][] val; // 행 - 할인율 * 열 - 이모티콘가격 => 할인가격
    static int[] ans; // {가입자수, 판매액}

    public int[] solution(int[][] users, int[] emoticons) {
        int[] dcRate = {9, 8, 7, 6};

        val = new int[4][emoticons.length];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < emoticons.length; j++) {
                val[i][j] = dcRate[i] * emoticons[j] / 10;
            }
        }
        ans = new int[2];

        permutation(new int[emoticons.length], users, 0);
        return ans;
    }
}
