public class Queues {
    Node head;
    private int size;

    class Node{
        int data;
        Node next;

        Node(int data){
            this.data = data;
            this.next = null;
            size++;
        }
    }
    Queues(){
        this.size = 0;
    }

    public void enqueue(int data){
        Node newNode = new Node(data);
        if(head == null){
            head = newNode;
            return;
        }
        Node currentNode = head;
        while(currentNode.next !=null){
            currentNode = currentNode.next;
        }
        currentNode.next=newNode;
    }
    public void printing(){
        if(head == null){
            System.out.println("list is empty");
        }
        Node currentNode = head;
        while(currentNode!=null){
            System.out.print(currentNode.data+"->");
            currentNode = currentNode.next;
        }
    }

    public int dequeue(){
        if(head == null){
            System.out.println("the list is empty");
            return 0;
        }
        size--;
        int removedItem = head.data;
        head = head.next;
        return removedItem;
    }

    public int gettingSize(){
        return size;
    }
}