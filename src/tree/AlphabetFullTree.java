package tree;



/* Again, start with the tree.java program and make a tree from characters typed
by the user. This time, make a complete tree�one that is completely full
except possibly on the right end of the bottom row. The characters should be
ordered from the top down and from left to right along each row, as if writing
a letter on a pyramid. (This arrangement does not correspond to any of the
three traversals we discussed in this chapter.) */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class AlphabetFullTree {
    private static class AbcNode {
        String sData;
        AbcNode leftChild;
        AbcNode rightChild;

        AbcNode() {

        }
    }

    private AbcNode root;            //first node of tree
    private int length;
    private String initString;

    public AlphabetFullTree(String initString) {
        this.initString = initString;
        length = initString.length();
        root = fillNode(1);
    }

    private AbcNode fillNode(int location) {
        if (location > length)                                                                  //before (location > length) all field linked to parent-child
            return null;
        else {
            AbcNode temp = new AbcNode();
            temp.sData = Character.toString(initString.charAt(location - 1));                       //previous value
            temp.leftChild = fillNode(2 * location);                                                //its nth child of nth node
            temp.rightChild = fillNode(2 * location + 1);
            return temp;
        }

    }

    public void traverse(int traverseType) {
        switch (traverseType) {
            case 1:
                System.out.print("\nPreorder traversal: ");
                preOrder(root);
                break;
            case 2:
                System.out.print("\nInorder traversal: ");
                inOrder(root);
                break;
            case 3:
                System.out.print("\nPostorder traversal: ");
                postOrder(root);
                break;
        }
        System.out.println("");
    }

    private void preOrder(AbcNode localRoot) {
        if (localRoot != null) {
            System.out.print(localRoot.sData + " ");
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }

    private void inOrder(AbcNode localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.leftChild);
            System.out.print(localRoot.sData + " ");
            inOrder(localRoot.rightChild);
        }
    }

    private void postOrder(AbcNode localRoot) {
        if (localRoot != null) {
            postOrder(localRoot.leftChild);
            postOrder(localRoot.rightChild);
            System.out.print(localRoot.sData + " ");
        }
    }

    public void displayTree() {
        Stack<AbcNode> globalStack = new Stack<>();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println(
                ".......................................................");
        while (!isRowEmpty) {
            Stack<AbcNode> localStack = new Stack<>();
            isRowEmpty = true;

            for (int j = 0; j < nBlanks; j++)
                System.out.print(" ");

            while (!globalStack.isEmpty()) {
                AbcNode temp = globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.sData);
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);

                    if (temp.leftChild != null ||
                            temp.rightChild != null)
                        isRowEmpty = false;
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++)
                    System.out.print(" ");
            } //end while globalStack not empty
            System.out.println();
            nBlanks /= 2;
            while (!localStack.isEmpty())
                globalStack.push(localStack.pop());
        } //end while isRowEmpty is false
        System.out.println(
                ".......................................................");
    }

    public static void main(String[] args) throws IOException {
        int value;
        String initString = "abcdefghijklmo";
        AlphabetFullTree theTree = new AlphabetFullTree(initString);

        while (true) {
            System.out.print("Enter first letter of show"
                    + " or traverse: ");
            int choice = getChar();
            switch (choice) {
                case 's':
                    theTree.displayTree();
                    break;
                case 't':
                    System.out.print("Enter type 1, 2, or 3: ");
                    value = getInt();
                    theTree.traverse(value);
                    break;
                default:
                    System.out.print("Invalid entry!\n");
            }
        }
    }

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        return br.readLine();
    }

    public static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }

    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
}