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
            fw.write("-1");
            return;
        }
        System.out.print(root.data);
        fw.write(String.valueOf(root.data));
        serialize(root.left, fw);
        serialize(root.right, fw);
    }

    private static void deserialize(Tree root, FileReader fr) throws IOException {
        int data = fr.read();
        System.out.print(data);
        if (data == -1) {
            return;
        }
        root = new Tree(data);
        deserialize(root.left, fr);
        deserialize(root.right, fr);
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
        FileReader fr = null;
        try {
            fw = new FileWriter(file);
            serialize(root, fw);
            fr = new FileReader(file);
            char[] array = new char[50];
            System.out.println(fr.read(array));
            deserialize(null, fr);

        } catch (IOException e) {
            System.out.println("IOE Exception :: messgae " + e.getMessage());
        } finally {
            if (fw != null)
                fw.close();
            if (fr != null)
                fr.close();
        }
    }
}
