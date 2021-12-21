package com.algorithm.tree.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author zxc
 */
public class HelloBinaryTree {

    private TreeNode root;

    public HelloBinaryTree() {
//        root = new TreeNode(1, "A");
    }

    /**
     * @param value
     */
    public void insert(long value) {

        TreeNode insertNode = new TreeNode(value, "");
        TreeNode current = root;
        TreeNode parent;

        if (root == null) {
            root = insertNode;
        } else {
            while (true) {

                parent = current;
                if (current.getIndex() > value) {
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = insertNode;
                        return;
                    }

                } else {
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = insertNode;
                        return;
                    }
                }
            }

        }


    }


    /**
     * 构建二叉树
     * A
     * <p>
     * B         C
     * <p>
     * D        E         F
     */
    public void createBinaryTree() {


        TreeNode nodeB = new TreeNode(2, "B");
        TreeNode nodeC = new TreeNode(3, "C");
        TreeNode nodeD = new TreeNode(4, "D");
        TreeNode nodeE = new TreeNode(5, "E");
        TreeNode nodeF = new TreeNode(6, "F");

        root.leftChild = nodeB;
        root.rightChild = nodeC;
        nodeB.leftChild = nodeD;
        nodeB.rightChild = nodeE;
        nodeC.rightChild = nodeF;
    }


    /**
     * @param nodes
     */
    public void createBinaryTree(String nodes) {

        //todo

    }

    /**
     * 获取二叉树高度
     *
     * @param node
     * @return
     */
    public long getHeight(TreeNode node) {

        if (node == null) {
            return 0;
        } else {
            long i = getHeight(node.leftChild);
            long j = getHeight(node.rightChild);
            return (i < j) ? j + 1 : i + 1;
        }

    }

    public long getSize() {
        return getSize(root);
    }

    /**
     * 获取二叉树的节点数
     *
     * @param node
     * @return
     */
    private long getSize(TreeNode node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + getSize(node.leftChild) + getSize(node.rightChild);
        }

    }


    /**
     * 删除二叉树节点
     *
     * @param data
     * @return
     */
    public boolean delete(long data) {

        TreeNode current = root;
        TreeNode parent = current;
        boolean isLeftChild = true;
        while (current.getIndex() != data) {

            if (current.getIndex() > data) {
                current = current.leftChild;
                isLeftChild = true;
            } else {
                current = current.leftChild;
                isLeftChild = false;
            }

        }

        if (current == null) {
            return false;
        }


        if (current.leftChild == null && current.rightChild == null) {
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.leftChild = null;
            } else {
                parent.rightChild = null;
            }

            return true;

        } else if (current.rightChild == null) {

            if (current == root) {
                root = current.leftChild;
            } else if (isLeftChild) {
                parent.leftChild = current.leftChild;
            } else {
                parent.rightChild = current.leftChild;
            }

            return true;

        } else if (current.leftChild == null) {

            if (current == root) {
                root = current.rightChild;
            } else if (isLeftChild) {
                parent.leftChild = current.rightChild;
            } else {
                parent.rightChild = current.rightChild;
            }

            return true;
        } else {

            TreeNode successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.leftChild = successor;
            } else {
                parent.rightChild = successor;
            }
            successor.leftChild = current.leftChild;


        }

        return false;

    }

    /**
     * @param deleteNode
     * @return
     */
    public TreeNode getSuccessor(TreeNode deleteNode) {

        TreeNode successor = deleteNode;
        TreeNode successorParent = deleteNode;
        TreeNode current = deleteNode.rightChild;

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.leftChild;

        }

        if (successor != deleteNode.rightChild) {
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = deleteNode.rightChild;
        }

        return successor;
    }

    /**
     * 前序
     *
     * @param node
     */
    public static List<Object> nonRecPreOrder(TreeNode node) {

        List<Object> preList = new ArrayList<>();
        if (node == null) {
            return preList;
        } else {
            Stack<TreeNode> nodeStack = new Stack<>();
            nodeStack.push(node);
            while (! nodeStack.isEmpty()) {

                // 弹出父节点
                TreeNode n = nodeStack.pop();
                // 访问父节点
                preList.add(n.getValue());
                // 压入子节点
                if (n.rightChild != null) {
                    nodeStack.push(n.rightChild);
                }
                if (n.leftChild != null) {
                    nodeStack.push(n.leftChild);
                }

            }

            return preList;
        }

    }







    /**
     * 前序
     *
     * @param node
     */
    public static void preOrderTraverse(TreeNode node) {

        if (node == null) {
            return;
        } else {
            System.out.println("Node: " + node.getValue());
            preOrderTraverse(node.leftChild);
            preOrderTraverse(node.rightChild);
        }

    }

    /**
     * @param node
     */
    public static void inOrderTraverse(TreeNode node) {

        if (node == null) {
            return;
        } else {
            inOrderTraverse(node.leftChild);
            System.out.println("Node: " + node.getValue());
            inOrderTraverse(node.rightChild);
        }

    }


    /**
     * @param node
     */
    public static void afterOrderTraverse(TreeNode node) {

        if (node == null) {
            return;
        } else {
            afterOrderTraverse(node.leftChild);
            afterOrderTraverse(node.rightChild);
            System.out.println("Node: " + node.getValue());
        }


    }


    /**
     * 通过前序遍历的数据反向生成二叉树
     * <p>
     * A
     * <p>
     * B             C
     * <p>
     * D       E      #        F
     * <p>
     * #     #  #    #         #     #
     * <p>
     * <p>
     * ABD##E##C#F##
     *
     * @param data
     */
    public void createBiTreePre(int index, ArrayList<String> data) {

        createBiTree(data.size(), data);
    }

    /**
     * @param size: 节点下标，判断是否为根节点: index=0为当前节点为根结点
     * @param data
     * @return
     */
    private TreeNode createBiTree(int size, ArrayList<String> data) {

        if (size < 0 || data == null || data.size() == 0) {
            return null;
        }

        String value = data.get(0);
        TreeNode node;

        if (value.equals("#")) {
            data.remove(0);
            return null;
        }

        int index = size - data.size();
        node = new TreeNode(index, value);
//        if(index == 0){//根节点
//            root = node;
//            data.remove(0);
//        }else{
//            data.remove(0);
//            node.leftChild = createBiTree(size, data);
//            node.rightChild = createBiTree(size, data);
//        }

        if (index == 0) {//根节点
            root = node;
        }
        data.remove(0);
        node.leftChild = createBiTree(size, data);
        node.rightChild = createBiTree(size, data);

        return node;

    }

    /**
     * 创建查找二叉树，添加节点
     *
     * @param data
     * @return
     */
    public TreeNode put(int data) {
        TreeNode<Integer> node = null;
        TreeNode parent = null;
        boolean isLeftChild = false;


        if (isEmptyTree(root)) {
            node = new TreeNode<>(0, data);
            root = node;
            return node;

        }
        node = root;
        while (node != null) {

            parent = node;

            System.out.println(node.getValue());
            if (data > node.getValue()) {
                node = node.rightChild;
                isLeftChild = false;
            } else if (data < node.getValue()) {
                node = node.leftChild;
                isLeftChild = true;
            } else {
                return node;
            }

        }
        //表示将此节点添加到相应的位置
        node = new TreeNode<>(0, data);
        if (isLeftChild) {
            parent.leftChild = node;
        } else {
            parent.rightChild = node;
        }
        node.parent = parent;
        return node;
    }

    /**
     * 判断是否为空树
     *
     * @param root
     * @return
     */
    public boolean isEmptyTree(TreeNode root) {
        return (root == null) ? true : false;
    }










    public static void main(String[] args) {


        HelloBinaryTree searchTree = new HelloBinaryTree();
        int[] nodes = new int[]{12, 3, 45, 67, 23, 33, 31, 22, 40, 87, 90, 100, 130};
        for (int node : nodes) {

            System.out.println(node);
            searchTree.put(node);
        }

        System.out.println("size:   " + searchTree.getSize());

        HelloBinaryTree.inOrderTraverse(searchTree.root);

    }

}
