// We import this to create random numbers for values.

import java.util.Random;

class LinkedListsForSort {

    // We store the value for the head node in theHead
    Node theHead;

    public LinkedListsForSort() {
        /**
         * This is the linked list constructor which will be called when we first call the class LinkedListsForSort
         */

        // Here we create the head and make it's value null.
        theHead = null;
    }


    public void addNode(Object data) {
        /**
         * This function is used to add new nodes
         *
         * @param data - It is the value to be added in the linked list. We will take only two values for our case, Integer and null
         */

        Node newNode = new Node(data);
        // If we have a null list
        if (theHead == null) {
            theHead = newNode;
        } else {
            Node currentNode = theHead;
            if (currentNode != null) {
                while (currentNode.next != null) {
                    // We try to find the last node and store it in the current node value
                    currentNode = currentNode.next;
                }
                // After this node we are at the end of the list and now we add our node
                currentNode.setNext(newNode);
            }
        }
    }


    @Override
    public String toString() {
        /**
         * This is the function we will use to print(traverse) our linked list
         *
         * @return the string after traversing the list
         */

        String out = "";
        if (theHead != null) {
            Node currentNode = theHead;
            while (currentNode != null) {
                String stringToPrint = currentNode.getData().toString();
                out = out + "{" + stringToPrint + "} -> ";
                currentNode = currentNode.getNext();
            }
        }
        out = out + "{null}";
        return out;
    }

    public Node getTheHead() {
        /**
         * This function returns the head of the linked list
         *
         * @return head of given linked list
         */

        return theHead;
    }

    public void setTheHead(Node theHead) {
        this.theHead = theHead;
    }

    public void swap_nodes(Node node1, Node node2) {
        /**
         * This is the function that will be used for the swapping of nodes. In this case we will swap the nodes and not the values inside the nodes.
         * This function is used in the sorting function for the swapping.
         *
         * @param node1 - This the first node to be swapped
         * @param node2 - This is the second node to be swapped
         */

        if(node1==node2){
            return;
        }
        Node nodeBefore1 = getNodesBeforeGivenNode(node1);
        Node nodeBefore2 = getNodesBeforeGivenNode(node2);
        // If this condition is satisfied then the node1 is the head
        if(nodeBefore1 == null){
            this.setTheHead(node2);
            nodeBefore2.setNext(node1);
            Node temp1 = (node1.getNext());
            node1.setNext(node2.getNext());
            node2.setNext(temp1);
        }
        else{
            nodeBefore1.setNext(node2);
            nodeBefore2.setNext(node1);
            Node temp1 = (node1.getNext());
            node1.setNext(node2.getNext());
            node2.setNext(temp1);
        }

    }
    public Node getNodesBeforeGivenNode(Node node){
        /**
         * This function is used to get the node just before the given node
         * @param node - The node for which the before node is to found
         */
        Node temp2 = null;
        Node temp = theHead;
        while(temp !=null){
            if(temp == node){
                return null;
            }
            if(temp.getNext() == node){
                temp2 = temp;
                return temp;
            }
            temp = temp.getNext();
        }
        return temp2;
    }

    LinkedListsForSort selectionSort(LinkedListsForSort linked_list) {
        /**
         * This is the function which is doing the selection sort on the linked list using the swapping function. We are doing basic
         * selection sort and are swapping the nodes and not the values.
         *
         * ADAPTED from the slides - selection-sort Page Number :- 7 (Class slides)
         *
         * @param linked_list - This is linked list we want to sort
         * @return sorted linked list
         */

        // I haven't changed this algorithm apart from the swapping nodes part. The algorithm is from the slides.
        Node current;
        Node current2;
        Node min;
        current = linked_list.getTheHead();
        while (current != null) {
            min = current;
            current2 = current.getNext();
            while (current2 != null) {
                if ((Integer) min.getData() > (Integer) current2.getData()) {
                    min = current2;
                }
                current2 = current2.getNext();
            }
            swap_nodes(current, min);
            // since we have swapped the values of min and current we will use min for the next value of current
            current = min.getNext();
        }
        return linked_list;
    }

    /**
     * Class Node is the one we will use to create a new node of a linked list
     */
    public class Node {
        Node next;
        Object data;

        /**
         * This is the node constructor with int values to be stored
         *
         * @param value - This is the value we want to the linked list
         */
        public Node(Object value) {
            next = null;
            data = value;
        }

        /**
         * This is the function that gets the data and returns it
         *
         * @return - data of the given node.
         */
        public Object getData() {
            return data;
        }

        /**
         * This is the function used to set the data for the node
         * I have created all these setter and getter functions so that they can be used later
         *
         * @param data
         */
        public void setData(Object data) {
            this.data = data;
        }

        /**
         * This is the function used to get the next node
         *
         * @return Next node for the given node
         */
        public Node getNext() {
            return next;
        }

        /**
         * This is the funciton used to set the next node of current node
         *
         * @param next the next node to be added
         */
        public void setNext(Node next) {
            this.next = next;
        }
    }
}

class main {
    /**
     * This is the main function wwhich will be used as the function to make and sort the linkedlists.
     * @param args
     */
    public static void main(String[] args) {
        LinkedListsForSort linkedList = new LinkedListsForSort();
        Random random = new Random();
        // You can change the number of items to be added here
        for (int i = 0; i < 20; i++) {
            linkedList.addNode(random.nextInt(50));
        }
        System.out.println("We have 20 nodes linked list right now.");
        System.out.println("This the Linked List before Sorting :-");
        System.out.println(linkedList.toString());
        System.out.println("This the Linked List after Sorting :-");
        System.out.println(linkedList.selectionSort(linkedList));
    }
}
