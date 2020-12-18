package Linked;

public class MergeSortLinkedList {

    class Link {
        int data;
        Link next;

        Link(int data) {
            this.data = data;
        }

        Link() {
        }
    }

    class LinkedList {
        Link head;

        public void insertFirst(int data) {
            if (head == null)
                head = new Link(data);
            else {
                Link newlink = new Link(data);
                newlink.next = head;
                head = newlink;
            }
        }
    }


    public static void main(String[] args) {
        MergeSortLinkedList m = new MergeSortLinkedList();
        m.dosort();
    }


    private void dosort() {

        LinkedList l = new LinkedList();
        l.insertFirst(1);
        l.insertFirst(4);
        l.insertFirst(2);
        l.insertFirst(9);
        l.insertFirst(5);
        Link temp = l.head;
        while (temp != null) {
            System.out.print(temp.data + " --> ");
            temp = temp.next;
        }
        System.out.println(" ");
        Link head1 = domergeSort(l.head);
        while (head1 != null) {
            System.out.print(head1.data + " --> ");
            head1 = head1.next;
        }
    }

    private Link domergeSort(Link link) {
        if (link == null || link.next == null) {
            return link;
        }
        Link middle = partition(link);
        Link secondHalf = middle.next;
        middle.next = null;

        return merge(domergeSort(link), domergeSort(secondHalf));

    }

    private Link partition(Link link) {
        if (link == null)
            return null;

        Link slowptr = link;
        Link fastptr = link.next;
        while (fastptr != null && fastptr.next != null) {
            slowptr = slowptr.next;
            fastptr = fastptr.next.next;
        }
        return slowptr;
    }

    private Link merge(Link m1, Link m2) {
        Link temp = new Link();                      //some dummy data
        Link mergehead = temp;
        while (m1 != null && m2 != null) {

            if (m1.data < m2.data) {
                temp.next = m1;
                m1 = m1.next;
            } else {
                temp.next = m2;
                m2 = m2.next;
            }
            temp = temp.next;
        }
        temp.next = (m1 == null) ? m2 : m1;
        return mergehead.next;
    }
}

