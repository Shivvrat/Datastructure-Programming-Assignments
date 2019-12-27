class main
{

	public static void main(String[] args)
	{
		/**
		 * This is the main function which is used to run the code
		 * @param args
		 */
		BinarySearchTree tree = new BinarySearchTree();
		//Here we create the array to enter values into BST
		int[] array = new int[]{100, 50, 200, 150, 300, 25, 75, 12, 37, 125, 175, 250, 320, 67, 87, 94, 89, 92, 88};
		System.out.println("This is the string during creation");
		for (int nextValue : array)
		{
			// Here we enter the values into the BST
			tree.addNode(nextValue);
			System.out.print(Integer.toString(nextValue) + " ");
		}
		System.out.println();
		System.out.println("This is the in order traversal of the Binary Search Tree");
		//Now we print the tree by using In order Traversal
		tree.inOrderTraversal(tree.getTheRoot());
		System.out.println();
		System.out.println("This is the in order traversal of the Binary Search Tree after deleting 100");
		// We delete the node with value 100
		tree.deleteNode(100);
		tree.inOrderTraversal(tree.getTheRoot());
	}
}

class BinarySearchTree
{
	Node theRoot;

	/**
	 * This is the BST constructor which will be called when we first call the class BinarySearchTree
	 */
	public BinarySearchTree()
	{
		theRoot = null;
	}
	/**
	 * This function is used to add a node to the BST
	 * @param newValue - This is the value we want to add into the tree
	 */
	public void addNode(Object newValue)
	{

		if (theRoot == null)
		{
			// Here we check if we don't have a tree and if yes then we create a new tree with this node as the root
			Node newNode = new Node(newValue, null);
			theRoot = newNode;
		}
		else
		{
			// Here we create the new node and find a place where we can add that node
			Node temp1 = theRoot;
			Node parent = null;
			while (temp1 != null)
			{
				// In this while loop we go to the direction in which the value should be added.
				// That is if the value is larger than the current node we are on then we go to the right
				// else we go to the left
				if ((Integer) newValue > (Integer) temp1.getData())
				{
					parent = temp1;
					temp1 = temp1.getRightChild();
					if (temp1 == null)
					{
						Node newNode = new Node(newValue, parent);
						parent.setRightChild(newNode);
						return;
					}
				}
				else if ((Integer) newValue < (Integer) temp1.getData())
				{
					parent = temp1;
					temp1 = temp1.getLeftChild();
					if (temp1 == null)
					{
						Node newNode = new Node(newValue, parent);
						parent.setLeftChild(newNode);
						return;
					}
				}
				else{
					System.out.println("You have already added the given value, cannot add again");
					return;
				}
			}
		}
	}

	/**
	 * This function is used for the inorder traversal of the tree
	 *
	 * @param temp this is the node on which we do inorder traversal
	 */
	public void inOrderTraversal(Node temp)
	{
		if (temp == null)
		{
			return;
		}
		inOrderTraversal(temp.getLeftChild());
		System.out.print(Integer.toString(((Integer) temp.getData())) + " ");
		inOrderTraversal(temp.getRightChild());
	}

	/**
	 * This function is used to delete a particular node from the tree with the given value
	 *
	 * @param value - this is the int value we want to delete from the tree
	 */
	public void deleteNode(int value)
	{
		Node current_Node = getTheRoot();
		// This is the case if we want to delete the root
		if ((Integer) current_Node.getData() == value)
		{
			Node predecessorOfTemp = getPredecessor(current_Node);
			if (predecessorOfTemp == current_Node)
			{
				if (current_Node.getRightChild() == null)
				{
					// This is the case when root does not have any children
					setTheRoot(null);
					return;
				}
				else
				{
					// This is the case when root does not have the right child
					setTheRoot(current_Node.getRightChild());
					return;
				}

			}
			// This is the case if the predecessor is a leaf node
			if (predecessorOfTemp.getRightChild() == null && predecessorOfTemp.getLeftChild() == null)
			{
				// This is the case if the predecessor is not the left child.
				if (predecessorOfTemp != current_Node.getLeftChild())
				{
					predecessorOfTemp.setLeftChild(current_Node.getLeftChild());
					current_Node.getLeftChild().setParent(predecessorOfTemp);
					predecessorOfTemp.getParent().setRightChild(null);
					predecessorOfTemp.setParent(null);

				}
				else
				{
					predecessorOfTemp.setLeftChild(null);
					predecessorOfTemp.setParent(null);
					setTheRoot(predecessorOfTemp);
				}
				if (predecessorOfTemp != current_Node.getRightChild())
				{
					predecessorOfTemp.setRightChild(current_Node.getRightChild());
					if (current_Node.getRightChild() != null)
					{
						current_Node.getRightChild().setParent(predecessorOfTemp);
					}
				}
				else
				{
					predecessorOfTemp.setRightChild(null);
					predecessorOfTemp.setParent(null);
					setTheRoot(predecessorOfTemp);
				}
				predecessorOfTemp.setParent(null);
				setTheRoot(predecessorOfTemp);
			}
			else
			{
				// In this case we have a child of the predecessor
				Node childOfPredecessor = predecessorOfTemp.getLeftChild();
				Node temp2 = current_Node.getLeftChild();
				if (temp2.getRightChild() != null)
				{
					while (temp2.getRightChild() != predecessorOfTemp)
					{
						temp2 = temp2.getRightChild();
					}
					temp2.setRightChild(childOfPredecessor);
					childOfPredecessor.setParent(temp2);
				}

				if (predecessorOfTemp != current_Node.getLeftChild())
				{
					predecessorOfTemp.setLeftChild(current_Node.getLeftChild());
					current_Node.getLeftChild().setParent(predecessorOfTemp);
				}
				else
				{
					predecessorOfTemp.setParent(null);
					setTheRoot(predecessorOfTemp);
				}
				if (predecessorOfTemp != current_Node.getRightChild())
				{
					if (current_Node.getRightChild() == null)
					{

					}
					else
					{
						predecessorOfTemp.setRightChild(current_Node.getRightChild());
					}
					if (current_Node.getRightChild() != null)
					{
						current_Node.getRightChild().setParent(predecessorOfTemp);
					}
				}
				else
				{
					predecessorOfTemp.setRightChild(null);
					predecessorOfTemp.setParent(null);
					setTheRoot(predecessorOfTemp);
				}
				predecessorOfTemp.setParent(null);
				setTheRoot(predecessorOfTemp);
			}
			return;
		}
		// Here we get to the node which has the value we want to delete
		while (current_Node != null)
		{
			if (value > (Integer) current_Node.getData())
			{
				current_Node = current_Node.getRightChild();
			}
			else if (value < (Integer) current_Node.getData())
			{
				current_Node = current_Node.getLeftChild();
			}
			else
			{
				break;
			}
		}
		if (current_Node == null)
		{
			// In this case we don't have the value in the tree
			System.out.println("The value you entered is not in the Tree, Please check again");
			return;
		}
		// Now current_Node is the node we want to delete.
		Node predecessorOfCurrentNode = getPredecessor(current_Node);
		// This is the case when the node is a leaf node
		if (current_Node.getRightChild() == null && current_Node.getLeftChild() == null)
		{
			if ((Integer) current_Node.getData() > (Integer) current_Node.getParent().getData())
			{
				current_Node.getParent().setRightChild(null);
			}
			else if ((Integer) current_Node.getData() < (Integer) current_Node.getParent().getData())
			{
				current_Node.getParent().setLeftChild(null);
			}
			return;
		}

		if (predecessorOfCurrentNode == current_Node)
		{
			// In this case we have already seen the case in which the node was a leaf. Thus we only check for the right node
			if (current_Node.getRightChild() != null)
			{
				if ((Integer) current_Node.getData() > (Integer) current_Node.getParent().getData())
				{
					current_Node.getParent().setRightChild(current_Node.getRightChild());
					current_Node.getRightChild().setParent(current_Node.getParent());
				}
				else if ((Integer) current_Node.getData() < (Integer) current_Node.getParent().getData())
				{
					current_Node.getParent().setLeftChild(current_Node.getRightChild());
					current_Node.getRightChild().setParent(current_Node.getParent());
				}
				return;
			}
		}
		// This is the case if the predecessor node is a leaf node
		if (predecessorOfCurrentNode.getRightChild() == null && predecessorOfCurrentNode.getLeftChild() == null)
		{
			if (predecessorOfCurrentNode != current_Node.getLeftChild())
			{
				predecessorOfCurrentNode.setLeftChild(current_Node.getLeftChild());
				predecessorOfCurrentNode.setRightChild(current_Node.getRightChild());
				predecessorOfCurrentNode.getParent().setRightChild(null);
				predecessorOfCurrentNode.setParent(current_Node.getParent());
				if ((Integer) current_Node.getData() > (Integer) current_Node.getParent().getData())
				{
					current_Node.getParent().setRightChild(predecessorOfCurrentNode);
				}
				else if ((Integer) current_Node.getData() < (Integer) current_Node.getParent().getData())
				{
					current_Node.getParent().setLeftChild(predecessorOfCurrentNode);
				}
			}
			else
			{
				predecessorOfCurrentNode.setRightChild(current_Node.getRightChild());
				predecessorOfCurrentNode.setParent(current_Node.getParent());
				if (current_Node.getLeftChild() != null)
				{
					current_Node.getLeftChild().setParent(predecessorOfCurrentNode);
				}
				if (current_Node.getRightChild() != null)
				{
					current_Node.getRightChild().setParent(predecessorOfCurrentNode);
				}
				if ((Integer) current_Node.getData() > (Integer) current_Node.getParent().getData())
				{
					current_Node.getParent().setRightChild(predecessorOfCurrentNode);
				}
				else if ((Integer) current_Node.getData() < (Integer) current_Node.getParent().getData())
				{
					current_Node.getParent().setLeftChild(predecessorOfCurrentNode);
				}
			}
			return;
		}
		// In this case the predecessor will have a left child
		else
		{
			// In this case the predecessor will have its own left child and right child of the current node
			if (predecessorOfCurrentNode == current_Node.getLeftChild())
			{
				if ((Integer) current_Node.getData() > (Integer) current_Node.getParent().getData())
				{
					current_Node.getParent().setRightChild(predecessorOfCurrentNode);
				}
				else if ((Integer) current_Node.getData() < (Integer) current_Node.getParent().getData())
				{
					current_Node.getParent().setLeftChild(predecessorOfCurrentNode);
				}
				if (current_Node.getRightChild() != null)
				{
					predecessorOfCurrentNode.setRightChild(current_Node.getRightChild());
				}
				predecessorOfCurrentNode.setParent(current_Node.getParent());
				current_Node.getRightChild().setParent(predecessorOfCurrentNode);
			}
			else
			{
				// In this case we will have to attach the child of predecessor into predecessor's place
				Node childOfPredecessor = predecessorOfCurrentNode.getLeftChild();
				Node temp2 = current_Node.getLeftChild();
				while (temp2.getRightChild() != predecessorOfCurrentNode)
				{
					temp2 = temp2.getRightChild();
				}
				temp2.setRightChild(childOfPredecessor);
				childOfPredecessor.setParent(temp2);
				predecessorOfCurrentNode.setParent(current_Node.getParent());
				if(current_Node.getRightChild() != null){
                    predecessorOfCurrentNode.setRightChild(current_Node.getRightChild());
                    current_Node.getRightChild().setParent(predecessorOfCurrentNode);
                }
				else{
                    predecessorOfCurrentNode.setRightChild(null);
                }
				if(current_Node.getLeftChild() != null){
                    predecessorOfCurrentNode.setLeftChild(current_Node.getLeftChild());
                    current_Node.getLeftChild().setParent(predecessorOfCurrentNode);
                }
				else{
                    predecessorOfCurrentNode.setLeftChild(null);
                }


				if ((Integer) current_Node.getData() > (Integer) current_Node.getParent().getData())
				{
					current_Node.getParent().setRightChild(predecessorOfCurrentNode);
				}
				else if ((Integer) current_Node.getData() < (Integer) current_Node.getParent().getData())
				{
					current_Node.getParent().setLeftChild(predecessorOfCurrentNode);
				}
			}
		}
	}

	/**
	 * This is used to find the predecessor of a given node
	 *
	 * @param temp This is the given node
	 * @return This returns the predecessor node
	 */
	public Node getPredecessor(Node temp)
	{
		if (temp.getLeftChild() == null)
		{
			return temp;
		}
		temp = temp.getLeftChild();
		while (temp.getRightChild() != null)
		{
			temp = temp.getRightChild();
		}
		return temp;
	}

	/**
	 * THis is a function to get the root of the tree
	 *
	 * @return root of the tree
	 */
	public Node getTheRoot()
	{
		return theRoot;
	}

	/**
	 * This is the function to set the root of the tree
	 *
	 * @param theRoot This is the new value of the root
	 */
	public void setTheRoot(Node theRoot)
	{
		this.theRoot = theRoot;
	}

	class Node
	{
		Node leftChild;
		Node rightChild;
		Node parent;
		Object data;

		/**
		 * This function is used to create a new node
		 *
		 * @param value  This is the new node's value
		 * @param parent This is the new node's parent
		 */
		public Node(Object value, Node parent)
		{
			this.leftChild = null;
			this.rightChild = null;
			(this.parent) = parent;
			this.data = value;
		}

		/**
		 * This function is used to set the data for a given node
		 *
		 * @param data This is the data we want to change to
		 */
		public void setData(Object data)
		{
			this.data = data;
		}

		// These are the getters and setters for the node class
		public Node getLeftChild()
		{
			if (leftChild != null)
			{
				return leftChild;
			}
			else
			{
				return null;
			}
		}

		public Node getRightChild()
		{
			if (rightChild != null)
			{
				return rightChild;
			}
			else
			{
				return null;
			}
		}

		public Node getParent()
		{
			if (parent != null)
			{
				return parent;
			}
			else
			{
				return null;
			}
		}

		public Object getData()
		{
			return (Integer) data;
		}

		public void setLeftChild(Node leftChild)
		{
			if (leftChild != null)
			{
				this.leftChild = leftChild;
			}
			else
			{
				this.leftChild = null;
			}
		}

		public void setParent(Node parent)
		{
			if (parent != null)
			{
				this.parent = parent;
			}
			else
			{
				this.parent = null;
			}
		}

		public void setRightChild(Node rightChild)
		{
			if (rightChild != null)
			{
				this.rightChild = rightChild;
			}
			else
			{
				this.rightChild = null;
			}
		}
	}
}