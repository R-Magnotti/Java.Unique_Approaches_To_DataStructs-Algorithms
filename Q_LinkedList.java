/*#####################################################################################################################
#                                          Queue|Linked List
#                                          Author: Richard Magnotti
#
# Goal of code is to implement a queue via a linked list -- and reverse it recursively!
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

            System.out.print("LL after adding " + tail.data + " is: ");
            printLL(tail);
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
                    //System.out.println("Node being removed " + head.data);
                    Node temp = head;
                    head = head.next;
                    head.prev = null;
                    return temp;
                }
                else //if <1 Node left in LL
                {
                    //System.out.println("Node being removed " + head.data);
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
        //want to reverse the queue using only standard operations using only original queue -- enQ, deQ, and isEmpty
        //approach: to dequeue before recursive call and enqueue after call (tail end) -- thus reversing the order of the initial queue
        //VISUAL OF PROCESS FOR RECURSIVE FUNC:
        //e.g. original linked list -                           [a|]-[b|]-[c|]-[d|]-[e|]-[f|]
        //e.g. deQ node and store locally -                     [e|]-[f|], temp = [d|]
        //e.g. enQ node in reverse order bc TAIL recursion -    [f|]-[e|]-[d|]
        //e.g. final linked list -                              [f|]-[e|]-[d|]-[c|]-[b|]-[a|]
        //time: O(n)
        void revQ()
        {
            //because we dequeue via the head, the head should always have a next Node until it's the last in the queue
            if (head.next == null)
            {
                return;
            }

            Node temp = deQ();
            System.out.print("LL after removing " + temp.data + " is: ");
            printLL(tail);
            System.out.println();
            revQ();
            enQ(temp.data);
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
        System.out.println("-----------------------------------------------------------------------------------");
        //following deQ should leave us with [47|]-[34|]-[1|]-[34|]-[76|]-[3|]-[4|]
        System.out.println("Getting rid of first node in the queue " + q.deQ().data + " we are left with:");
        q.printLL(q.tail);
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("Starting to reverse the queue: ");
        q.revQ();
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("\nFinal queue after being reversed:");
        q.printLL(q.tail);
    }
}
