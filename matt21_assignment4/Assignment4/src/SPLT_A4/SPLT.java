package SPLT_A4;

public class SPLT implements SPLT_Interface {
	private BST_Node root;
	private int size;

	public SPLT() {
		this.size = 0;
	}

	public BST_Node getRoot() { // please keep this in here! I need your root
								// node to test your tree!
		return root;
	}

	@Override

	public void insert(String s) {
		if (this.root == null) {
			this.root = new BST_Node(s);
		} else {
			this.root = this.root.insert(s);
		}
		if (this.root.justMade) {
			this.size += 1;
			this.root.justMade = false;
		}
	}

	@Override
	public void remove(String s) {
		if ((this.root == null) || (!contains(s))) {
			return;
		}
		if (this.root.left == null) {
			this.root = this.root.right;
		} else {
			BST_Node right = this.root.right;
			
			if (right == null) {
				this.root = this.root.left;
			} else {
				this.root = this.root.left.findMax();
			}
			if (right != null) {
				this.root.right = right;
			}
			if (this.root.right != null) {
				this.root.right.par = this.root;
			}
		}
		if (this.root != null) {
			this.root.par = null;
		}
		this.size -= 1;
	}

	@Override
	public String findMin() {
		if (this.root == null) {
			return null;
		}
		this.root = this.root.findMin();
		return this.root.data;
	}

	public String findMax() {
		if (this.root == null) {
			return null;
		}
		this.root = this.root.findMax();
		return this.root.data;
	}

	@Override
	public boolean empty() {
		// TODO Auto-generated method stub
		if (size == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean contains(String s) {
		if (this.root == null) {
			return false;
		}
		this.root = this.root.containsNode(s);
		return this.root.data.equals(s);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public int height() {
		if (empty()) {
			return -1;
		} else {
			return root.getHeight();
		}
	}

}