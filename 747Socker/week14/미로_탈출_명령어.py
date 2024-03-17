from collections import deque

def solution(n, m, x, y, r, c, k):
    dx = (-1, 0, 0, 1) 
    dy = (0, 1, -1, 0)
    ds = ('u', 'r', 'l', 'd')    
    answer = ""
    
    total = abs(x-r)+abs(y-c)
    
    if total > k or (k - total) % 2 == 1:
        return "impossible"
    
    q = deque([(x, y, "", 0)])

    while q:
        x, y, string, move = q.pop()
        
        if move == k and x == r and y == c :
            return string
        
        for i in range(4):
            nx, ny = x+dx[i], y+dy[i]
            
            if 0 < nx <= n and 0 < ny <= m and k >= move + abs(r-x) + abs(c-y) :
                q.append((nx, ny, string+ds[i], move+1))

    return answer