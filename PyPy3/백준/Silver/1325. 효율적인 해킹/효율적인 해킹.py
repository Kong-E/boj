import sys
from collections import deque, defaultdict

input = sys.stdin.readline

n, m = map(int, input().split())

graph = defaultdict(list)

for _ in range(m):
    a, b = map(int, input().split())
    graph[b].append(a)  # 반대로 저장해서 역방향 그래프를 만듦

def bfs(start):
    visited = [False] * (n + 1)
    queue = deque([start])
    visited[start] = True
    count = 0
    
    while queue:
        node = queue.popleft()
        for neighbor in graph[node]:
            if not visited[neighbor]:
                visited[neighbor] = True
                queue.append(neighbor)
                count += 1
    return count

result = []
max_hacks = -1

for i in range(1, n + 1):
    hacks = bfs(i)
    if hacks > max_hacks:
        max_hacks = hacks
        result = [i]
    elif hacks == max_hacks:
        result.append(i)

print(" ".join(map(str, result)))
