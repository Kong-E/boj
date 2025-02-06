import java.util.*;

class Solution {
    static class Position {
        int r, c, time;
        
        Position(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }
        
        @Override
        public String toString() {
            return time + "(" + r + "," + c + ")";
        }
    }
    
    public int solution(int[][] points, int[][] routes) {
        // 포인트 번호 -> 좌표 매핑
        Map<Integer, int[]> pointMap = new HashMap<>();
        for (int i = 0; i < points.length; i++) {
            pointMap.put(i + 1, points[i]);
        }
        
        // 시간별 위치에 있는 로봇 수를 기록
        Map<String, Integer> collisionMap = new HashMap<>();
        
        // 각 로봇의 경로를 시뮬레이션
        for (int robotId = 0; robotId < routes.length; robotId++) {
            List<Position> path = new ArrayList<>();
            
            // 각 경로 구간별로 이동 경로 계산
            for (int i = 0; i < routes[robotId].length - 1; i++) {
                int[] start = pointMap.get(routes[robotId][i]);
                int[] end = pointMap.get(routes[robotId][i + 1]);
                
                int startTime = path.isEmpty() ? 0 : path.get(path.size() - 1).time + 1;
                List<Position> segment = getPath(start[0], start[1], end[0], end[1], startTime);
                path.addAll(segment);
            }
            
            // 마지막 위치 추가
            if (!path.isEmpty()) {
                int[] lastPoint = pointMap.get(routes[robotId][routes[robotId].length - 1]);
                path.add(new Position(lastPoint[0], lastPoint[1], path.get(path.size() - 1).time + 1));
            }
            
            // 경로의 각 위치를 collision map에 기록
            for (Position pos : path) {
                String key = pos.toString();
                collisionMap.put(key, collisionMap.getOrDefault(key, 0) + 1);
            }
        }
        
        // 충돌 횟수 계산
        int answer = 0;
        for (int count : collisionMap.values()) {
            if (count > 1) answer++;
        }
        
        return answer;
    }
    
    // 두 점 사이의 경로를 반환하는 메서드
    private List<Position> getPath(int startR, int startC, int endR, int endC, int startTime) {
        List<Position> path = new ArrayList<>();
        int currentR = startR;
        int currentC = startC;
        int time = startTime;
        
        // r 좌표 이동
        while (currentR != endR) {
            path.add(new Position(currentR, currentC, time++));
            currentR += (endR > currentR) ? 1 : -1;
        }
        
        // c 좌표 이동
        while (currentC != endC) {
            path.add(new Position(currentR, currentC, time++));
            currentC += (endC > currentC) ? 1 : -1;
        }
        
        return path;
    }
}