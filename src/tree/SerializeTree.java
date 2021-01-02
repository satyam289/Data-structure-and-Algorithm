package tree;

import java.io.*;

public class SerializeTree {

    private static class Tree {
        int data;
        Tree left;
        Tree right;

        public Tree(int data) {
            this.data = data;
            left = right = null;
        }
    }

    private static void serialize(Tree root, FileWriter fw) throws IOException {
        if (root == null) {
            fw.write("-1 ");
            return;
        }
        fw.write(root.data + " ");
        serialize(root.left, fw);
        serialize(root.right, fw);
    }

    private static int deserialize(Tree root, String[] st, int offset) throws IOException {
        int data = Integer.parseInt(st[offset]);
        if (data == -1) {
            return offset;
        }
        root = new Tree(data);
        int leftIndex = deserialize(root.left, st, ++offset);
        return deserialize(root.right, st, leftIndex + 1);
    }

    public static void main(String[] args) throws IOException {
        Tree root = new Tree(20);
        root.left = new Tree(8);
        root.right = new Tree(22);
        root.left.left = new Tree(4);
        root.left.right = new Tree(12);
        root.left.right.left = new Tree(10);
        root.left.right.right = new Tree(14);
        File file = new File("F:/abc.txt");
        Tree newNode = null;
        FileWriter fw = null;
        BufferedReader br = null;
        try {
            fw = new FileWriter(file);
            serialize(root, fw);
            fw.close();
            br = new BufferedReader(new FileReader(file));
            deserialize(newNode, br.readLine().split(" "), 0);
        } catch (IOException e) {
            System.out.println(" IOE Exception :: message " + e.getMessage());
        } finally {
            if (fw != null)
                fw.close();
            if (br != null)
                br.close();
        }
    }
}
