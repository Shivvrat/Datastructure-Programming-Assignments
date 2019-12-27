import java.util.Random;

public class main
{

	public static void main(String[] args)
	{
		Random random = new Random();
		int totalNumberOfElements = 5;
		int[] array = new int[totalNumberOfElements + 1];
		// Here the first element is the size of the array
		for (int i = 1; i < totalNumberOfElements + 1; i++)
		{
			// In this for loop I have added an element to the heap array and also increased number of elements
			array[0] = array[0] + 1;
			array[i] = random.nextInt(100);
		}
		// Here I added these numbers so that we can have some -1's(null nodes) in the array
		array[2] = -1;
		array[4] = -1;
		//array[10] = -1;
		//array[15] = -1;
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
		for (int i = 0; i <= myHeap.array[0]; i++)
		{
			System.out.print(myHeap.array[i] + " ");
		}
		System.out.println();
		// This method is used to do the heap sort
		myHeap.heapSort();
		for (int i = 0; i <= myHeap.array[0]; i++)
		{
			System.out.print(myHeap.array[i] + " ");
		}

		System.out.println();

	}

	static class Heap
	{
		int[] array;

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
					array[i] = array[array[0]];
					array[array[0]] = -1;
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
			int leftChild = 2 * index;
			int rightChild = (2 * index) + 1;
			int parent = index;
			// Here I took the largest as the parent
			int largest = parent;
			// Checked with the left and right child and have the largest value index in the the largest variable
			if (rightChild < heapSize && array[rightChild] > array[largest])
			{
				largest = rightChild;
			}
			if (leftChild < heapSize && array[leftChild] > array[largest])
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
			// Here we take from the last parent till the root
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
			// This step is dont to print the array after sorting
			heap[0] = heapSize;
		}
	}
}
