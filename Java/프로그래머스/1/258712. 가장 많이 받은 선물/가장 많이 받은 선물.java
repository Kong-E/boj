import java.io.*;
import java.util.*;

class Solution {
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

        int ans = 0;
        for (int i = 0; i < friends.length; i++) {
            for (int j = i + 1; j < friends.length; j++) {
                Person one = map.getOrDefault(friends[i], new Person(friends[i]));
                Person two = map.getOrDefault(friends[j], new Person(friends[j]));
                Map<String, Integer> oneReceive = one.receive;
                Map<String, Integer> twoReceive = two.receive;
                if (oneReceive.containsKey(friends[j])
                        || twoReceive.containsKey(friends[i])) {
                    if (oneReceive.getOrDefault(friends[j], 0) 
                        < twoReceive.getOrDefault(friends[i], 0)) {
                        giftMap.put(friends[i], giftMap.getOrDefault(friends[i], 0) + 1);
                    }
                    if (oneReceive.getOrDefault(friends[j], 0) 
                        > twoReceive.getOrDefault(friends[i], 0)) {
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
                ans = Math.max(Math.max(giftMap.getOrDefault(friends[i], 0), 
                                        giftMap.getOrDefault(friends[j],0)), 
                               ans);
            }
        }

        return ans;
    }
}