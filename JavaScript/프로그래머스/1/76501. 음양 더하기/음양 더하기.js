function solution(absolutes, signs) {
    let sum = 0;
    absolutes.forEach((item, index) => sum += (signs[index] ? 1 : -1) * item)
    return sum;
}