public class 프로그래머스_불량사용자 {
    public int solution(String[] user_id, String[] banned_id) {
        int userLen = user_id.length;
        int banLen = banned_id.length;

        int[] comb = new int[userLen];
        for (int i = 0; i < banLen; i++) {
            comb[userLen - 1 - i] = 1;
        }
        String[] selected = new String[banLen];


        int ans = 0;

        outer:
        do {
            int index = 0;
            for (int i = 0; i < userLen; i++) {
                if (comb[i] != 0) {
                    selected[index++] = user_id[i];
                }
            } // 조합 끝
            int[] ref = new int[banLen];
            for (int i = 1; i < banLen; i++) {
                ref[i] = i;
            }

            inner:
            do {
                String[] caseRef = new String[banLen];
                for (int i = 0; i < banLen; i++) {
                    caseRef[i] = banned_id[ref[i]];
                }
                for (int i = 0; i < banLen; i++) {
                    if (!isPossible(selected[i], caseRef[i])) {
                        continue inner;
                    }
                }
                ++ans;
                continue outer;
            } while (nextPermutation(ref));
        } while (nextPermutation(comb));

        return ans;


    }

    static boolean nextPermutation(int[] arr) {
        int i = arr.length - 1;
        while (i > 0 && arr[i - 1] >= arr[i]) {
            --i;
        }
        if (i == 0) {
            return false;
        }

        int j = arr.length - 1;
        while (arr[i - 1] >= arr[j]) {
            --j;
        }
        swap(arr, i - 1, j);

        int k = arr.length - 1;
        while (k > i) {
            swap(arr, k--, i++);
        }
        return true;
    }

    static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    static boolean isPossible(String name, String ref) {
        int nameLen = name.length();
        int refLen = ref.length();
        if (nameLen != refLen) {
            return false;
        }
        for (int i = 0; i < nameLen; i++) {
            if (ref.charAt(i) == '*') {
                continue;
            }
            if (name.charAt(i) != ref.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
