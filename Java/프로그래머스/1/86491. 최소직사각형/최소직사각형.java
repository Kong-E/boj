class Solution {
    public int solution(int[][] sizes) {
        int row = 0;
        int col = 0;
        
        int temp;
        for (int[] size: sizes) {
            if (size[0] < size[1]) {
                temp = size[0];
                size[0] = size[1];
                size[1] = temp;
            }
            row = Math.max(row, size[0]);
            col = Math.max(col, size[1]);
        }
        
        return row * col;
    }
}