package Linked;

public class DoublyEndedLinkedList_OpenBothEnd {

    private static class LinkDD {
        int data;
        LinkDD pre;
        LinkDD next;

        LinkDD(int data) {
            this.data = data;
            pre = null;
            next = null;
        }
    }

    LinkDD first;
    LinkDD last;

    public static void main(String[] args) {
        DoublyEndedLinkedList_OpenBothEnd dl = new DoublyEndedLinkedList_OpenBothEnd();
        dl.insertLast(3);
        dl.insertFirst(5);
        dl.insertFirst(6);
        dl.insertAtSpot(6, 23);
        // dl.deleteAtSpot(6);
        dl.displayForward();
        dl.dispalyBackward();
    }

    public void insertFirst(int value) {
        LinkDD newLink = new LinkDD(value);
        if (first == null) {
            first = newLink;
            last = newLink;
        } else {
            newLink.next = first;
            first.pre = newLink;
            first = newLink;
        }
    }

    public void insertLast(int value) {
        LinkDD newLink = new LinkDD(value);
        if (first == null) {
            first = newLink;
            last = newLink;
        } else {
            newLink.pre = last;
            last.next = newLink;
            last = newLink;
        }
    }

    public int deleteFirst() { //assume non-empty queue

        int data = first.data;
        if (first.next == null) {
            first = null;
            last = null;
        } else {
            first.next.pre = null;
            first = first.next;
        }
        return data;
    }

    public int deleteLast() { // assume non-empty queue
        int data = last.data;
        if (last.pre == null) {
            last = null;
            first = null;
        } else {
            last.pre.next = null;
            last = last.pre;
        }
        return data;
    }

    public int deleteAtSpot(int key) { // delete at current position at matched key(assume non empty)
        LinkDD current = first;
        LinkDD previous = first;
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

    public void insertAtSpot(int key, int value) { // insert at current position at matched key
        LinkDD current = first;
        LinkDD previous = first;
        LinkDD newLink = new LinkDD(value);

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
            return;
        }
        if (current == last) {
            newLink.pre = last;
            last.next = newLink;
        } else {
            previous.next = newLink;
            newLink.pre = previous;
            current.pre = newLink;
            newLink.next = current;
        }
    }

    public void displayForward() {
        LinkDD current = first;
        while (current != null) {
            System.out.print(current.data + "->");
            current = current.next;
        }
        System.out.println("");
    }

    public void dispalyBackward() {
        LinkDD current = last;
        while (current != null) {
            System.out.print(current.data + "->");
            current = current.pre;
        }
        System.out.println("");
    }
}
