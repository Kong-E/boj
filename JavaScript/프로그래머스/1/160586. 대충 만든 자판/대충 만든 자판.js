function solution(keymap, targets) {
    const answer = [];
    const map = {};
    
    // 알파벳마다 가장 적은 입력 수를 저장하면 되겠네
    keymap.forEach(key => Array.from(key).forEach(
        (item, idx) => map[item] = Math.min(map[item] ?? 100, idx + 1)
    ));
    
    targets.forEach((target, idx) => {
            const arr = Array.from(target);
            let item;
            for (let i = 0; i < target.length; i++) {
                item = arr[i];
                if (map[item]) {
                    if (answer[idx]) answer[idx] += map[item];
                    else answer[idx] = map[item];
                } else {
                    answer[idx] = -1;
                    break;
                }
            }
        }
    )
    
    return answer;
}