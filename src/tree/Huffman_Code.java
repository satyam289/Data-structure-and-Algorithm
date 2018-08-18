package tree;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

public class Huffman_Code {

	class NodeH implements Comparable<NodeH> {
		char data;
		int number;
		NodeH left;
		NodeH right;
		NodeH parent;

		NodeH(char data, int number) {
			this.data = data;
			this.number = number;
		}

		NodeH(int number) {
			this.number = number;
			data = '\u0000';
		}

		@Override
		public int compareTo(NodeH o) {
			return this.number - o.number;
		}
	}

	String s;
	NodeH root;
	String Maincode;
	String[] codebox = new String[128];
	String encodedmessage = "";

	public void encoding() {
		char[] ch = s.toCharArray();
		ArrayList al = new ArrayList();
		for (int i = 0; i < ch.length; i++) {
			if (!al.contains(ch[i]))
				al.add(ch[i]);
		}
		int[] frequencyArray = new int[al.size()];
		for (int i = 0; i < frequencyArray.length; i++) {
			frequencyArray[i] = 0;
		}

		for (int i = 0; i < al.size(); i++) {
			char ch1 = (char) al.get(i);
			for (int j = 0; j < ch.length; j++) {
				if (ch1 == ch[j])
					frequencyArray[i]++;
			}
		}

		for (int i = 0; i < frequencyArray.length - 1; i++) {
			for (int j = 0; j < frequencyArray.length - 1; j++) {
				if (frequencyArray[j] > frequencyArray[j + 1]) {

					int temp = frequencyArray[j];
					frequencyArray[j] = frequencyArray[j + 1];
					frequencyArray[j + 1] = temp;

					char temp2 = (char) al.get(j);
					al.set(j, al.get(j + 1));
					al.set(j + 1, temp2);
				}
			}

		}

		/*
		 * for(int i=0;i<al.size();i++){ System.out.print(al.get(i) + " "); }
		 * 
		 * for(int i=0;i<frequencyArray.length;i++){ System.out.print(frequencyArray[i]
		 * + " "); }
		 */

		NodeH[] nodeArray = new NodeH[frequencyArray.length];
		for (int i = 0; i < nodeArray.length; i++) {
			nodeArray[i] = new NodeH((char) al.get(i), frequencyArray[i]);
		}

		PriorityQueue q = new PriorityQueue();
		for (int i = 0; i < nodeArray.length; i++) {
			q.add(nodeArray[i]);
		}
		// System.out.println(q.size());
		while (!(q.size() == 1)) {
			NodeH h1 = (NodeH) q.poll();
			NodeH h2 = (NodeH) q.poll();
			NodeH newNode = new NodeH((h1.number + h2.number));
			newNode.left = h1;
			newNode.right = h2;
			q.offer(newNode);
		}
		root = (NodeH) q.poll();
		// System.out.println(root.number);
		NodeH current = root;
		NodeH parent = root;
		NodeH grandparent = root;
		while (current != null) {
			grandparent = parent;
			parent = current;
			current = current.left;
		}
		String output = "";
		generatecodeTable(root, output);

		for (int i = 0; i < s.length(); i++) {
			encodedmessage += codebox[(int) s.charAt(i)] + "";
		}
		System.out.println();
	}

	public void generatecodeTable(NodeH root, String output) {
		if (root.data != '\u0000') {
			char ch = root.data;
			codebox[(int) ch] = output;
			output = output.substring(0, output.length() - 1);
		} else {
			if (root.left != null)
				;
			generatecodeTable(root.left, output + "1");
			if (root.right != null)
				generatecodeTable(root.right, output + "0");
		}

	}

	public void decode(String encodemessage) {
		NodeH current = root;
		String decoded = "";
		for (int i = 0; i < encodemessage.length(); i++) {

			if (encodemessage.charAt(i) == '1')
				current = current.left;
			else if (encodemessage.charAt(i) == '0')
				current = current.right;

			if (current.data != '\u0000') {
				decoded += current.data + "";
				current = root;
			}

		}
		System.out.println(decoded);
	}

	@SuppressWarnings("unchecked")
	public void display() {
		Stack<NodeH> globalStack = new Stack<NodeH>();
		boolean isRow = false;
		int pitem = 32;
		globalStack.push(root);
		System.out.println("----------------------------------------------------");
		while (!isRow) {
			isRow = true;
			Stack<NodeH> localStack = new Stack<NodeH>();
			for (int i = 0; i < pitem; i++)
				System.out.print(" ");

			while (globalStack.isEmpty() == false) {

				NodeH current = (NodeH) globalStack.pop();
				if (current == null) {
					System.out.print("--");
					localStack.push(null);
					localStack.push(null);

				} else {
					if (current.data == '\u0000')
						System.out.print(current.number);
					else
						System.out.print(current.data);

					localStack.push(current.left);
					localStack.push(current.right);
					isRow = false;
				}

				for (int i = 0; i < (pitem * 2 - 2); i++)
					System.out.print(" ");
			}
			while (localStack.isEmpty() == false) {
				globalStack.push(localStack.pop());
			}
			System.out.println("");
			pitem = pitem / 2;
		}
		System.out.println("--------------------------------------------------");

	}

	public static void main(String[] args) {
		Huffman_Code h = new Huffman_Code();
		h.s = "missccippi";
		h.encoding();
		System.out.println(h.encodedmessage);
		h.decode(h.encodedmessage);
		h.display();
	}
}
