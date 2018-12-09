package LinkedList;

class Link2D {
	int data;
	Link2D rowsLink;
	Link2D columnsLink;

	Link2D(int data) {
		this.data = data;
		rowsLink = null;
		columnsLink = null;
	}
}

class Matrix_LinkedList {
	Link2D first;

	int rowN;
	int colN;

	public static void main(String args[]) {
		Matrix_LinkedList m = new Matrix_LinkedList();
		m.creatMatrix(10, 10);
		m.insert(500, 5, 5);
		m.display();
	}

	public void creatMatrix(int row, int col) {
		this.rowN = row;
		this.colN = col;
		Link2D newFirst = new Link2D(0);
		first = newFirst;
		Link2D current = first;
		Link2D temp;
		for (int i = 0; i < row; i++) {
			temp = current;

			for (int j = 0; j < col; j++) {
				Link2D colNew = new Link2D(0);
				temp.columnsLink = colNew;
				temp = temp.columnsLink;
			}
			Link2D rowNew = new Link2D(0);
			current.rowsLink = rowNew;
			current = current.rowsLink;

		}
	}

	public void insert(int data, int row, int col) {

		if (row < 0 && col < 0 && row < rowN && col < colN) {
			throw new RuntimeException("Wrong Row/Column entered");
		}
		Link2D current = first;

		for (int i = 0; i < row; i++) {
			// System.out.println("inside row");
			current = current.rowsLink;

		}

		for (int j = 0; j < col; j++) {
			// System.out.println("inside col");
			current = current.columnsLink;
		}

		current.data = data;

	}

	public void display() {
		Link2D current = first;
		Link2D temp;
		while (current != null) {
			temp = current;

			while (temp.columnsLink != null) {
				System.out.print(temp.data + "    ");
				temp = temp.columnsLink;
			}
			System.out.println("");
			current = current.rowsLink;
		}
	}

}