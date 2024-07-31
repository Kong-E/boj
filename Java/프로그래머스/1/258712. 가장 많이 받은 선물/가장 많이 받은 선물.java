import java.io.*;
import java.util.*;

class Solution {
//    public static void main(String[] args) throws IOException {
//        int answer = solution(new String[] {"a", "b", "c"},
//                new String[] {"a b", "b a", "c a", "a c", "a c", "c a"});
//
//        System.out.println(answer);
//    }
    static class Person {
        String name;
        Map<String, Integer> receive = new HashMap<>(); // 받은 선물의 수
        Map<String, Integer> send = new HashMap<>(); // 준 선물의 수
        int score = 0;

        Person(String name) {
            this.name = name;
        }
    }

    static void calculateScore(Person p) {
        int score = 0;
        for (String key : p.receive.keySet()) {
            score -= p.receive.get(key);
        }
        for (String key : p.send.keySet()) {
            score += p.send.get(key);
        }
        p.score = score;
    }

    public static int solution(String[] friends, String[] gifts) {
        StringTokenizer st;
        Map<String, Person> map = new HashMap<>();
        Map<String, Integer> giftMap = new HashMap<>();

        //gifts로 각각의 receive와 send를 계산한 다음
        //사람 객체를 어떻게 저장하지?
        for (int i = 0; i < gifts.length; i++) {
            st = new StringTokenizer(gifts[i]);
            String sender = st.nextToken();
            String receiver = st.nextToken();
            Person senderObj = map.getOrDefault(sender, new Person(sender));
            Person receiverObj = map.getOrDefault(receiver, new Person(receiver));
            senderObj.send.put(receiver, senderObj.send.getOrDefault(receiver, 0) + 1);
            receiverObj.receive.put(sender, receiverObj.receive.getOrDefault(sender, 0) + 1);
            map.put(sender, senderObj);
            map.put(receiver, receiverObj);
        }

        //Map에 다음 달 선물 개수 저장
        //어떻게?
        //calculateScore를 각각 돌려서 더 크면 map.put(map.getOrDefault(사람이름, 0) + 1);
        for (int i = 0; i < friends.length; i++) {
            for (int j = i + 1; j < friends.length; j++) {
                Person one = map.getOrDefault(friends[i], new Person(friends[i]));
                Person two = map.getOrDefault(friends[j], new Person(friends[j]));
                Map<String, Integer> oneReceive = one.receive;
                Map<String, Integer> twoReceive = two.receive;
                if (oneReceive.containsKey(friends[j])
                        || twoReceive.containsKey(friends[i])) {
                    int oneSend = twoReceive.getOrDefault(friends[i], 0);
                    int twoSend = oneReceive.getOrDefault(friends[j], 0);
                    if (oneSend > twoSend) {
                        giftMap.put(friends[i], giftMap.getOrDefault(friends[i], 0) + 1);
                    }
                    if (oneSend < twoSend) {
                        giftMap.put(friends[j], giftMap.getOrDefault(friends[j], 0) + 1);
                    }
                }
                if ((!oneReceive.containsKey(friends[j])
                        && !twoReceive.containsKey(friends[i]))
                        || oneReceive.getOrDefault(friends[j], 0).equals(twoReceive.get(friends[i]))) {
                    calculateScore(one);
                    calculateScore(two);
                    if (one.score > two.score) {
                        giftMap.put(friends[i], giftMap.getOrDefault(friends[i], 0) + 1);
                    }
                    if (one.score < two.score) {
                        giftMap.put(friends[j], giftMap.getOrDefault(friends[j], 0) + 1);
                    }
                }
            }
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(giftMap.entrySet());

        list.sort(Map.Entry.comparingByValue());

        return list.isEmpty() ? 0 : list.get(list.size() - 1).getValue();
    }
}