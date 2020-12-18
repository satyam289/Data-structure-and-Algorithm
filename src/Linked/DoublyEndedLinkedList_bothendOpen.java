package Linked;

public class DoublyEndedLinkedList_bothendOpen {

    private static class LinkD {
        int data;
        LinkD pre;
        LinkD next;

        LinkD(int data) {
            this.data = data;
            pre = null;
            next = null;
        }
    }

    LinkD first;
    LinkD last;

    public static void main(String[] args) {
        DoublyEndedLinkedList_bothendOpen dl = new DoublyEndedLinkedList_bothendOpen();
        dl.insertLast(3);
        dl.insertFirst(5);
        dl.insertFirst(6);
        dl.insertAtSpot(6, 23);
        //dl.deleteAtSpot(6);
        dl.displayForward();
        //dl.dispalyBackward();
    }

    public void insertFirst(int value) {                       //insertLeft()
        LinkD newLink = new LinkD(value);
        if (first == null) {
            first = newLink;
            last = newLink;
        } else {
            newLink.next = first;
            first.pre = newLink;
            first = newLink;
        }
    }

    public void insertLast(int value) {                     //insertRight()
        LinkD newLink = new LinkD(value);
        if (first == null) {
            first = newLink;
            last = newLink;
        } else {
            newLink.pre = last;
            last.next = newLink;
            last = newLink;
        }

    }

    public int deleteFirst() {                            //removeLeft(),assume non-empty queue

        int data = first.data;
        if (first.next == null) {                             //for one elements
            first = null;
            last = null;
        } else {
            first.next.pre = null;
            first = first.next;
        }
        return data;
    }

    public int deleteLast() {                             //removeRight() ,assume non-empty queue

        int data = last.data;
        if (last.pre == null) {                              //for one elements
            last = null;
            first = null;
        } else {
            last.pre.next = null;
            last = last.pre;
        }
        return data;

    }

    public int deleteAtSpot(int key) {                           //delete at current position at matched key(assume non empty)
        LinkD current = first;
        LinkD previous = first;
        while (current != null) {
            if (current.data == key)
                break;
            previous = current;
            current = current.next;
        }
        int data = current.data;
        if (current == first) {
            first = first.next;
        }
        if (current == last) {
            previous.next = null;
            last = previous;
        } else {
            previous.next = current.next;
            current.next.pre = previous;
        }
        return data;

    }

    public void insertAtSpot(int key, int value) {                           //insert at current position at matched key
        LinkD current = first;
        LinkD previous = first;
        LinkD newLink = new LinkD(value);

        while (current != null) {
            if (current.data == key)
                break;
            previous = current;
            current = current.next;
        }
        if (current == first) {

            newLink.next = first;
            first.pre = newLink;
            first = newLink;
            return;                                    //return is needed as it will  continues executing the next block causing undefine loop
        }
        if (current == last) {
            newLink.pre = last;
            last.next = newLink;
        } else {
            previous.next = newLink;
            newLink.pre = previous;                                    //before pasting old link(previous here) , do all required operation

            current.pre = newLink;
            newLink.next = current;

        }
    }

    public void displayForward() {
        LinkD current = first;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }

    }

    public void dispalyBackward() {
        LinkD current = last;
        while (current != null) {
            System.out.println(current.data);
            current = current.pre;
        }
    }
}




