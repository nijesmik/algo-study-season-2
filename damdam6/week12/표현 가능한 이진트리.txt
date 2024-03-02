import java.util.*;

class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        int idx = 0;
        for(long num: numbers){
          //  System.out.println(num);
            String binaryString = Long.toBinaryString(num);
            //0 추가해서 사용
            binaryString = getFullBinary(binaryString);
           // System.out.println(binaryString);
            
            // 0을 빼가면서 테스트하기
            if(isBinaryTree(binaryString)){
                answer[idx++] = 1;
            }else{
                answer[idx++] = 0;
            }
            
        }
        
        return answer;
    }
    
    private String getFullBinary(String binary){
        int length = binary.length();
        int nodeCnt = 1;
        int level = 1;
        // 몇 층까지 있는지 확인
        while(length > nodeCnt){
            level *= 2;
            nodeCnt += level;
        }
        int offset = nodeCnt - length;
        return "0".repeat(offset) + binary;
    }
    
    private boolean isBinaryTree(String binary){
        int len = binary.length();
        //  리프 노드일 경우
        if(binary.length() == 0) return true;
        
        int root = len/2;
        String leftSubTree = binary.substring(0,root);
        String rightSubTree = binary.substring(root+1);
        
        // 루트 노드가 0이면 서브트리도 모두 0이어야 됨
        if(binary.charAt(root) == '0'){
            return isZeroTree(binary.substring(0,root))&& isZeroTree(binary.substring(root+1));
        }
        
        return isBinaryTree(leftSubTree) && isBinaryTree(rightSubTree);
        
    }
    
    // 자식이 전부다 0인가 확인
    private boolean isZeroTree(String binary){
        int len = binary.length();
        if(binary.length() == 0) return true;
        
        int root = len / 2;
        String leftSubTree = binary.substring(0, root);
        String rightSubTree = binary.substring(root + 1);

        if (binary.charAt(root) == '1') {
            return false;
        }

        return isZeroTree(leftSubTree) && isZeroTree(rightSubTree);
    }
}