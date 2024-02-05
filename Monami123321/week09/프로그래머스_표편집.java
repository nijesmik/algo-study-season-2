import java.util.*;
class 프로그래머스_표편집 {
    static class Llist {
        Node head,tail;

    }
    static class Node {
        int id;
        boolean flag;
        Node prev,next;

        Node(int id) {
            this.id = id;
        }
    }
    public String solution(int n, int k, String[] cmd) {
        Node[] nodes = new Node[n];
        for(int i = 0; i<n; i++) {
            nodes[i]=  new Node(i);
        }
        for(int i = 0; i< n-1; i++) {
            nodes[i].next = nodes[i+1];
            nodes[n-1-i].prev = nodes[n-2-i];
        }
        Llist list = new Llist();

        list.head = new Node(-1);
        nodes[0].prev = list.head;
        list.head.next = nodes[0];

        list.tail = new Node(-1);
        nodes[n-1].next = list.tail;
        list.tail.prev = nodes[n-1];

        Stack<Node> stack = new Stack<>();
        Node now = nodes[k];

        for(String c : cmd) {
            char command = c.charAt(0);
            if(command == 'C') {
                stack.push(now);
                now.flag = true;
                now.prev.next = now.next;
                now.next.prev = now.prev;
                if(now.next == list.tail) {
                    now = now.prev;
                } else {
                    now = now.next;
                }

            } else if (command == 'Z') {
                Node restored = stack.pop();
                restored.flag = false;
                restored.prev.next = restored;
                restored.next.prev = restored;
            } else if (command == 'U') {
                int x = Integer.parseInt(c.substring(2));
                while(x-- > 0) {
                    now = now.prev;
                }

            } else {
                int x = Integer.parseInt(c.substring(2));
                while(x-- > 0) {
                    now = now.next;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            if(nodes[i].flag) {
                sb.append("X");
            } else {
                sb.append("O");
            }
        }
        return sb.toString();
    }
}