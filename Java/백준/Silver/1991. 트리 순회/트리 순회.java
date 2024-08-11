
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {
    String data;
    Node left;
    Node right;

    Node(String data) {
        this.data = data;
    }
}

class Tree {
    Node root;

    void insert(String data, String leftData, String rightData) {
        if (root == null) {
            root = new Node(data);
            if (!leftData.equals(".")) root.left = new Node(leftData);
            if (!rightData.equals(".")) root.right = new Node(rightData);
        } else {
            insertNode(root, data, leftData, rightData);
        }
    }

    void insertNode(Node node, String data, String leftData, String rightData) {
        if (node.data.equals(data)) {
            if (!leftData.equals(".")) node.left = new Node(leftData);
            if (!rightData.equals(".")) node.right = new Node(rightData);
        } else {
            if (node.left != null) insertNode(node.left, data, leftData, rightData);
            if (node.right != null) insertNode(node.right, data, leftData, rightData);
        }
    }

    void preorder(Node root) {
        if (root != null) {
            System.out.print(root.data);
            preorder(root.left);
            preorder(root.right);
        }
    }

    void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data);
            inorder(root.right);
        }
    }

    void postorder(Node root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data);
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        Tree tree = new Tree();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String root = st.nextToken();
            String left = st.nextToken();
            String right = st.nextToken();

            tree.insert(root, left, right);
        }

        tree.preorder(tree.root);
        System.out.println();
        tree.inorder(tree.root);
        System.out.println();
        tree.postorder(tree.root);
    }
}