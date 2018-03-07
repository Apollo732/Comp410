/**
 * COMP 410
 *
 * Make your class and its methods public!
 *
 * Your SPLT implementation will implement this BST interface.
 *
*/

package SPLT_A4;





// ADT operations

public interface SPLT_Interface {
	public void insert(String s);

	public void remove(String s);

	public String findMin();

	public String findMax();

	public boolean empty();

	public boolean contains(String s);

	public int size();

	public int height();

	public BST_Node getRoot();
}