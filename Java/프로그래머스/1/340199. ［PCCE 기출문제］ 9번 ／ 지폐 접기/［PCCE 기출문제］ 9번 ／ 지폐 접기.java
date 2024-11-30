class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        int minWallet = Math.min(wallet[0], wallet[1]);
        int maxWallet = Math.max(wallet[0], wallet[1]);
        
        while ((Math.min(bill[0], bill[1]) > minWallet) 
               || (Math.max(bill[0], bill[1]) > maxWallet)) {
            if (bill[0] > bill[1]) {
                bill[0] /= 2;
            } else {
                bill[1] /= 2;
            }
            answer++;
         }
                                 
        return answer;
    }
}

