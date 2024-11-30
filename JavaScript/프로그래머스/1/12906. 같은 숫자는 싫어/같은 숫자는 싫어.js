function solution(arr)
{
    const answer = [];
    let lastSeen = null;
    
    arr.forEach(item => {
        if (item !== lastSeen) {
            answer.push(item);
        }
        lastSeen = item;
    })
    
    if (answer[answer.length - 1] !== arr[arr.length - 1]) {
        answer.push(arr[arr.length - 1]);
    }
    
    return answer;
}