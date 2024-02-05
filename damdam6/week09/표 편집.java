import java.util.*;

public class Solution {
    // 노드 클래스 정의: 테이블의 각 행을 나타냄
    static class Node {
        int index; // 행의 인덱스
        Node prev, next; // 이전 및 다음 노드를 가리키는 포인터

        Node(int index) {
            this.index = index; // 생성자에서 노드의 인덱스 초기화
        }
    }

    public String solution(int n, int k, String[] cmd) {
        Node[] nodes = new Node[n]; // 모든 행을 노드로 표현하기 위한 배열
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i); // 각 인덱스에 해당하는 노드 생성
            if (i > 0) {
                nodes[i].prev = nodes[i - 1]; // 이전 노드 설정
                nodes[i - 1].next = nodes[i]; // 다음 노드 설정
            }
        }

        Stack<Node> removedNodes = new Stack<>(); // 삭제된 노드를 저장하기 위한 스택
        Node current = nodes[k]; // 처음 선택된 행을 현재 노드로 설정

        for (String c : cmd) {
            char command = c.charAt(0); // 명령어의 첫 글자 추출
            if (command == 'U') {
                int x = Integer.parseInt(c.substring(2)); // 이동할 행의 수
                while (x-- > 0 && current.prev != null) current = current.prev; // 위로 이동
            } else if (command == 'D') {
                int x = Integer.parseInt(c.substring(2)); // 이동할 행의 수
                while (x-- > 0 && current.next != null) current = current.next; // 아래로 이동
            } else if (command == 'C') {
                removedNodes.push(current); // 현재 노드를 삭제 목록에 추가
                if (current.prev != null) current.prev.next = current.next; // 이전 노드의 다음을 현재의 다음으로 설정
                if (current.next != null) current.next.prev = current.prev; // 다음 노드의 이전을 현재의 이전으로 설정
                current = (current.next != null) ? current.next : current.prev; // 현재 노드를 다음 노드로 이동, 마지막 행이면 이전 노드로 이동
            } else if (command == 'Z') {
                Node node = removedNodes.pop(); // 가장 최근에 삭제된 노드를 복구
                if (node.prev != null) node.prev.next = node; // 이전 노드와 다시 연결
                if (node.next != null) node.next.prev = node; // 다음 노드와 다시 연결
            }
        }

        StringBuilder answer = new StringBuilder("O".repeat(n)); // 초기 상태: 모든 행이 존재하는 상태
        while (!removedNodes.isEmpty()) {
            Node node = removedNodes.pop(); // 삭제된 노드를 가져와
            answer.setCharAt(node.index, 'X'); // 해당 인덱스를 'X'로 표시
        }

        return answer.toString(); // 최종 결과 문자열 반환
    }
}