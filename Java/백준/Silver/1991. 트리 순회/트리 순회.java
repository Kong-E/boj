import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Main {
    static StringBuilder sb = new StringBuilder();
    static class Node {
        String key;
        String left;
        String right;

        Node(String key) {
            this.key = key;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Node> map = new HashMap<>();

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String root = st.nextToken();
            String left = st.nextToken();
            String right = st.nextToken();

            Node rootNode = new Node(root);
            if(!left.equals(".")) rootNode.left = left;
            if(!right.equals(".")) rootNode.right = right;

            map.put(root, rootNode);
        }

        recursion("preorder", map, "A");
        sb.append("\n");
        recursion("inorder", map, "A");
        sb.append("\n");
        recursion("postorder", map, "A");

        System.out.println(sb.toString());
    }

    static void recursion(String order, Map<String, Node> map, String root) {
        String left = map.get(root).left;
        String right = map.get(root).right;

        if (order.equals("preorder")) {
            sb.append(root);
            if (left != null) recursion(order, map, left);
            if (right != null) recursion(order, map, right);
        }
        if (order.equals("inorder")) {
            if (left != null) recursion(order, map, left);
            sb.append(root);
            if (right != null) recursion(order, map, right);
        }
        if (order.equals("postorder")) {
            if (left != null) recursion(order, map, left);
            if (right != null) recursion(order, map, right);
            sb.append(root);
        }
    }
}