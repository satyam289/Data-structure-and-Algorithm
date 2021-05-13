package Linked;

// Last node always connected to first node
class CircularLinkedList { 

    private static class Link {
        public int data;
        public Link next;

        public Link(int data) {
            this.data = data;
            next = null;
        }
    }
    Link current;
    Link previous;
    int count = 0;

    CircularLinkedList() {
        current = null;
        previous = null;
    }

    public static void main(String[] args) {
        CircularLinkedList cl = new CircularLinkedList();
        cl.insert(5);
        cl.insert(4);
        cl.insert(7);
        cl.insert(9);
        // cl.deleteAtSpot();
        // cl.delete();
        // cl.search(6);
        // System.out.println("deleting "+cl.delete());
        // System.out.println("deleting "+cl.delete());
        cl.stepWiseDisplay();
        cl.stepWiseDisplay();
        cl.stepWiseDisplay();
        cl.stepWiseDisplay();
    }

    public void insert(int value) {
        Link newLink = new Link(value);
        if (current == null) {
            current = newLink;
            current.next = current;
        } else {
            newLink.next = current.next;
            current.next = newLink;
            previous = current;
            current = newLink;
        }

    }

    public int delete() { // assume non empty Linked, delete next element of current Node
        int data = current.next.data;
        if (current.next == current) { // one element left
            current = null;
            previous = null;
            return data;
        } else {
            current.next = current.next.next;
            return data;
        }
    }

    public int deleteAtSpot() {
        int data = current.data;
        if (current.next == current) { // one element left
            current = null;
            previous = null;
            return data;
        } else {
            previous.next = current.next;
            current = current.next;
        }
        return data;
    }

    public void stepWiseDisplay() {
        if (current == null) {
            System.out.println("No element in Circular Linked List");
        } else {
            System.out.println("step " + ++count + ": " + current.data);
            current = current.next;
        }
    }

    public void search(int key) {
        int counter = 0;
        while (current.data != key) {
            if (counter == 20)
                break;
            current = current.next;
            counter++;
        }
        if (current.data == key)
            System.out.println("Found");
        else
            System.out.println("Can't found after 20 search");
    }
}
