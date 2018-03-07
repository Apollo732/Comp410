/**
 * COMP 410
 *See inline comment descriptions for methods not described in interface.
 *
*/
package LinkedList_A1;

public class LinkedListImpl implements LIST_Interface {
	Node sentinel; // this will be the entry point to your linked list (the
					// head)
	int counter = 0;

	public LinkedListImpl() {// this constructor is needed for testing purposes.
								// Please don't modify!
		sentinel = new Node(0); // Note that the root's data is not a true part
								// of your data set!
	}

	// implement all methods in interface, and include the getRoot method we
	// made for testing purposes. Feel free to implement private helper methods!

	public Node getRoot() { // leave this method as is, used by the grader to
							// grab your linkedList easily.
		return sentinel;
	}

	@Override
	public boolean insert(double elt, int index) {
		Node sent = sentinel;
		Node temp = new Node(elt);
		
		

		if (index - counter > 0) {
			return false;
		}
		
		else if (counter == 0) {
			sent.next = temp;
			temp.next = sent;
			temp.prev = sent;
			sent.prev = temp;
			counter++;
			return true;

		}  

		else if (index >= counter) {

			Node last = sent.prev;

			last.next = temp;
			temp.prev = last;
			temp.next = sent;
			sent.prev = temp;

			counter++;
			return true;

		} else if (index == 0) {

			Node first = sent.next;

			first.prev = temp;
			temp.next = first;
			temp.prev = sent;
			sent.next = temp;

			counter++;
			return true;

		} else {

			Node curr = sent; // the first data node in the list is the
								// one after sentinel.
			for (int i = 0; i <= index + 1; i++) {

				if (i != index) {
					curr = curr.next;
				} else {
					curr.next.prev = temp;
					temp.next = curr.next;
					temp.prev = curr;
					curr.next = temp;
					counter++;
					return true;
				}

			}
		}

		counter++;
		return true;

	}

	@Override
	public boolean remove(int index) {

		Node sent = sentinel;

		if (index >= counter || counter == 0) {
			return false;

		}

		else if (index == counter - 1) {

			Node last = sent.prev;

			sent.prev = last.prev;
			last.prev.next = sent;

			counter--;
			return true;

		} else if (index == 0) {

			Node first = sent.next;

			sent.next = first.next;
			first.next.prev = sent;
			counter--;
			return true;

		} else {

			Node curr = sent; // the first data node in the list is the
								// one after sentinel.
			for (int i = 0; i <= index + 1; i++) {

				if (i != index) {
					curr = curr.next;
				} else {
					Node delete = curr.next;

					curr.next = delete.next;
					delete.next.prev = curr;

					counter--;
					return true;
				}

			}
		}

		counter--;
		return true;

	}

	@Override
	public double get(int index) {
		Node sent = sentinel;
		Node temp = sent.next;
		if (index > counter - 1 || counter == 0) {
			return Double.NaN;
		} else {
			for (int i = 0; i < index; i++) {
				temp = temp.getNext();
			}

			return temp.getData();
		}
	}

	@Override
	public int size() {
		return counter;
	}

	@Override
	public boolean isEmpty() {
		if (counter == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void clear() {
		sentinel = null;
		sentinel = new Node(0);
		sentinel.next = sentinel;
		sentinel.prev = sentinel;
		counter = 0;
	}
}