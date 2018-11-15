package Miscellaneous;

public class CircularQ {
    private static final int defaultsize = 10;
    Object[] a;
    int front;
    int rear;
    int size;
    int count = 0;

    public CircularQ() {
        a = new Object[defaultsize];
        front = 0;
        rear = 0;
        size = 5;
    }

    CircularQ(int n) {
        a = new Object[n];
        front = 0;
        rear = 0;
        this.size = n;
    }

    public void insert(Object value) {
        if (count == size)
            throw new RuntimeException(" stack overflows");
        a[rear] = value;

        rear = (rear + 1) % size;
        //System.out.println("rear "+rear);
        count++;
    }

    public Object delete() {
        if (count == 0)
            throw new RuntimeException(" stack underflows");
        Object temp = a[front];
        front = (front + 1) % size;
        //System.out.println("front "+front);
        count--;
        return temp;
    }

    @Override
    public String toString() {
        String output = "[";
        for (int i = 0; i < count; i++) {

            output += a[(front + i) % size];
            if (i < count - 1)
                output += ',';
        }
        output += "]";
        return output;

    }

    public boolean isEmpty() {
        if (count == 0) {
            return true;
        } else return false;
    }

    public void display() {
        if (front < rear) {
            for (int i = front; i < rear; i++) {
                System.out.println(a[i]);
            }
        } else {
            for (int i = front; i < size; i++)
                System.out.println(a[i]);
            for (int i = 0; i < rear; i++)
                System.out.println(a[i]);
        }
    }

    public static void main(String[] args) {
        CircularQ cq = new CircularQ(3);
        cq.insert(3);
        cq.insert(4);
        cq.insert(5);
        cq.delete();
        cq.delete();
        //cq.delete();
        cq.insert(9);
        cq.insert(2);
        //cq.display();
        System.out.println(cq);
    }

}
