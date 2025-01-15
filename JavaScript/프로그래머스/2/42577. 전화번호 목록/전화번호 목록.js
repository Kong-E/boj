function solution(phone_book) {
    // 기본 정렬: 사전순
    phone_book.sort();

    let prev, cur = phone_book[0];
    for (let i = 1; i < phone_book.length; i++) {
        [prev, cur] = [cur, phone_book[i]];
        if (cur.startsWith(prev)) {
            return false;
        }
    }
    
    return true;
}