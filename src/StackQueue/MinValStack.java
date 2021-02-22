package StackQueue;

import java.util.Stack;

class MinValStack {

    Stack<Integer> stack = new Stack<>();
    int min;

    public void push(int x) {
      if(stack.isEmpty()){
            stack.push(x);
            min = x;
            return;
       }
       if (x > min) {
           stack.push(x);
       } else {   
           stack.push((2 * x) - min); // pushing value
           min = x;  //actual val
        }
    }

    public int pop() {
        if(stack.isEmpty()) {
            return -1;
        } else {
            int popVal = stack.pop();
            int actualVal = popVal;
            if(popVal <= min) {    //getting value
                actualVal = min;  //actual value
                min = (2 * min) - popVal;  // new min as per insertion formula
            }
            return actualVal;
        }
    }

    public int getMin() {
        if(stack.isEmpty()) {
            return -1;
        }
        return min;
    }

    public int peek() {
        if(stack.isEmpty()) {
            return -1;
        }
        int val = stack.peek();
        if(val > min) {   // actual val is top of stack
            return val;
        }else{
            return min;   // actual val is minimum
        }
    }
    
    public static void main(String[] args) 
    { 
        MinValStack s = new MinValStack(); 
        s.push(3);  // [3]
        s.push(5);  // [3 5]  Min 3
        System.out.println("The min value : "+s.getMin()); 
        s.push(2);  // [3 5 1] Min 2
        s.push(1);  // [3 5 1 1] Min 1
        System.out.println("The min value : "+s.getMin()); 
        System.out.println("The popped value : "+s.pop());  // [3 5 1]  Min = 2
        System.out.println("The min value : "+s.getMin()); 
        System.out.println("The popped value : "+s.pop()); // [3 5] Min = 3
        System.out.println("The peek value : "+s.peek()); 
    }
}