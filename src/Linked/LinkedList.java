package Linked;

public class LinkedList {

    public Link head;

    public LinkedList() {
        head = null;
    }

    public void insert(int end) {
        if (end == 0) {
            if (head == null)
                head = new Link(1);
            else {
                Link temp = head.next;
                head = new Link(1);
                head.next = temp;
            }
        } else {
            if (head == null)
                head = new Link(0);
            Link current = head;
            for (int i = 0; i < end; i++) {

                if (current.next == null)
                    current.next = new Link(0);

                current = current.next;

            }
            //Link temp= current.next;
            current.data = 1;
            //current.next=temp;
        }
        display();


    }

    public void display() {
        Link current = head;
        while (current != null) {
            System.out.print(current.data + "--->");
            current = current.next;
        }
        System.out.println("");
    }



}
