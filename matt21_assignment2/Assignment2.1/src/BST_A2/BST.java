package BST_A2;

public class BST implements BST_Interface {
	public BST_Node root;
	int size;
	private BST blank;

	public BST() {

		size = 0;
		root = null;
	}

	@Override
	// used for testing, please leave as is
	public BST_Node getRoot() {

		return root;
	}

	@Override
	public boolean insert(String s) {
		if (root == null) {
			size = 1;
			root = new BST_Node(s);

			return true;
		}
		if (root.containsNode(root, s)) {
			return false;
		}

		return root.insertNode(root, s);
	}

	@Override
	public boolean remove(String s) {
		if (root == null) {
			return false;
		} else if (!contains(s)) {
			return false;
		} else if (root.left == null && root.right == null) {
			root = null;
			return true;
		}
		if (root.data.equals(s)) {

			root = root.removeRoot(root);

			return true;

		}

		return root.removeNode(root, s);
	}

	@Override
	public String findMin() {
		if (size == 0) {
			return null;
		}
		return root.findMin(root).data;
	}

	@Override
	public String findMax() {
		if (size == 0) {
			return null;
		}
		return root.findMax(root).data;
	}

	@Override
	public boolean empty() {
		if (root == null) {
			return true;

		}
		return false;
	}

	@Override
	public boolean contains(String s) {
		if (size == 0) {
			return false;
		}
		return root.containsNode(root, s);
	}

	@Override
	public int size() {
		if (root != null) {
			size = root.getSize(root) + 1;
			return size;
		} else {

			size = 0;
			return size;
		}
	}

	@Override
	public int height() {
		if (size == 0 && root == null) {
			return -1;
		} else {

			return root.getHeight(root);
		}
	}

}