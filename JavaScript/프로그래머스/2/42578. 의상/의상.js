/**
 * 두 숫자를 더합니다. 
 * @param {number} clothes [의상 이름, 의상 종류]
 * @returns {number} 서로 다른 옷의 조합의 수
 */
function solution(clothes) {
    const map = new Map();
    
    clothes.forEach(item => {
        const [_, kind] = item;
        map.set(kind, (map.get(kind) || 1) + 1);
    })
    
    return Array.from(map.values()).reduce((acc, cur) => acc * cur, 1) - 1;
}

// 매일 다른 조합 옷 (다른 의상이 겹치지 않거나, 의상을 추가로 더 착용)
// 각 종류 별 최대 1가지 의상만 착용
// 최소 1개 의상

// 서로 다른 옷의 조합의 수 return
// clothes.length + map.get(의상종류1)*map.get(의상종류2)*...