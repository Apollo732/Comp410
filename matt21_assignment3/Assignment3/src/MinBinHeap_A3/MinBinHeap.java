package MinBinHeap_A3;

public class MinBinHeap implements Heap_Interface {
	private EntryPair[] array; // load this array
	private int size;
	private static final int arraySize = 10000; // Everything in the array will
												// initially
												// be null. This is ok! Just
												// build out
												// from array[1]

	public MinBinHeap() {
		this.array = new EntryPair[arraySize];
		array[0] = new EntryPair(null, -100000); // 0th will be unused for
													// simplicity
													// of child/parent
													// computations...
													// the book/animation page
													// both do this.
	}

	// Please do not remove or modify this method! Used to test your entire
	// Heap.
	@Override
	public EntryPair[] getHeap() {
		return this.array;
	}

	@Override
	public void insert(EntryPair entry) {

		this.size++;
		array[this.size] = entry;
		insertShift(this.size);

	}

	private void insertShift(int index) {
		int parentIndex;
		EntryPair temp;

		parentIndex = getParent(index);

		if (index != 0) {
			if (array[index].getPriority() < array[parentIndex].getPriority()) {
				temp = array[index];
				array[index] = array[parentIndex];
				array[parentIndex] = temp;
				insertShift(parentIndex);
				if(parentIndex !=1){
					insertShift(parentIndex);
				}
			}
		}
	}

	private int getLeft(int index) {
		return (2 * index);
	}

	private int getRight(int index) {
		return (2 * index) + 1;
	}

	private int getParent(int index) {
		if ((index - 1) / 2 == 0) {
			return 1;
		}

		return (index) / 2;
	}

	@Override
	public void delMin() {
		
		if(this.array[1] != null){
			if(this.array[2] == null){
				this.array[1]=null;
			}
			this.array[1] = this.array[this.size];
		array[this.size] = null;
		this.size--;
		if (this.size > 0) {
			deleteShift(1);
		}
		}
		
		
	}

	public void deleteShift(int index) {
		
		
		
		int parentIndex, leftIndex, rightIndex, minIndex;
		parentIndex = getParent(index);
		leftIndex = getLeft(index);
		rightIndex = getRight(index);
		EntryPair temp;

		
		if(leftIndex > 9999){
			return;
		}
		
		
		if (rightIndex > this.size) {
			if (leftIndex > this.size) {
				return;
			} else {
				minIndex = leftIndex;
			}
		} else {
			if (array[leftIndex].getPriority() <= array[rightIndex].getPriority()) {
				minIndex = leftIndex;
			} else {
				minIndex = rightIndex;
			}

		}
		if (array[index].getPriority() > array[minIndex].priority) {
			temp = array[minIndex];
			array[minIndex] = array[index];
			array[index] = temp;
			deleteShift(minIndex);

		}

	}

	@Override
	public EntryPair getMin() {
		// TODO Auto-generated method stub
		return this.array[1];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void build(EntryPair[] entries) {

		int index = 0;
		
		while((index < entries.length) && (entries[index] !=null)){
		
			array[index+1] = entries[index];
			size++;
			index++;
		}
		for(int i = size/2; i > 0; i--){
			deleteShift(i);
		}

	}
}