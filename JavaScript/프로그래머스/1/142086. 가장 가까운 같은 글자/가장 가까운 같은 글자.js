function solution(s) {
    const map = {}
    return Array.from(s).map((c, idx) => {
        if (map[c]) {
            map[c] = [idx, idx - map[c][0]];
        } else {
            map[c] = [idx, -1]
        }
        return map[c][1];
    });
}