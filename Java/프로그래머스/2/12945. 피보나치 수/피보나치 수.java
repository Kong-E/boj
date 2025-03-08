class Solution {
    int[] memo = new int[100_001];
    
    public int solution(int n) {
        return fibo(n) % 1_234_567;
    }
    
    public int fibo(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (memo[n] == 0) return memo[n] = solution(n-1) + solution(n-2);
        return memo[n];
    }
}