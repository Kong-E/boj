function solution(players, callings) {
    const answer = [...players];
    const indexMap = {};
    
    players.forEach((item, index) => {
        indexMap[item] = index;
    })
    
    // calling 되면
    // 1. 불린 사람 등수 - 1 해줘야 하고
    // 2. 원래 있던 사람 등수 + 1 해줘야 함
    
    callings.forEach(item => {
        const origin = indexMap[item];
    
        const temp = answer[origin - 1];
        answer[origin] = temp;
        answer[origin - 1] = item;
        
        indexMap[temp] = origin;
        indexMap[item] = origin - 1;
    })
    
    return answer;
}