import java.util.*;
class Solution {
    public int[] solution(String[] gems) {
        HashSet<String> set = new HashSet<>(Arrays.asList(gems));
        HashMap<String,Integer> map = new HashMap<>();
        int start = 0;
        int end  = 0;
        int len = Integer.MAX_VALUE;
        int[] ans = new int[2];
        for(int i = 0; i< gems.length; i++) {
            map.put(gems[i],map.getOrDefault(gems[i],0)+1);
            end++;

            while(map.getOrDefault(gems[start],0) > 1) {
                map.put(gems[start],map.get(gems[start])-1);
                start++;
            }
            if(set.size() == map.size()) {
                if(len > end - start) {
                    len = end - start;
                    ans[0] = start+1;
                    ans[1] = end;
                    map.remove(gems[start]);
                    start++;
                } else {
                    map.remove(gems[start]);
                    start++;
                }
            }

        }
        return ans;

    }

}