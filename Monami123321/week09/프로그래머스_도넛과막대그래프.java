class 프로그래머스_도넛과막대그래프 {
    static int[] parent;
    static int[][] degree;
    static boolean[] isCycled;


    public int[] solution(int[][] edges) {
        int len = 0; // 정점 갯수
        for(int[] edge : edges) {
            for(int e : edge) {
                len = e > len ? e: len;
            }
        }

        degree = new int[2][len+1]; // in out

        for(int[] edge : edges) {
            degree[1][edge[0]]++; // 나가는거
            degree[0][edge[1]]++; // 들어오는거
        }
        int newOne = 0;

        for(int i =1; i<len+1; i++) {
            if(degree[0][i] == 0 && degree[1][i] >=2) {
                newOne = i;
                break;
                // 새로생긴애 찾음
            }
        }

        parent = new int[len+1];
        isCycled = new boolean[len+1];
        for(int i =1; i<len+1; i++) {
            parent[i] = i;
        }
        for(int[] edge : edges) {
            if(edge[0] == newOne) {
                continue;
            }
            union(edge[0],edge[1]);
        }
        boolean[] visited = new boolean[len+1];

        int donut = 0;
        int eight = 0;
        int stick = 0;
        for(int i = 1; i<len+1; i++) {
            int now = findSet(i);
            if(visited[now] || i == newOne) {
                continue;
            }
            visited[now] = true;
            if(isCycled[now]) {
                if(degree[1][now] == 2) {
                    eight++;
                } else {
                    donut++;
                }
            } else {
                stick++;
            }

        }
        return new int[] {newOne, donut, stick, eight};

    }

    static int findSet(int x) {
        if(parent[x] != x) {
            return parent[x] = findSet(parent[x]);
        }
        return x;
    }

    static void union(int a, int b) {
        int parentA = findSet(a);
        int parentB = findSet(b);
        if(parentA == parentB) {
            isCycled[parentA] = true;
            return;
        }
        if(degree[1][parentA] == 2) {
            parent[parentB] = parentA;
            return;
        }

        if(degree[1][parentB] == 2) {
            parent[parentA] = parentB;
            return;
        }

        if(parentA > parentB) {
            parent[parentA] = parentB;
        } else {
            parent[parentB] = parentA;
        }

    }
}