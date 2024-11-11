function solution(arr) {
    const min = Math.min(...arr);
    arr = arr.filter(num => num !== min);
    
    return arr.length === 0 ? [-1] : arr;
}