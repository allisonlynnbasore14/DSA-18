package your_code;

public class MyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Chicken val;
        Node prev;
        Node next;

        private Node(Chicken d, Node prev, Node next) {
            this.val = d;
            this.prev = prev;
            this.next = next;
        }

        private Node(Chicken d) {
            this.val = d;
            prev = null;
            next = null;
        }
    }

    public MyLinkedList() {
        this.head = head;
        this.tail = tail;
        this.size = size;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(Chicken c) {
        addLast(c);
    }

    public Chicken pop() {
        return removeLast();
    }

    public void addLast(Chicken c) {
        Node newNode = new Node(c, null, null);
        size ++;
        if(head == null){
          head = newNode;
        }else{
            Node target = head;
            while(target.next !=null){
                target = target.next;
            }
            target.next = newNode;
            newNode.prev = target;
        }
    }

    public void addFirst(Chicken c) {
        size++;
        Node newNode = new Node(c, null, null);
        if(head == null){
            head = newNode;
            tail = newNode;
        }else{
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    public Chicken get(int index) {
        // next time use the size variable to have it return null if the index is out of bounds
        Node target = head;
        int count = 0;
        while(target != null){
            if(count == index){
                return target.val;
            }
            count ++;
            target = target.next;
        }
        return null;
    }


    public Chicken remove(int index){
        Node target = head;
        Chicken foundChicken = null;
        for(int i = 0; i < size ; i++){
            if(index == i){
                foundChicken = target.val;
                if(target.next != null){
                    target.next.prev = target.prev;
                }
                if(target.prev != null){
                    target.prev.next = target.next;
                }
            }

            if(i == size -1){
                tail = target;
            }
            if(i == 0){
                head = target;
            }
            target = target.next;
        }
        size --;
        return foundChicken;
    }



    public Chicken removeFirst() {
        Node target = head;
        if(target == null){
            return target.val;
        }
        size --;
        if(target.next!=null){
            head = target.next;
            head.prev = null;
        }else{
            head = null;
        }
        return target.val;
    }

    public Chicken removeLast() {
        Node target = head;
        if(target == null){
            return target.val;
        }
        while(target.next != null){
            target = target.next;
        }
        if(target.prev!=null){
            tail = target.prev;
            tail.next = null;
        }else{
            tail = null;
        }
        size --;
        return target.val;
    }
}