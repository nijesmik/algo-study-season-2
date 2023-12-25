package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Node {
	int parent;
	int num;
	int left;
	int right;
	public Node(int parent, int num, int left, int right) {
		this.parent = parent;
		this.num = num;
		this.left = left;
		this.right = right;
	}
}

public class 트리의_높이와_너비 {
	static int N, maxLevel, location;
	static List<Node> tree;
	static int[] levelMax, levelMin;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		tree = new ArrayList<>();
		levelMax = new int[N+1];
		levelMin = new int[N+1];
		for(int i = 0; i <= N; i++) {
			tree.add(new Node(-1, i, -1, -1));
			levelMax[i] = 0;
			levelMin[i] = N;
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			
			tree.get(n).left = l;
			tree.get(n).right = r;
			
			if (l != -1) tree.get(l).parent = n;
			if (r != -1) tree.get(r).parent = n;
		}
		
		int root = 0;
		for(int i = 1; i <= N; i++) {
			if(tree.get(i).parent == -1) {
				root = i;
				break;
			}
		}
		maxLevel = 0;
		location = 1;
		inorder(root, 1);
		
		int answer = 0;
		int answerIdx = 0;
		for(int i = 1; i <= maxLevel; i++) {
			int tmp = levelMax[i] - levelMin[i] + 1;
			if(answer < tmp) {
				answer = tmp;
				answerIdx = i;
			}
		}
		System.out.println(answerIdx + " " + answer);
	}
	
	//중위순회
	static void inorder(int root, int level) {
		Node curr = tree.get(root);
		
		maxLevel = Math.max(maxLevel, level);
		
		if(curr.left != -1) {
			inorder(curr.left, level+1);
		}
		
		levelMin[level] = Math.min(levelMin[level], location);
		levelMax[level] = location++;
		
		if(curr.right != -1) {
			inorder(curr.right, level+1);
		}
	}
}
