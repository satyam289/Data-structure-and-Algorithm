package tree;


public class Tree234 {

    static class Data {
        int item;

        Data(int item) {
            this.item = item;
        }
    }

    class Node234 {
        int maxSize = 4;
        Node234 parent;
        Node234[] childArray = new Node234[maxSize];
        Data[] dataArray = new Data[maxSize - 1];
        int size;

        public int find(int item2) {
            for (int i = 0; i < maxSize - 2; i++) {
                if (dataArray[i] == null)
                    break;
                else if (dataArray[i].item == item2) {
                    return i;
                }
            }
            return -1;
        }

        public int insert(int item2) {   //assume non empty node
            size++;
            Data data = new Data(item2);
            for (int i = maxSize - 2; i >= 0; i--) {      //start from back side

                if (dataArray[i] == null)                //backside might be null(non empty array)
                    continue;                        //skip
                else {
                    if (dataArray[i].item > item2)
                        dataArray[i + 1] = dataArray[i];
                    else {
                        dataArray[i + 1] = data;
                        return i + 1;
                    }
                }
            }
            dataArray[0] = data;
            return 0;
        }

        public void ConnectChild(int childnum, Node234 node) {
            childArray[childnum] = node;
            if (node != null)
                node.parent = this;
        }

        public Node234 disconnectChild(int childnum) {
            Node234 temp = childArray[childnum];
            childArray[childnum] = null;
            return temp;
        }

        public void display() {
            for (int i = 0; i < size; i++) {
                System.out.print(" / " + dataArray[i].item);
            }
        }

        public boolean isLeafNode() {
            return childArray[0] == null;
        }

        public boolean isFull() {
            return size == maxSize - 1;
        }

        public int remove() {
            Data temp = dataArray[size - 1];
            dataArray[size - 1] = null;
            size--;
            return temp.item;
        }
    }

    Node234 root = new Node234();

    public void insert(int value) {
        Node234 current = root;
        while (true) {
            if (current.isFull()) {
                split(current);
                current = current.parent;     //assume parent is not full

                current = getNextChild(current, value);
            } else if (current.isLeafNode())
                break;
            else {
                current = getNextChild(current, value);
            }
        }
        current.insert(value);
    }

    public void split(Node234 node) {

        int value2, value3;
        Node234 child2, child3, parent;
        value3 = node.remove();
        value2 = node.remove();
        child3 = node.disconnectChild(2);
        child2 = node.disconnectChild(3);

        if (node == root) {
            root = new Node234();
            root.ConnectChild(0, node);
            parent = root;
        } else
            parent = node.parent;
        int insertionpoint = parent.insert(value2);
        for (int j = parent.size - 1; j > insertionpoint; j--) {
            Node234 temp = parent.disconnectChild(j);
            parent.ConnectChild(j + 1, temp);
        }

        Node234 newRight = new Node234();
        parent.ConnectChild(insertionpoint + 1, newRight);
        newRight.insert(value3);
        newRight.ConnectChild(0, child2);
        newRight.ConnectChild(1, child3);
    }

    public Node234 getNextChild(Node234 current, int value) {
        // System.out.println(current.size);
        for (int i = 0; i < current.size; i++) {
            if (value <= current.dataArray[i].item)
                return current.childArray[i];
        }
        return current.childArray[current.size];
    }


    public int find(int item) {
        Node234 current = root;
        int datanumber;
        while (true) {
            if ((datanumber = current.find(item)) != -1) {
                return datanumber;
            } else if (current.isLeafNode())
                return -1;
            else
                current = getNextChild(current, item);
        }
    }

    public void display() {
        recdisplay(root, 0, 0);
    }

    public void recdisplay(Node234 node, int level, int childNumber) {
        System.out.print("display for level: " + level + " childNumber: " + childNumber + "          ");
        node.display();
        for (int i = 0; i < node.size + 1; i++) {
            Node234 n = node.childArray[i];
            if (n != null) {
                System.out.println("");
                recdisplay(n, level + 1, i);
            } else
                return;
        }
    }

    public int minValue() {
        Node234 current = root;
        while (true) {
            if (current.isLeafNode()) {
                return current.dataArray[0].item;
            } else
                current = getNextChild(current, current.dataArray[0].item);
        }
    }

    public void inOrder(Node234 node) {
        if (node == null) {
            return;
        } else {
            node.display();
            if (node.childArray[0] != null) {
                inOrder(node.childArray[0]);
                //node.display();
            }
            if (node.childArray[1] != null) {
                inOrder(node.childArray[1]);
                // node.display();
            }
            if (node.childArray[2] != null) {
                inOrder(node.childArray[2]);
                // node.display();
            }
            //node.display();
        }
    }


    public void sortTraverse(int[] theArray) {

        for (int value : theArray) {
            insert(value);
        }
        for (int i = 0; i < theArray.length; i++) {
            theArray[i] = 0;
        }
        int i = 0;
        recSortTraverse(root, theArray, i);
        for (int value : theArray)
            System.out.print(value + " ");
    }

    private int recSortTraverse(Node234 curNode, int[] theArray, int i) {
        //if it's a leaf, spew it all out
        if (curNode.isLeafNode()) {
            for (int j = 0; j < curNode.size; j++) {
                theArray[i++] = curNode.dataArray[j].item;
            }
            return i;
        }
        //otherwise get child 0, print item 0, get child 1, print item 1...
        else {
            for (int j = 0; j < curNode.size + 1; j++) {
                i = recSortTraverse(curNode.childArray[j], theArray, i);
                if (j < curNode.size) {
                    theArray[i++] = curNode.dataArray[j].item;
                }
            }
            return i;
        }
    }


    public static void main(String[] args) {
        Tree234 t = new Tree234();
	    /* t.insert(5);
	     t.insert(3);
	     t.insert(6);
	     t.insert(4);
	     t.insert(7);
	     t.insert(8);
	     t.insert(1);
	     t.insert(9);
	     t.insert(11);
	     t.insert(12);
	     t.insert(0);
	    if(t.find(9)!=-1)
	    	 System.out.println("found at "+ t.find(9));
	     else System.out.println("Not Found");
	     t.display();
	     System.out.println("");
	     //System.out.println("Minimum value: "+t.minValue());
         t.inOrder(t.root);*/
        int[] a = {4, 9, 1, 5, 10, 11};
        t.sortTraverse(a);
    }
}
