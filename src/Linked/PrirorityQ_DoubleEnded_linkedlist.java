package Linked;


//In queue using LinkedList implement always thinks insertLast() , deleteFirst() , better choice DoubleEndedLinkedList 
class PrirorityQ_DoubleEnded_linkedlist {

    private static class Link {
        public int data;
        public Link next;

        public Link(int data) {
            this.data = data;
            next = null;
        }
    }

    Link first;
    Link last;

    PrirorityQ_DoubleEnded_linkedlist() {
        first = null;
        last = null;
    }

    public static void main(String[] args) {
        PrirorityQ_DoubleEnded_linkedlist pl = new PrirorityQ_DoubleEnded_linkedlist();
        //pl.enqueInsertLast(5);
        //pl.enqueInsertLast(6);
        pl.enqueInsertSorted(6);
        pl.enqueInsertSorted(2);
        pl.enqueInsertSorted(1);
        pl.dequeDeleteFirst();
        pl.enqueInsertSorted(8);
        pl.enqueInsertSorted(9);
        //pl.enqueInsertLast(7);
        System.out.println(pl);
    }

    public void enqueInsertLast(int a) {                    //Normal enqueue operation
        Link newLink = new Link(a);
        if (first == null)
            first = newLink;
        else
            last.next = newLink;
        last = newLink;
    }


    public void enqueInsertSorted(int a) {                        //inserting at current position, we think of previous pointer(just one back of current)
        Link newLink = new Link(a);
        if (first == null) {
            first = newLink;
            last = newLink;
        } else {

            Link current = first;
            Link previous = first;

            while (current != null && current.data < a) {

                previous = current;
                //System.out.println("previous:  "+previous.data);
                current = current.next;
            }
            if (current == first) {                       //for one first elements
                newLink.next = first;
                first = newLink;
            } else {
                newLink.next = previous.next;
                previous.next = newLink;
            }
        }
    }

    public int dequeDeleteFirst() {
        if (first == null) throw new RuntimeException("Empty queue");
        int data = first.data;
        first = first.next;
        return data;
    }

    public String toString() {
        String output = "[";
        Link current = first;
        while (current != null) {
            output += (current.data + ",");
            current = current.next;
        }
        output += "]";
        return output;
    }
}