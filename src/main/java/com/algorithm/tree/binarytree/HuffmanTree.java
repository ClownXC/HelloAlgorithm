package com.algorithm.tree.binarytree;

import sun.security.krb5.internal.PAData;

import javax.xml.crypto.NodeSetData;
import java.util.*;

public class HuffmanTree {


    public static void main(String[] args) {
        int arr[] = {13, 7, 8, 3, 29, 6, 1};

        Node root = createHuffmanTree(arr);
        nonRecPreOrder(root);


    }

    private static Node createHuffmanTree(int[] arr) {
        List<Node> nodes = new LinkedList<>();
        for (int element : arr){
            nodes.add(new Node(element));
        }
        while (nodes.size() > 1) {

            Collections.sort(nodes);

            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(leftNode.value + rightNode.value);

            parent.left = leftNode;
            parent.right = rightNode;

            nodes.remove(leftNode);
            nodes.remove(rightNode);

            nodes.add(parent);
        }

        return nodes.get(0);
    }


    /**
     * 前序
     *
     * @param node
     */
    public static void nonRecPreOrder(Node node) {

        List<Object> preList = new ArrayList<>();
        if (node == null) {
            return;
        } else {
            Stack<Node> nodeStack = new Stack<>();
            nodeStack.push(node);
            while (! nodeStack.isEmpty()) {

                // 弹出父节点
                Node n = nodeStack.pop();
                // 访问父节点
                System.out.print(n.value + " ");
                // 压入子节点
                if (n.right != null) {
                    nodeStack.push(n.right);
                }
                if (n.left != null) {
                    nodeStack.push(n.left);
                }

            }
        }

    }


}


class Node implements Comparable<Node>{

    // 权值
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }


    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }
}