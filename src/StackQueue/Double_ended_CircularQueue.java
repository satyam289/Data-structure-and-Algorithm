package StackQueue;

//acts as a single circular queue whose both side (front+end) opens for insertion and deletion in single array
public class Double_ended_CircularQueue {

       int[] a;
       int front;
       int rear;
       int N;
       int size = 0;

       Double_ended_CircularQueue() {
              a = new int[5];
              front = 0;
              N = 5;
              rear = 0;
       }

       Double_ended_CircularQueue(int n) {
              a = new int[n];
              front = 0;
              N = n;
              rear = 0;
       }

       public static void main(String[] args) {
              Double_ended_CircularQueue de = new Double_ended_CircularQueue();
              de.insertLeft(4);
              de.insertLeft(7);
              de.removeLeft();
              de.insertRight(9);
              de.removeRight();
              System.out.println(de);

       }

       public void insertLeft(int value) {
              if (isFull())
                     throw new RuntimeException("stack is full");
              front = front == 0 ? N - 1 : front - 1;
              a[front] = value;
              size++;

       }

       public void insertRight(int value) {
              if (isFull())
                     throw new RuntimeException("Stack is full");
              a[rear] = value;
              rear = (rear + 1) % N;
              size++;
       }

       public int removeLeft() {
              if (isEmpty())
                     throw new RuntimeException("stack is Empty");
              int temp = a[front];
              front = (front + 1) % N;
              size--;
              return temp;

       }

       public int removeRight() {
              if (isEmpty())
                     throw new RuntimeException("stack is empty");
              rear = rear == 0 ? N - 1 : rear - 1;
              int temp = a[rear];
              rear = (rear + 1) % N;
              size--;
              return temp;
       }

       public boolean isEmpty() {
              if (size == 0)
                     return true;
              return false;
       }

       public boolean isFull() {
              if (size == N)
                     return true;
              return false;
       }

       public String toString() {
              String output = "[";
              for (int i = 0; i < size; i++) {
                     output += a[(front + i) % N];

                     if (i < size - 1)
                            output += ",";
              }
              output += "]";
              return output;
       }

}