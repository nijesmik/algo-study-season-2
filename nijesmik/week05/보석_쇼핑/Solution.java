package nijesmik.week05.보석_쇼핑;

import java.util.*;

class Solution {
    String[] types, gems;
    Map<String, List<Integer>> map;
    int combination[], start, length, typeSize;

    public int[] solution(String[] gems) {
        this.gems = gems;
        types = new String[100_000];
        typeSize = 0;
        map = new HashMap<>();
        for (int i = 0; i < gems.length; i++) {
            List<Integer> list = map.get(gems[i]);
            if (list == null) {
                list = new ArrayList<>();
                types[typeSize++] = gems[i];
            }
            list.add(i+1);
            map.put(gems[i], list);
        }
        combination = new int[typeSize];
        length = gems.length;
        dfs(0);
        return new int[]{start, start + length};
    }

    void dfs(int idx) {
        if (idx == typeSize) {
            int max = 0, min = gems.length;
            for (int num : combination) {
                max = Math.max(max, num);
                min = Math.min(min, num);
            }
            int length = max - min;
            if (this.length == length) {
                start = Math.min(start, min);
            } else if (this.length > length) {
                this.length = length;
                start = min;
            }
            return;
        }
        List<Integer> list = map.get(types[idx]);
        for (int num : list) {
            combination[idx] = num;
            dfs(idx+1);
        }
    }
}
