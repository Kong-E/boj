import java.io.*;
import java.util.*;

class Solution {
    static class Node {
        int x;
        int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int[][] board;
    static boolean[][] visited;
    static int c; // 가로 수
    static int r; // 세로 수
    static int chunk = 0;

    public static int solution(int[][] land) {
        int answer = 0;
        r = land.length;
        c = land[0].length;
        int[] columns = new int[c]; // 열마다 석유 크기
        board = land;
        visited = new boolean[r][c];

        for (int y = 0; y < r; y++) {
            for (int x = 0; x < c; x++) {
                if (!visited[y][x] & land[y][x] == 1) {
                    boolean[] visitedIdx = getChunk(new Node(x, y), new boolean[c]);
                    for (int i = 0; i < c; i++) {
                        if (visitedIdx[i]) {
                            columns[i] += chunk;
                        }
                    }
                    chunk = 0;
                }
            }
        }

        for (int col : columns) {
            if (answer < col) answer = col;
        }

        return answer;
    }

    static boolean[] getChunk(Node node, boolean[] visitedIdx) {
        Queue<Node> q = new LinkedList<>();
        q.offer(node);
        visited[node.y][node.x] = true;

        while (!q.isEmpty()) {
            Node curNode = q.poll();
            int x = curNode.x;
            int y = curNode.y;
            if (!visitedIdx[x]) visitedIdx[x] = true;
            chunk += 1;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= c || ny < 0 || ny >= r) continue;
                if (visited[ny][nx] || board[ny][nx] == 0) continue;
                q.offer(new Node(nx, ny));
                visited[ny][nx] = true;
            }
        }

        return visitedIdx;
    }
}