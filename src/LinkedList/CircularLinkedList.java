package LinkedList;


class CircularLinkedList {            //last node always connected to first node

    Link current;                     //Here take current pointer unlike other problem takes first/last
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
        //cl.deleteAtSpot();
        //cl.delete();
        //cl.search(6);
        //System.out.println("deleting "+cl.delete());
        //System.out.println("deleting "+cl.delete());
        cl.step_for_display();
        cl.step_for_display();
        cl.step_for_display();
        cl.step_for_display();
        //cl.step_for_display();

    }


    public void insert(int value) {                          //pointer at new element inserted
        //current.next=current;
        Link newLink = new Link(value);
        if (current == null) {                               //inserting at first
            current = newLink;
            current.next = current;
        } else {

            newLink.next = current.next;
            current.next = newLink;
            previous = current;
            current = newLink;
        }

    }

    public int delete() {                  //assume non empty LinkedList,it will delete next element of current
        int data = current.next.data;
        if (current.next == current) {          //only one element
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
        if (current.next == current) {          //only one element
            current = null;
            previous = null;
            return data;
        } else {
            previous.next = current.next;
            current = current.next;
        }
        return data;
    }

    public void step_for_display() {                   //infinte Loop as there is no size concept in LinkedList, so step by step
        if (current == null) {
            System.out.println("no element in Linked List");
        } else {
            System.out.println("step" + ++count + ": " + current.data);
            current = current.next;
        }
    }

    public void search(int key) {

        int counter = 0;
        while (current.data != key) {
            if (counter == 20)
                break;
            //System.out.println("hi, currentdata   "+current.data +" key "+key);
            current = current.next;
            counter++;
        }
        if (current.data == key)
            System.out.println("found");
        else
            System.out.println("cant found after 20 search");

    }

    public class Link {
        int data;
        Link next;

        Link(int data) {
            this.data = data;
            next = null;
        }

    }


}

