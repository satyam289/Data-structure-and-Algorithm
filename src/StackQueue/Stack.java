
package StackQueue;

public class Stack {
    int[] arr;
    int top;
    int size;

    public Stack(int size) {
        this.size = size;
        arr = new int[size];
        top = -1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int value) {
        if (top == size)
            System.out.print("stack Overflows");
        else {
            arr[++top] = value;
        }
    }

    public int pop() {
        if (isEmpty()) {
            System.out.print("stack UnderFlows");
            return 0;
        } else {
            return arr[top--];
        }
    }

    public int peek() {
        return arr[top];
    }
}
