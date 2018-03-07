package MinBinHeap_A3;

import java.util.Arrays;

public class MinBinHeap_Playground {
	public static void main(String[] args) {
		// Add more tests as methods and call them here!!
		TestBuild();
	}

	public static void TestBuild() {
		// constructs a new minbinheap, constructs an array of EntryPair,
		// passes it into build function. Then print collection and heap.
		MinBinHeap mbh = new MinBinHeap();
		EntryPair[] collection = new EntryPair[10];
		collection[0] = new EntryPair("i", 31);
		collection[1] = new EntryPair("b", 4);
		collection[2] = new EntryPair("c", 16);
		collection[3] = new EntryPair("d", 18);
		collection[4] = new EntryPair("e", 11);
		collection[5] = new EntryPair("f", 9);
		collection[6] = new EntryPair("g", 7);
		collection[7] = new EntryPair("h", 12);
		collection[8] = new EntryPair("g", 3);
		collection[9] = new EntryPair("h", 21);

		/*
		 * 3 7 4 16 12 11 9 31 18 21
		 */

		mbh.build(collection);
		EntryPair why = new EntryPair("t", 2);
		//mbh.insert(why);
		
		//mbh.delMin();
		
		printHeapCollection(collection);
		
		printHeap(mbh.getHeap(), mbh.size());
		System.out.println("Size of heap: " + mbh.size() + "\n "+ mbh.getMin().value);

	}

	public static void printHeapCollection(EntryPair[] e) {
		// this will print the entirety of an array of entry pairs you will pass
		// to your build function.
		System.out.println("Printing Collection to pass in to build function:");
		for (int i = 0; i < e.length; i++) {
			System.out.print("(" + e[i].value + "," + e[i].priority + ")\t");
		}
		System.out.print("\n");
	}

	public static void printHeap(EntryPair[] e, int len) {
		// pass in mbh.getHeap(),mbh.size()... this method skips over unused 0th
		// index....
		char[] b = new char[50];
		Arrays.fill(b, ' ');
		System.out.println("Printing Heap");
		int p = 50;
		for (int i = 1; i < len + 1; i++) {
			p = p / 2;
			String yo = new String(Arrays.copyOfRange(b, 0, p));

			if (Space(i)) {
				System.out.print(i+": " + yo);
			}
			System.out.print("(" + e[i].value + "," + e[i].priority + ")\t");
			
		}
		System.out.print("\n");
	}

	private static boolean Space(int i) {
		for (int t = 1; t <= i; t = t * 2) {
			if (i == t) {
				return true;
			}
		}
		return false;
	}
}