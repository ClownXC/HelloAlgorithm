package com.algorithm.list.doublelinkedlist;



public class DoubleLinkedList {


    public static void main(String[] args) {

        DoubleLinkedList testList = new DoubleLinkedList();


//        HeroNode head1 = new HeroNode(1, "zxc","xc");
        HeroNode head2 = new HeroNode(2, "zxc2","xc2");
//        HeroNode head3 = new HeroNode(3, "zxc3","xc3");
//        HeroNode head4 = new HeroNode(4, "zxc4","xc4");
//        HeroNode head5 = new HeroNode(5, "zxc5","xc5");
//
//
//
        testList.addByOrder(head2);
//        testList.addByOrder(head5);
//        testList.addByOrder(head3);
//        testList.addByOrder(head1);
//        testList.addByOrder(head4);


        testList.list();

//        System.out.println("length: "
//                +DoubleLinkedList.getLength(testList.getHead()));






    }



    private HeroNode head = new HeroNode(0, null,null);


    public HeroNode getHead() {
        return head;
    }

    /**
     *
     * @param node
     */
    public void addByOrder(HeroNode node){

        HeroNode temp = head;
        boolean heroIdExisted = false;


        while (true){

            System.out.println("---------------------------"+temp.next);

            if(temp.next == null){
                break;
            }


            if(temp.next.no > node.no){
                break;
            }else if(temp.next.no == node.no){
                heroIdExisted = true;
                break;
            }else {
                temp = temp.next;
            }
        }

        if(heroIdExisted){
            System.out.println("id is existed");
        }else{

            System.out.println("temp.next.no---------------------------"+temp.no);
            temp.next = node;
            node.pre = temp;

        }

    }

    /**
     *
     * @param node
     */
    public void update(HeroNode node){

        if(head.next == null){
            System.out.println("------------------------------");
        }

        HeroNode temp = head;
        boolean flag = false;

        while (true){

            if(temp.next == null){
                System.out.println("over");
                break;
            }

            if(temp.no == node.no){
                flag = true;
                break;

            }else{
                temp = temp.next;
            }

        }

        if(flag){
            temp.name = node.name;
            temp.nickName = node.nickName;
        }else {
            System.out.println("not found ");
        }




    }

    public void list(){

        if(head.next == null){
            return;
        }

        HeroNode temp = head;

        while (true){

            if(temp == null){
                break;
            }

            System.out.println("node:---"+temp.toString());

            temp = temp.next;

        }




    }


    /**
     *
     * @param addNode
     */
    public void add(HeroNode addNode){

        HeroNode temp = head;

        while (true){
            if(temp.next == null){
                break;
            }

            temp = temp.next;

        }

        temp.next = addNode;
        addNode.pre = temp;
    }


    /**
     *
     * @param delNo
     */
    public void delete(int delNo){


        if(head.next == null){
            return;
        }

        HeroNode temp = head.next;
        boolean flag = false;


        while (true){

            if(temp == null){
                break;
            }
            if(temp.no == delNo){
                flag = true;
                break;
            }else{
                temp = temp.next;
            }
        }

        if(flag){
            temp.pre.next = temp.next;

            if(temp.next != null){
                temp.next.pre = temp.pre;
            }

        }else{
            System.out.println("not found ");
        }

    }


    /**
     *
     * @return
     */
    public static int getLength(HeroNode head){

        if(head.next == null){
            return 0;
        }

        HeroNode current = head.next;
        int length = 0;

        while(current != null){
            length ++;
            current = current.next;
        }

        return length;




    }




    /**
     *
     * @return
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index){

        if(head.next == null){
            return null;
        }

        int size = getLength(head);

        if(index < 0 || index > size){
            return null;
        }

        HeroNode current = head.next;
        for(int i = 0; i < size -index; i++){
            current = current.next;
        }
        return current;
    }





}
class HeroNode{

    int no;
    String nickName;
    String name;
    HeroNode next;
    HeroNode pre;


    public HeroNode(int no, String nickName, String name) {
        this.no = no;
        this.nickName = nickName;
        this.name = name;
    }


    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", nickName='" + nickName + '\'' +
                ", name='" + name + '\'' +
                ", next=" + next +
                ", pre=" + pre +
                '}';
    }
}