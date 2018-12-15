package StackQueue;

public class Queue {

    int[] arr;
    int maxsize;
    int size = 0;
    int front = 0;
    int rear = -1;

    public Queue(int size) {
        this.maxsize = size;
        arr = new int[size];
    }

    public void add(int value) {
        if (size == maxsize)
            System.out.println("stack over-flows");
        else {
            rear = (rear + 1) % maxsize;
            arr[rear] = value;
            size++;
        }
    }

    public int remove() {
        if (size == 0) {
            System.out.println("stack under-flows");
            return 0;
        } else {
            size--;
            int temp = arr[front];
            front = (front + 1) % maxsize;
            return temp;
        }
    }

    public boolean isEmpty() {
        if (size == 0)
            return true;
        else
            return false;
    }

    public String toString() {
        String output = "[";
        for (int i = 0; i < size; i++) {
            output += arr[(front + i) % maxsize];
            if (i < size - 1)
                output += ",";
        }
        output += "]";
        return output;
    }

}