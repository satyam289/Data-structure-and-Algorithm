package Miscellaneous;

import java.util.Stack;

public class ArithemeticExpression {                      //changing to post-fix

    static StringBuffer output = new StringBuffer();
    Stack s;
    Stack finalstack;

    ArithemeticExpression() {
        s = new Stack();
        finalstack = new Stack();
    }


    public static void main(String[] args) {

        ArithemeticExpression a = new ArithemeticExpression();
        a.postfix("3*(4+5)-6/(1+2)");
        System.out.println(output);
        System.out.println(a.getResult(output.toString()));
        //System.out.println(a.getResult("345+*612+/-"));

    }

    public void postfix(String st) {


        char[] ch = st.toCharArray();
        for (int i = 0; i < ch.length; i++) {

            char ch1 = ch[i];

            switch (ch1) {
                case '+':
                case '-':
                    getOperation(ch1, 1);
                    break;

                case '*':
                    getOperation(ch1, 2);
                    break;
                case '/':
                    getOperation(ch1, 2);
                    break;

                case '(':
                    getOperation(ch1, 3);
                    break;

                case ')':
                    getOperation2();
                    break;
                default:
                    output.append(ch1);
                    break;
            }
        }
        while (!s.isEmpty())
            output.append(s.pop());

    } //end of postfix

    public void getOperation(char ch1, int priorityCurrent) {


        if (s.isEmpty()) {
            s.push(ch1);
        } else if (ch1 == '(') {
            s.push(ch1);
        } else {

            char ch3 = (char) s.peek();
            if (ch3 == '(') {                                           //simply push the value after seeing (
                s.push(ch1);

            } else {
                int priorityLocal = 0;
                if (ch3 == '+' || ch3 == '-')
                    priorityLocal = 1;
                else if (ch3 == '*' || ch3 == '%')
                    priorityLocal = 2;
                if (priorityCurrent > priorityLocal) {

                    s.push(ch1);
                } else {

                    output.append(ch3);                       // if priorityLocal > priorityCurrent , pop the old value and append to result and push the new value in stack
                    s.pop();
                    s.push(ch1);
                }
            }
        }

    }

    public void getOperation2() {

        char ch = (char) s.pop();                         //pop all value till we find '('and append to output
        while (ch != '(') {
            output.append(ch);
            ch = (char) s.pop();
        }

    }


    public Object getResult(String post) {  //doing the calculation


        for (int i = 0; i < post.length(); i++) {
            char ch = post.charAt(i);
            if (ch > '0' && ch < '9') {
                finalstack.push((int) (ch - '0'));  //difference of two character then converting int

            } else {
                int result = 0;

                // char cl=(char) finalstack.pop();
                // System.out.println("checking "+((int)cl-48));

                int num1 = ((int) finalstack.pop());    //pop the last two value from stack
                int num2 = ((int) finalstack.pop());
                System.out.println("num1: " + num1 + " num2: " + num2);
                switch (ch) {
                    case '+':
                        result = num1 + num2;
                        System.out.println("addition " + result);
                        finalstack.push(result);      //push the resultant in stack
                        break;
                    case '-':
                        result = num2 - num1;
                        System.out.println("subtraction" + result);
                        finalstack.push(result);
                        break;
                    case '*':
                        result = num1 * num2;
                        System.out.println("multiplication" + result);
                        finalstack.push(result);
                        break;
                    case '/':
                        result = num1 % num2;
                        System.out.println("division" + result);
                        finalstack.push(result);
                        break;
                    default:
                        finalstack.push(0);
                        break;

                }

            }

        }
        return finalstack.pop();              //display the last display value in the stack

    }

}
 
 