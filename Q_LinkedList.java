/*#####################################################################################################################
#                                          Queue|Linked List
#                                          Author: Richie Magnotti
#
# Goal of code is to implement a queue via a linked list
######################################################################################################################*/

/*#####################################################################################################################
# Queue class: main class to encapsulate all other classes in this data struct
######################################################################################################################*/
class Q
{
    /*##################################################################################################################
    # Queue class should have push, pop, peek, and isEmpty methods
    # Queue is composed of Nodes with bi-directional pointers, making traversal possible forward OR back!
    ##################################################################################################################*/
    static class Queue
    {
        Node head; //head of linked list
        Node tail; //tail of linked list
        Queue(int data) //constructor to accept data for initial first node in LL
        {
            tail = new Node(); //setting tail of linked list = first node being created
            head = tail; //pointing tail and head to same Node object
            tail.data = data; //setting initial head/tail object's value to the passed int
        }
        /*##############################################################################################################
        # Node class should have pointer to previous node, next node, and data of current node
        ##############################################################################################################*/
        class Node
        {
            Node next;
            Node prev;
            int data;
        }
        //method to check whether the queue is empty
        //time: O(1)
        boolean isEmpty()
        {
            if (head == null)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        //method to add to the queue. the queue adds new Nodes to the tail, and removes nodes from the head
        // @int val: new Node will be made and given passed int value
        //time: O(1)
        void enQ(int val)
        {
            Node temp = tail;
            tail = new Node();
            tail.data = val;
            tail.prev = temp;
            tail.prev.next = tail; //to establish 2 way relationship

            System.out.print("Linked list at each step: ");
            printLL(tail);
            System.out.println("The unchanging head value should be " + head.data);
            System.out.println();
        }
        //method to print the linked list recursively
        //time: O(n) -- because must iterate through all n nodes and thus must encounter each one
        void printLL(Node currNode)
        {
            if (currNode != null)
            {
                System.out.print(currNode.data + ", ");
                printLL(currNode.prev);
            }
        }
        //to remove node from the queue in FIFO fashion -- i.e. from the head end
        //time: O(1)
        Node deQ()
        {
            //nested if statement in case list is not empty
            if (!isEmpty())
            {
                if (head != tail) //if >1 Node left in LL
                {
                    System.out.println("Node being removed " + head.data);
                    Node temp = head;
                    head = head.next;
                    head.prev = null;
                    System.out.println("LL after dequeueing is ");
                    printLL(tail);
                    return temp;
                }
                else //if <1 Node left in LL
                {
                    System.out.println("Node being removed " + head.data);
                    head = null;
                }
                return head;
            }
            //if list is empty
            else
            {
                System.out.println("Q is empty!");
                return null;
            }
        }
    }
    //main driver method to test all classes/methods/implement the queue
    //user can change value and size of array at will, the following are dummy/example values and dummy tests
    public static void main(String[] args)
    {
        int[] A = {2,4,3,76,34,1,34,47};
        Queue q = new Queue(A[0]);
        for(int i = 1; i<A.length; i++)
        {
            q.enQ(A[i]);
        }
        System.out.println("Getting rid of first node in the queue " + q.deQ().data);
        System.out.println("Getting rid of second node in the queue " + q.deQ().data);
        q.deQ();
        q.deQ();
        q.deQ();
        q.deQ();
        q.deQ();
        q.deQ();
        System.out.println(q.isEmpty());
    }
}
