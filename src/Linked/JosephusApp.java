package Linked;

public class JosephusApp {
    CircularLinkedList circularList;
    int nPeople;
    int nCount;

    public JosephusApp(int nPeople, int nCount, int startPoint) {
        this.nPeople = nPeople;
        this.nCount = nCount;
        circularList = new CircularLinkedList();

        for (int i = 1; i <= nPeople; i++) {
            circularList.insert(i);
        }
        // moving to old pointer position (at first insertion point), check the display at last node
        circularList.stepWiseDisplay();
        for (int i = 1; i < startPoint; i++) { // moving to starting position
            circularList.stepWiseDisplay();
        }
    }

    //Function to find the only person left after one in every m-th node is killed in a circle of n nodes
    public void run() {
        for (int j = 0; j < nPeople - 1; j++) { // same number times as (no_of_people -1) times
             // // rotating till count-1 as delete operation works at next of current pointer, so stop before 1 count less
            for (int i = 0; i < nCount - 1; i++) { 
                circularList.stepWiseDisplay();
            }
            System.out.println("--- Killed----  " + circularList.delete());
            circularList.stepWiseDisplay(); // After deleting, moves to next node (left of dead person)

        }
        System.out.println("lucky Man is found at --->");
        circularList.stepWiseDisplay();
        System.out.println("done");
    }

    public static void main(String[] args) {
        JosephusApp josephusObj = new JosephusApp(10, 2, 1);
        josephusObj.run();
    }
}
