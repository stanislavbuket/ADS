package lab1_5;

import java.util.LinkedList;
import java.util.Queue;

public class BST {
    private TreeNode root;

    public void insertLevel2(Student student) {
        root = insertRec(root, student);
    }

    private TreeNode insertRec(TreeNode node, Student student) {
        if (node == null) {
            return new TreeNode(student);
        }
        if (student.city().compareToIgnoreCase(node.student.city()) < 0) {
            node.left = insertRec(node.left, student);
        } else {
            node.right = insertRec(node.right, student);
        }
        return node;
    }

    public void insertLevel3(Student student) {
        root = insertTreapRec(root, new TreeNode(student));
    }

    private TreeNode insertTreapRec(TreeNode node, TreeNode newNode) {
        if (node == null) {
            return newNode;
        }
        if (newNode.student.city().compareToIgnoreCase(node.student.city()) < 0) {
            node.left = insertTreapRec(node.left, newNode);
            if (node.left.priority > node.priority) {
                node = rotateRight(node);
            }
        } else {
            node.right = insertTreapRec(node.right, newNode);
            if (node.right.priority > node.priority) {
                node = rotateLeft(node);
            }
        }
        return node;
    }

    private TreeNode rotateRight(TreeNode node) {
        TreeNode newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        return newRoot;
    }

    private TreeNode rotateLeft(TreeNode node) {
        TreeNode newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        return newRoot;
    }

    public TreeNode search(String city) {
        return searchRec(root, city);
    }

    private TreeNode searchRec(TreeNode node, String city) {
        if (node == null) {
            return null;
        }
        int cmp = city.compareToIgnoreCase(node.student.city());
        if (cmp == 0) {
            return node;
        }
        if (cmp < 0) {
            return searchRec(node.left, city);
        }
        return searchRec(node.right, city);
    }

    public void printBFS() {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        StringBuilder sb = new StringBuilder("Tree BFS: ");
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            sb.append(curr.student.lastName()).append("(").append(curr.student.city()).append(") ");
            if (curr.left != null) queue.add(curr.left);
            if (curr.right != null) queue.add(curr.right);
        }
        System.out.println(sb);
    }
}
