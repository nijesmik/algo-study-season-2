package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Shark {
	int r = 0;
	int c = 0;
	int s = 0;
	int d = 0;
	int z = 0;
	Shark() {};
	Shark(int r, int c, int s, int d, int z) {
		this.r = r;
		this.c = c;
		this.s = s;
		this.d = d;
		this.z = z;
	}
}

public class 낚시왕 {
	static int R, C, M;
	static Shark[][] map;
	static int answer = 0;
	static PriorityQueue<Shark> pq;
	public static void main(String[] args) throws IOException {
		//입력 받기 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken()); 
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new Shark[R][C];
		pq = new PriorityQueue<>((s1, s2) -> s1.r-s2.r);
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()); 
			int z = Integer.parseInt(st.nextToken());
			
			map[r-1][c-1] = new Shark(r-1, c-1, s, d-1, z);
			pq.add(map[r-1][c-1]);
		}
		
		bfs();
		System.out.println(answer);
	}
	
	static int[] dr = {-1, 1, 0, 0}; // 위 아래 오른쪽 왼쪽
	static int[] dc = {0, 0, 1, -1};
	static void bfs() {
		PriorityQueue<Shark> pq2 = new PriorityQueue<>(new Comparator<Shark>() {
			@Override
			public int compare(Shark s1, Shark s2) {
				if(s1.r == s2.r) {
					if(s1.c == s1.c) {
						return s2.z - s1.z;
					}
					return s1.c - s2.c;
				}
				return s1.r - s2.r;
			}
		}); //이거는 map을 초기화 할 때 사이즈가 큰 것만 초기화할라고.
		
		int human = 0;
		boolean check = false;
		while(human != R && M!=0) {
			human++;
			for(int i = 0; i < M; i++) {
				Shark now = pq.poll();
				if(human == now.c && !check) {
					check = true;					//상어 한마리 잡았나 확인
					answer += now.z;
					map[now.r][now.c] = new Shark(); 
				} else {
					Shark tmp = moveShark(now);
					map[tmp.r][tmp.c] = new Shark();
					pq2.add(tmp);
				}
			}
			M = M-1; // 상어 한마리 잡았으니까 상어 숫자 줄이기
			
			// 상어 움직임 토대로 map 바꾸기
			while(!pq2.isEmpty()) {
				Shark now = pq2.poll();
				if(map[now.r][now.c].z != 0) { // 그 자리에 상어가 있으면
					M--; //상어 총 마리 수 줄이기
					continue;
				} else {
					map[now.r][now.c]= now;
					pq.add(now);
				}
			}
		}
	}
	static Shark moveShark(Shark shark) {
		int dir = shark.d;
		int speed = shark.s;
		if(dir == 0 || dir == 1) {
			while(speed != 0) {
				if(shark.c == 0 && dir == 0) dir += 1;
				if(shark.c == C-1 && dir == 1) dir -= 1;
				shark.c += dc[dir];
				speed--;
			}
		} else if(dir == 2 || dir == 3) {
			while(speed != 0) {
				if(shark.r == 0 && dir == 3) dir -= 1;
				if(shark.r == R-1 && dir == 2) dir += 1;
				shark.r += dr[dir];
				speed--;
			}
		}
		shark.d = dir;
		return shark;
	}
}
