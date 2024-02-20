class 표현_가능한_이진트리 {
    static int len; // 트리 노드 수
    static boolean chk; // 트리 구성 가능 여부

    static void chkRoot(String tree, int root, int layer) {
        if (root < 0 || root >= len || layer <= 0) { // 범위를 벗어나거나 리프노드이면 종료
            return;
        }

        // 부모 노드가 없는데 자식 노드가 존재하는 경우
        if (tree.charAt(root) == '0' && (tree.charAt(root - layer) == '1' || tree.charAt(root + layer) == '1')) {
            chk = false;
            return;
        }

        chkRoot(tree, root - layer, layer / 2); // 왼쪽 자식 노드 탐색
        chkRoot(tree, root + layer, layer / 2); // 오른쪽 자식 노드 탐색
    }

    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        Outer: for (int idx = 0; idx < numbers.length; idx++) {
            String tree = Long.toBinaryString(numbers[idx]); // 이진수 변환
            chk = true;

            int nums = 0; // 포화 이진 트리 노드 개수
            int layer = 0; // 트리 층 수

            // 포화 이진 트리 만들기
            while (true) {
                nums += Math.pow(2, layer);

                if (tree.length() < nums) {
                    int tmp = tree.length();
                    for (int i = 0; i < nums - tmp; i++) {
                        tree = "0" + tree;
                    }
                    break;
                } else if (tree.length() == nums) {
                    break;
                }

                layer++;
            }

            len = tree.length();
            layer = (int) Math.pow(2, layer - 1);

            chkRoot(tree, tree.length() / 2, layer); // 탐색 시작

            if (chk) answer[idx] = 1; // 이진 트리 표현 가능
            else answer[idx] = 0; // 이진 트리 표현 불가능
        }

        return answer;
    }
}