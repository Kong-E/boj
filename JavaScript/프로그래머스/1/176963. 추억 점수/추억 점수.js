function solution(name, yearning, photo) {
    const map = {};
    const answer = Array.from({length: photo.length}, () => 0);
    
    name.reduce((acc, cur, idx) => {
        map[cur] = yearning[idx];
        return acc
    }, {});
    
    photo.forEach((item, idx) => {
              item.forEach(person => {
                  !!map[person] && (answer[idx] += map[person])
        })
    })
    return answer;
}