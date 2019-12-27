import java.util.Random;

public class main
{

	public static void main(String[] args)
	{
		// I am using the random number generator to create the array
		Random random = new Random();
		int totalNumberOfElements = 25;
		int[] array = new int[totalNumberOfElements + 1];
		// Here the first element is the size of the array
		for (int i = 1; i < totalNumberOfElements + 1; i++)
		{
			// In this for loop I have added an element to the heap array and also increased number of elements
			array[0] = array[0] + 1;
			array[i] = random.nextInt(100);
		}
		// Here I added these numbers so that we can have some -1's(null nodes) in the array
		array[1] = -1;
		array[2] = -1;
		array[3] = -1;
		array[9] = -1;
		array[10] = -1;
		//array[15] = -1;
		System.out.println("The initial array is :-");
		// The first value while printing is always the size of the array
		for (int i = 0; i <= array[0]; i++)
		{
			System.out.print(array[i] + " ");
		}
		System.out.println();
		// This method is used to create the heap
		Heap myHeap = new Heap(array);
		// I have used this method to convert the array into the complete tree format array
		myHeap.convertArrayToCompleteTree();
		// This function is used to convert the random array to a heap array
		myHeap.heapify();
		System.out.println("The array after conversion to heap :-");
		// The first value while printing is always the size of the array
		for (int i = 0; i <= myHeap.array[0]; i++)
		{
			System.out.print(myHeap.array[i] + " ");
		}
		System.out.println();
		// This method is used to do the heap sort
		myHeap.heapSort();
		System.out.println("The array after applying heap sort");
		// The first value while printing is always the size of the array
		for (int i = 0; i <= myHeap.array[0]; i++)
		{
			System.out.print(myHeap.array[i] + " ");
		}

		System.out.println();

	}

	static class Heap
	{
		int[] array;

		/***
		 * This is the constructor for the heap
		 * @param array The array we want to convert to heap
		 */
		Heap(int[] array)
		{
			this.array = array;
		}

		// We are making a max heap here

		/***
		 * In this method we convert the array into a complete tree array
		 */
		void convertArrayToCompleteTree()
		{
			// In this method we will find the '-1' elements and put them at the last
			for (int i = 1; i <= array[0]; i++)
			{
				if (array[i] == -1)
				{
					// Last element comes to the place of '-1'
					int size = array[0];
					// Here I used the while loop since the -1 values can be at the end
					while(true){
						if(array[size] != -1){
							array[i] = array[size];
							array[size] = -1;
							break;
						}
						else{
							// If there is a -1 value at the end then reduce the size of array
							array[0] = array[0] - 1;
							size = size - 1;
						}
					}

					// reduce the size of the array
					array[0] = array[0] - 1;
				}
			}
		}

		/***
		 * This method is used to convert the given random array into a heap array which uses recursion
		 * @param array This is the array we want to convert
		 * @param index This is the index on which we have the parent
		 * @param heapSize This is the heap size of the given array
		 */
		void convertToHeap(int[] array, int index, int heapSize)
		{
			// Got the left and right children of the given parent at index
			heapSize = array[0];
			int parent = index;
			int leftChild = 2 * parent;
			int rightChild = (2 * parent) + 1;
			// Here I took the largest as the parent
			int largest = parent;
			// Checked with the left and right child and have the largest value index in the the largest variable
			if (rightChild <= heapSize && array[rightChild] > array[largest])
			{
				largest = rightChild;
			}
			if (leftChild <= heapSize && array[leftChild] > array[largest])
			{
				largest = leftChild;
			}
			// If we don't have the largest as the parent then we swap
			if (largest != parent)
			{
				int temp = array[largest];
				array[largest] = array[index];
				array[index] = temp;
				// We also have to percolate the value down so that the heap is made correctly
				// If we swap the values then the value of the parent is on the index largest
				convertToHeap(array, largest, heapSize);
			}
		}

		void heapify()
		{
			// Here we take from the last parent(array[0]/2) till the root(1)
			for (int index = array[0] / 2; index >= 1; index--)
			{
				convertToHeap(array, index, array[0]);
			}
		}

		void heapSort()
		{
			int[] heap = this.array;
			int heapSize = heap[0];
			while (heap[0] > 0)
			{
				// We will swap the first and last node and then heapify again
				int temp = heap[1];
				heap[1] = heap[heap[0]];
				heap[heap[0]] = temp;
				heap[0] = heap[0] - 1;
				convertToHeap(heap, 1, heap[0]);
			}
			// This step is used to print the array after sorting since we reduce size each time we won't get array at last
			heap[0] = heapSize;
		}
	}
}
