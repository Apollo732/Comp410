package SPLT_A4;

public class BST_Node {
	String data;
	BST_Node left;
	BST_Node right;
	BST_Node par; // parent...not necessarily required, but can be useful in
	// splay tree
	boolean justMade; // could be helpful if you change some of the return types
	// on your BST_Node insert.
	// I personally use it to indicate to my SPLT insert whether or not we
	// increment size.

	BST_Node(String data) {
		this.data = data;
		this.justMade = true;
	}

	BST_Node(String data, BST_Node left, BST_Node right, BST_Node par) {
		this.data = data;
		this.left = left;
		this.right = right;
		this.par = par;
		this.justMade = true;
	}

	// --- used for testing ----------------------------------------------
	//
	// leave these 3 methods in, as is (meaning also make sure they do in fact
	// return data,left,right respectively)

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

	// --- Some example methods that could be helpful
	// ------------------------------------------
	//
	// add the meat of correct implementation logic to them if you wish

	// you MAY change the signatures if you wish...names too (we will not grade
	// on delegation for this assignment)
	// make them take more or different parameters
	// have them return different types
	//
	// you may use recursive or iterative implementations

	public BST_Node containsNode(String s) {
		if (this.data.equals(s)) {
			splay(this);
			return this;
		}

		if (this.data.compareTo(s) < 0) {
			if (this.right == null) {
				splay(this);
				return this;
			}
			return this.right.containsNode(s);
		}
		if (this.data.compareTo(s) > 0) {
			if (this.left == null) {
				splay(this);
				return this;
			}
			return this.left.containsNode(s);
		}
		return null;
	} // note: I personally find it easiest to make this return a Node,(that
		// being the node splayed to root), you are however free to do what you
		// wish.

	public BST_Node insert(String s) {
		if (s.compareTo(this.data) < 0) {
			if (this.left != null) {
				return this.left.insert(s);
			}
			this.left = new BST_Node(s, null, null, this);
			BST_Node root = this.left;
			splay(this.left);
			return root;
		}
		if (s.compareTo(this.data) > 0) {
			if (this.right != null) {
				return this.right.insert(s);
			}
			this.right = new BST_Node(s, null, null, this);
			BST_Node root = this.right;
			splay(this.right);
			return root;
		}
		splay(this);
		return this;
	} // Really same logic as above note

	public boolean removeNode(String s) { // DIO
		if (this.data == null) {
			return false;
		}
		if (this.data.equals(s)) {
			if (this.left != null) {
				this.data = this.left.findMax().data;
				this.left.removeNode(this.data);
				
				if (this.left.data == null) {
				}
				this.left = null;
			} else if (this.right != null) {
				this.data = this.right.findMin().data;
				this.right.removeNode(this.data);
				if (this.right.data == null) {
					this.right = null;
				}
			} else
				this.data = null;
			return true;
		} else if (this.data.compareTo(s) > 0) {
			if (this.left == null) {
				return false;
			}
			if (!this.left.removeNode(s)) {
				return false;
			}
			if (this.left.data == null) {
				this.left = null;
			}
			return true;
		} else if (this.data.compareTo(s) < 0) {
			if (this.right == null) {
				return false;
			}
			if (!this.right.removeNode(s)) {
				return false;
			}
			if (this.right.data == null) {
				this.right = null;
			}
			return true;
		}
		return false;
	} // I personal do not use the removeNode internal method in my impl since
		// it is rather easily done in SPLT, feel free to try to delegate this
		// out, however we do not "remove" like we do in BST

	public BST_Node findMin() {
		if (this.left != null) {
			return this.left.findMin();
		}
		splay(this);
		return this;
	}

	public BST_Node findMax() {
		if (this.right != null) {
			return this.right.findMax();
		}
		splay(this);
		return this;
	}

	public int getHeight() {
		int left = 0;
		int right = 0;
		if (this.left != null) {
			left = 1 + this.left.getHeight();
		}
		if (this.right != null) {
			right = 1 + this.right.getHeight();
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

	private void splay(BST_Node pivot) {
		while (pivot.par != null) {
			BST_Node parent = pivot.par;
			BST_Node grandparent = parent.par;
			if (grandparent == null) { // Near the root
				if (pivot == parent.left) { // left of root
					rotateRight(pivot);
				} else { // right of root
					rotateLeft(pivot);
				}
			} else if (pivot == parent.left) { // left of parent
				if (parent == grandparent.left) { // far left of grandparent
					rotateRight(pivot);
					rotateRight(pivot);
				} else { // left, then right, of grandparent
					rotateRight(pivot);
					rotateLeft(pivot);
				}
			} else if (pivot == parent.right) { // right of parent
				if (parent == grandparent.right) { // far right of grandparent
					rotateLeft(pivot);
					rotateLeft(pivot);

				} else { // right, then left, of grandparent
					rotateLeft(pivot);
					rotateRight(pivot);
				}
			}
		}
	}

	private void rotateLeft(BST_Node curr) {
		BST_Node parent = curr.par;
		if (parent.par != null) {
			if (parent != parent.par.left) {
				parent.par.right = curr;
			} else {
				parent.par.left = curr;
			}
		}
		if (curr.left != null) {
			curr.left.par = parent;
		}
		curr.par = parent.par;
		parent.par = curr;
		parent.right = curr.left;
		curr.left = parent;
	}

	private void rotateRight(BST_Node curr) {
		BST_Node parent = curr.par;
		if (parent.par != null) {
			if (parent != parent.par.left) {
				parent.par.right = curr;
			} else {
				parent.par.left = curr;
			}
		}
		if (curr.right != null) {
			curr.right.par = parent;
		}
		curr.par = parent.par;
		parent.par = curr;
		parent.left = curr.right;
		curr.right = parent;
	}

	public String toString() {
		return "Data: " + this.data + ", Left: " + ((this.left != null) ? left.data : "null") + ",Right: "
				+ ((this.right != null) ? right.data : "null");
	}
	// you could have this return or take in whatever you want..so long as it
	// will do the job internally. As a caller of SPLT functions, I should
	// really have no idea if you are "splaying or not"
	// I of course, will be checking with tests and by eye to make sure you are
	// indeed splaying
	// Pro tip: Making individual methods for rotateLeft and rotateRight might
	// be a good idea!

	// --- end example methods --------------------------------------

	// --------------------------------------------------------------------
	// you may add any other methods you want to get the job done
	// --------------------------------------------------------------------

}