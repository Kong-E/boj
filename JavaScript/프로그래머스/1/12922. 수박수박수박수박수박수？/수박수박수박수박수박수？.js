function solution(n) {
    let answer = [];
    
    Array.from({length: n}, (_, idx) => idx + 1)
        .forEach(item => item % 2 === 0 ? answer.push("박") : answer.push("수"));
    
    return answer.join("");
}