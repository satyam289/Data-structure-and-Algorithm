package DynamicProgramming;

public class TowerOfHonai {

    private static void towerOfHonai(int no_of_disk, String from, String to, String between) {
        if (no_of_disk == 1) {
            System.out.println("Disk 1 is move from " + from + " to " + to);
            return;
        }
        towerOfHonai(no_of_disk - 1, from, between, to);
        System.out.println("Disk " + no_of_disk + " is moved from " + from + " to " + to);
        towerOfHonai(no_of_disk - 1, between, to, from);
    }

    public static void main(String[] args) {
        towerOfHonai(3, "A", "B", "C");
    }
}
