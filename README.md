# Datastructure-Programming-Assignments
The code includes the work done during the datastructure and algorithms course at the University of Texas at Dallas.

## HW1
1. Create a single linked list of at least 15 nodes.  The numbers in the list should not be sorted.
2. Traverse the list.
3. Sort the list in ascending order. Sort in such a manner that the node is unlinked and re-linked at the correct location.
4. Traverse the list - it should show the sorted order.

## HW2
1. Create a BST using the following values , inserted in the order given here.
    100, 50, 200, 150, 300, 25, 75, 12, 37, 125, 175, 250, 320, 67, 87, 94, 89,92,88
2. Do an inorder traversal to print the values.
3. delete 100, use predecessor.
4. Do an  inorder traversal again.

## HW3
Implement heap sort.
You are given an array of N positive numbers, in a random order.
1. Make the array into a heap.  A null node is indicated by -1. The -1 (null node) could be anywhere in the array, i.e. all -1 are not at the end of array.
2. Sort the heap using heap sort.

## HW4
Implement Dijkstra's algorithm.
Your graph must have at least 10 vertices and 20 edges.
Print out the graph - list of vertices and edges(pair of vertices)
Run dijkstra's algorithm.
Print the tree that results - list of vertices in the tree (same as above) and list of edges that make up the tree.

## HW5
Implement the DFS topological ordering.
Your directed graph must have at least 10 vertices and 15 edges.
You must run the algorithm on two sets of graphs.
1. The graph does not have a cycle.  It generates a correct topological ordering.
2. The graph has a cycle. It attempts to generate an order. But discovers the cycle and exits (some nodes will already be ordered.

## HW6
For this assignment, you will create a hash table to implement spell checker.
1. Create a file of 100 words of varying length (max 8 characters).
2. All the words are in lower case.
3. design a hash table.
4. enter each word in the hash table.

Once the hash table is created, run it as a spell checker.  You enter a word (interactive), find the word in your hash table. If not found enter an error message.  Then prompt for next word.  End the session by some control character like ctrl-c or something.

1. use the linear probing first.  Count the number of collisions and print it.
2. Then use quadratic probing.  Count the number of collisions and print it.
3. Start with a table size that is about 53.  So 100 words would have a load factor of more than .5.  your program should increase table size accordingly.  Now add 10 more words.  The program should automatically determine that table size needs to increase.
Print out - increasing table size to <size>
rehash all the old words also and the new words to the new hash table.
You already have linear probing and quadratic probing functions.  Print the collisions in each case for the new table size.
