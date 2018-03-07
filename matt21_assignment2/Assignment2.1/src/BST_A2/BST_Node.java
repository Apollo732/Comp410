package BST_A2;

public class BST_Node {
	String data;
	BST_Node root;
	BST_Node left;
	BST_Node right;

	BST_Node past;
	int size = 1;

	BST_Node(String data) {
		this.data = data;
	}

	// --- used for testing ----------------------------------------------
	//
	// leave these 3 methods in, as is

	public String getData() {
		return data;
	}

	public BST_Node getLeft() {
		return left;
	}

	public BST_Node getRight() {
		return right;
	}

	// --- end used for testing -------------------------------------------

	// --- fill in these methods ------------------------------------------
	//
	// at the moment, they are stubs returning false
	// or some appropriate "fake" value
	//
	// you make them work properly
	// add the meat of correct implementation logic to them

	// you MAY change the signatures if you wish...
	// make the take more or different parameters
	// have them return different types
	//
	// you may use recursive or iterative implementations

	public boolean containsNode(BST_Node curr, String s) {

		if (curr == null) {
			return false;
		}
		int val = curr.data.compareTo(s);

		if (val < 0) {
			return containsNode(curr.right, s);
		} else if (val > 0) {
			return containsNode(curr.left, s);
		} else if (val == 0) {
			return true;
		} else {
			return false;
		}

	}

	public BST_Node removeRoot(BST_Node root) {
		BST_Node temp = new BST_Node("");
		temp.right = root;
		removeNode(temp, root.data);
		root = temp.right;

		return root;

	}

	public boolean removeNode(BST_Node curr, String s) {

		int val = s.compareTo(curr.data);
		if (val < 0) {
			past = curr;
			removeNode(curr.left, s);

		} else if (val > 0) {
			past = curr;
			removeNode(curr.right, s);
		} else {

			if (curr.right == null && curr.left == null) {

				if (past.right == curr) {

					past.right = null;
				} else {
					past.left = null;
				}
			} else if (curr.right != null && curr.left == null) {
				if (past.right == curr) {
					past.right = curr.right;
				} else {
					past.left = curr.right;
				}

			} else if (curr.right == null && curr.left != null) {
				if (past.left == curr) {
					past.left = curr.left;
				} else {
					past.right = curr.left;
				}

			} else {

				if (past.right == curr) {
					if (findMin(curr.right).data != curr.right.data) {
						curr.data = findMin(curr.right).data;
						removeNode(curr.right, curr.data);
					} else {
						curr.data = findMin(curr.right).data;
						curr.right = null;
					}
				} else if (past.left == curr) {
					if (findMin(curr.right).data != curr.right.data) {
						curr.data = findMin(curr.right).data;
						past.left = curr;

						removeNode(curr.right, curr.data);
					} else {
						curr.data = findMin(curr.right).data;
						curr.right = null;
					}
				}

			}

		}

		return true;
	}

	public boolean insertNode(BST_Node parent, String s) {

		BST_Node curr = new BST_Node(s);
		int val = parent.data.compareTo(s);

		if (val > 0) {
			if (parent.left == null) {
				parent.left = curr;

			} else {
				insertNode(parent.left, s);

			}

		} else if (val < 0) {
			if (parent.right == null) {
				parent.right = curr;

			} else {
				insertNode(parent.right, s);
			}
		}

		return true;
	}

	public BST_Node findMin(BST_Node find) {
		if (find.left == null) {
			return find;
		}

		else {
			return findMin(find.left);
		}
	}

	public BST_Node findMax(BST_Node find) {

		if (find.right == null) {
			return find;
		} else {
			return findMax(find.right);
		}
	}

	public int getHeight(BST_Node curr) {
		int left = 0;
		int right = 0;
		if (curr.left != null) {
			left = 1 + getHeight(curr.left);
		}
		if (curr.right != null) {
			right = 1 + getHeight(curr.right);
		}

		return left > right ? left : right;

	}

	public int getSize(BST_Node curr) {
		int left = 0;
		int right = 0;
		if (curr.left != null) {
			left = 1 + getSize(curr.left);
		}
		if (curr.right != null) {
			right = 1 + getSize(curr.right);
		}

		return left + right;

	}

	public String toString() {
		return "Data: " + this.data + ", Left: " + ((this.left != null) ? left.data : "null") + ",Right: "
				+ ((this.right != null) ? right.data : "null");
	}

}