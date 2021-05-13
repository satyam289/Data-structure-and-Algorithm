package Linked;

// Double Ended Priority Queue
class PrirorityQ {

    private static class Link {
        public int data;
        public Link next;

        public Link(int data) {
            this.data = data;
            next = null;
        }
    }

    static Link first;
    static Link last;

    public static void insertAtLast(int a) { // Normal enqueue operation
        Link newLink = new Link(a);
        if (first == null)
            first = newLink;
        else
            last.next = newLink;
        last = newLink;
    }

    public static void insertAtPosition(int data, int position) {
        Link newNode = new Link(data);
       

        if (first == null) {
            if (position == 0) {  // first Node
                first = newNode;
                last = first;
            } else {
                return;
            }
        }
        if (first != null && position == 0) {  // insert at begin
            newNode.next = first;
            first = newNode;
            return;
        }

        Link current = first;
        Link previous = null;
        int i = 0;
        while (i < position) {
            previous = current;
            current = current.next;
            if (current == null) { // At end
                break;
            }
            i++;
        }
        newNode.next = current;
        if(current == null){ 
            last = newNode;
        }
        previous.next = newNode;
    }

    public static int deleteAtFirst() {
        if (first == null)
            throw new RuntimeException("Empty queue");
        int data = first.data;
        first = first.next;
        return data;
    }

    private static void print() {
        String output = "[";
        Link current = first;
        while (current != null) {
            output += (current.data + ",");
            current = current.next;
        }
        output += "]";
        System.out.println(output);
    }

    public static void main(String[] args) {
        insertAtLast(1);
        insertAtLast(2);
        insertAtLast(3);
        deleteAtFirst();
        insertAtPosition(100, 1);
        insertAtPosition(90, 0);
        print();
    }
}