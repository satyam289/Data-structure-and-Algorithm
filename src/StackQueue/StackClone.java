package StackQueue;
import java.util.Stack;

class StackClone {

    // First revert the srcStack then put back into destination stack
    // O(n2)
    public static Stack<Integer> clone(Stack<Integer> srcStack){

        if(srcStack.isEmpty())
          return null;

        Stack<Integer> destStack = new Stack<Integer>();
        int count = 1;  // Indicate completed reverse value
        int size = srcStack.size();
        
        while(count < size){
            int temp = (int) srcStack.pop();
            int ctr = size - count;
            while(ctr >  0){
               destStack.push(srcStack.pop());
               ctr--;
            }
            srcStack.push(temp);
            while(!destStack.isEmpty()){ 
                srcStack.push(destStack.pop());
            }
            count ++;
        }
        while(!srcStack.isEmpty()){  
            destStack.push(srcStack.pop());
        }
        return destStack;
    }
    

    private static class StackLinkedListImpl {

        private class StackNode{
            private int data;
            StackNode next;
            public StackNode(int data){
                this.data = data;
                this.next = null;
            }
        }

        public StackNode top;
        int SizeCount = 0;
        public void push(int data){
           if(top == null){
               top = new StackNode(data);
           }else{
               StackNode newNode = new StackNode(data);
               newNode.next = top;
               top = newNode;
           }
           SizeCount++;
        }

        public int pop(){
            StackNode temp = top;
            top = top.next;
            SizeCount--;
            return temp.data;
        }

        public void reverse(){
            StackNode curr = top;
            StackNode pre = null;
            
            while(curr != null){
                StackNode temp = curr.next;
                curr.next = pre;
                pre = curr;
                curr = temp;
            }
           top = pre;
        }

        public boolean isEmpty() {
           return SizeCount == 0;
        }

        public void display()
        {
            StackNode s = this.top;
            while (s != null) {
                System.out.print(s.data + " ");
                s = s.next;
            }
            System.out.println();
        }  
    }

    public static void main(String [] args){
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        Stack<Integer> clonedstacked = clone(stack);
        while(!clonedstacked.isEmpty()){
            System.out.print(clonedstacked.pop() + " ");
        }
        System.out.println("");

        //O(n) Make stack class linked List impl, reverse the stack, now pop the element to cloned one
        StackLinkedListImpl stackNode  = new StackLinkedListImpl();
        stackNode.push(1);
        stackNode.push(2);
        stackNode.push(3);
        stackNode.push(4);
        stackNode.reverse();
        StackLinkedListImpl ClonedstackNode  = new StackLinkedListImpl();
        while(!stackNode.isEmpty()){
            ClonedstackNode.push(stackNode.pop());
        }
        ClonedstackNode.display();
    }
}