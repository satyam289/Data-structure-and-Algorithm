package graph;

public class Heap {
    private Node[] heapArray;
    private int maxSize;
    private int currentSize;

    public Heap(int maxSize) {
        heapArray = new Node[maxSize];
        this.maxSize = maxSize;
        currentSize = 0;
    }

    public boolean insert(int key) {
        if (currentSize == maxSize)
            return false;
        heapArray[currentSize] = new Node(key);

        trickleUp(currentSize);
        currentSize++;
        return true;
    }

    public void trickleUp(int currentSize) {
        int parent = (currentSize - 1) / 2;
        Node temp = heapArray[currentSize];
        while (currentSize > 0 && temp.data > heapArray[parent].data) {
            heapArray[currentSize] = heapArray[parent];
            currentSize = parent;
            parent = (parent - 1) / 2;
        }
        heapArray[currentSize] = temp;
    }


    public Node remove() {
        Node root = heapArray[0];
        heapArray[0] = heapArray[currentSize - 1];
        --currentSize;
        trickleDown(0);
        return root;
    }

    public void trickleDown(int start) {
        int largerChild;
        Node temp = heapArray[start];
        while (start < currentSize / 2) {

            int leftChild = 2 * start + 1;
            int rightChild = leftChild + 1;

            if (rightChild < currentSize && heapArray[leftChild].data < heapArray[rightChild].data)
                largerChild = rightChild;
            else
                largerChild = leftChild;
            if (temp.data > heapArray[largerChild].data)
                break;
            heapArray[start] = heapArray[largerChild];
            start = largerChild;
        }
        heapArray[start] = temp;
    }

    public void displayHeap() {
        for (int i = 0; i < currentSize; i++)
            if (heapArray[i] != null)
                System.out.print(heapArray[i].data + "  ");
            else
                System.out.print("--");
        System.out.println("");
        int nBlank = 32;
        int column = 0;
        String dots = "...............................";
        System.out.println(dots + dots);
        int k = 0;
        int itemperRow = 1;
        while (k < currentSize) {
            if (column == 0) {
                for (int i = 0; i < nBlank; i++)
                    System.out.print(" ");
            }
            System.out.print(heapArray[k].data);
            k++;
            if (k == itemperRow) {
                itemperRow = itemperRow * 2 + 1;
                nBlank = nBlank / 2;
                column = 0;
                System.out.println("");

            } else {
                for (int i = 0; i < nBlank - 2; i++)
                    System.out.print(" ");
            }
        }
        System.out.println();
        System.out.println(dots + dots);
    }


    public static void main(String[] args) {
        Heap h = new Heap(20);
        h.insert(70);
        h.insert(90);
        h.insert(50);
        h.insert(20);
        h.insert(60);
        h.insert(100);
        h.insert(80);
        h.insert(30);
        h.insert(10);
        //h.remove();
        h.displayHeap();
    }

	class Node {
		int data;

		Node(int data) {
			this.data = data;
		}
	}
}
