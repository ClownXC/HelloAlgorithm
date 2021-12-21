package com.algorithm.tree.binarytree;


import java.io.*;
import java.util.*;

public class HuffmanCode {

    public static void main(String[] args) {

//        String content = "i like like like java do you like a java";
//        byte[] contentbytes = content.getBytes();
//        byte[] zipBytes = huffmanZip(contentbytes);
//
//        System.out.println("压缩之后的结果: " + Arrays.toString(zipBytes));
//        System.out.println("压缩之后长度: " + zipBytes.length);
//
//        byte[] sourceBytes = decode(huffmanCodes, zipBytes);
//        System.out.println("原来的字符串: " + new String(sourceBytes));


//        zipFile("/Users/zxc/Desktop/2333.png", "/Users/zxc/Documents/算法/huffman/dst.zip");
        zipFile("/Users/zxc/Documents/算法/huffman/dst.zip", "/Users/zxc/Documents/算法/huffman/23332.png");


    }

    public static byte[] huffmanZip(byte[] contentbytes) {

        List<HuffmanNode> huffmanNodes = getNodes(contentbytes);
        HuffmanNode huffmanRoot = createHuffmanTree(huffmanNodes);

        Map<Byte, String> huffmanCodes = getHuffmanCodes(huffmanRoot);
        System.out.println("编码: " + huffmanCodes);

        return zip(contentbytes, huffmanCodes);
    }

    static StringBuilder builder = new StringBuilder();
    static Map<Byte, String> huffmanCodes = new HashMap<>();


    private static Map<Byte, String> getHuffmanCodes(HuffmanNode root){
        if (root == null){
            return null;
        }

        getHuffmanCodes(root.left, "0", builder);
        getHuffmanCodes(root.right, "1", builder);

        return huffmanCodes;


    }

    /**
     *
     * @param node
     * @param code
     * @param builder
     */
    private static void getHuffmanCodes(HuffmanNode node, String code, StringBuilder builder){

        StringBuilder stringBuilder = new StringBuilder(builder);
        stringBuilder.append(code);

        if (node != null){
            if(node.data == null){
                getHuffmanCodes(node.left, "0", stringBuilder);
                getHuffmanCodes(node.right, "1", stringBuilder);
            }else {

                huffmanCodes.put(node.data, stringBuilder.toString());
            }
        }


    }

    /**
     *
     * @param bytes
     * @param huffmanCodes
     * @return: 返回字符串对应的 huffman 编码
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes){

        StringBuilder stringBuilder = new StringBuilder();

        for (byte b : bytes){
            stringBuilder.append(huffmanCodes.get(b));
        }

        int length = (stringBuilder.length() + 7) / 8;
//        if (stringBuilder.length() % 8 == 0){
//            length = stringBuilder.length() / 8;
//        }else {
//            length = stringBuilder.length() / 8 + 1;
//        }

        byte[] huffmanCodeBytes = new byte[length];
        int index = 0;

        for (int i = 0; i < stringBuilder.length(); i += 8){
            String strByte;
            if (i + 8 > stringBuilder.length()){
                strByte = stringBuilder.substring(i);
            }else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }

        return huffmanCodeBytes;

    }

    /**
     *
     * @param srcFile
     * @param dstFile
     */
    public static void zipFile(String srcFile, String dstFile){

        OutputStream os = null;
        ObjectOutputStream oos = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(srcFile);
            byte[] b = new byte[fis.available()];

            fis.read(b);

            byte[] huffmanBytes = huffmanZip(b);

            os = new FileOutputStream(dstFile);
            oos = new ObjectOutputStream(os);

            oos.writeObject(huffmanBytes);
            oos.writeObject(huffmanCodes);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                oos.close();
                os.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    /**
     *
     * @param zipFile
     * @param dstFile
     */
    public static void unzipFile(String zipFile, String dstFile){

        OutputStream os = null;
        ObjectInputStream ois = null;
        InputStream is = null;
        try {
            is = new FileInputStream(zipFile);
            byte[] b = new byte[is.available()];

            ois = new ObjectInputStream(is);
            byte[] huffmanBytes = (byte[]) ois.readObject();
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();

            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            os = new FileOutputStream(dstFile);
            os.write(bytes);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    /**
     *
     * @param b
     * @return
     */
    private static String byte2BitString(boolean flag, byte b){
        int temp = b;


        if (flag){
            temp |= 256;
        }

        String str = Integer.toBinaryString(temp);
        if (flag){
            return str.substring(str.length() - 8);
        }else {
            return str;

        }
    }

    /**
     *
     * @param huffmanCodes
     * @param huffmanBytes
     * @return
     */
    public static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes){

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < huffmanBytes.length; i++){

            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byte2BitString(!flag, huffmanBytes[i]));

        }

        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()){
            map.put(entry.getValue(), entry.getKey());
        }


        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ){
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag){
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);

                if (b == null){
                    count++;
                }else {
                    flag = false;

                }
            }
            list.add(b);
            i += count;

        }
        byte b[] = new byte[list.size()];
        for(int i = 0; i < b.length; i++){
            b[i] = list.get(i);
        }
        return b;

    }

    /**
     *
     * @param bytes
     * @return
     */
    private static List<HuffmanNode> getNodes(byte[] bytes){

        List<HuffmanNode> nodes = new LinkedList<>();

        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes){
            Integer count = counts.get(b);
            if(count == null){
                counts.put(b, 1);
            }else {
                counts.put(b, count+1);
            }
        }

        for (Map.Entry<Byte, Integer> entry : counts.entrySet()){
            nodes.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        return nodes;
    }


    private static HuffmanNode createHuffmanTree(List<HuffmanNode> nodes ) {

        while (nodes.size() > 1) {

            Collections.sort(nodes);

            HuffmanNode leftNode = nodes.get(0);
            HuffmanNode rightNode = nodes.get(1);
            HuffmanNode parent = new HuffmanNode(null, leftNode.weight + rightNode.weight);

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
    public static void preOrder(HuffmanNode node) {

        List<Object> preList = new ArrayList<>();
        if (node == null) {
            return;
        } else {
            Stack<HuffmanNode> nodeStack = new Stack<>();
            nodeStack.push(node);
            while (! nodeStack.isEmpty()) {

                // 弹出父节点
                HuffmanNode n = nodeStack.pop();
                // 访问父节点
                System.out.println(n.toString());
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



class HuffmanNode implements Comparable<HuffmanNode>{

    Byte data;
    Integer weight;
    HuffmanNode left;
    HuffmanNode right;


    public HuffmanNode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(HuffmanNode o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "HuffmanNode{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }
}