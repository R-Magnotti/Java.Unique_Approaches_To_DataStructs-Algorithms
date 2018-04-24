/*#####################################################################################################################
#                                          Stack|Linked List
#                                          Author: Richard Magnotti
#
# Goal of code is to implement a stack via a linked list -- and SORT it recursively!
######################################################################################################################*/
public class StackRecursSort
{
    /*##############################################################################################################
    # Node class should have pointer to previous node, next node, and data of current node
    ##############################################################################################################*/
    Node head;
    static class Node
    {
        Node next;
        int data;
    }
    //printS will print the stack in order from top to bottom (FILO fashion)
    void printS(Node curr)
    {
        if (curr != null)
        {
            System.out.println(curr.data);
            printS(curr.next);
        }
    }
    //push will add a new Node to the linked list and be assigned the passsed value
    //@int val
    void push(int val)
    {
        if (head == null)
        {
            head = new Node();
            head.data = val;
        }
        else
        {
            Node temp = head;
            head = new Node();
            head.next = temp;
            head.data = val;
        }
    }
    //pop will remove the top Node on the stacj
    Node pop()
    {
        Node temp = head;
        head = head.next;
        return temp;
    }
    //isEmpty will return whether or not the stack is empty or not
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
    //peek will return the value of the top Node in the stack without removing it like pop
    int peek()
    {
        return head.data;
    }
    //want to sort the stack using only stack functions and recursion
    //RSort will act as the "outer" recursive call to call the sorting method repeatedly
    //@Node curr
    int i = 0;//counter to print which pass of the sorting method we are on
    void RSort(Node curr)
    {
        i++;
        if(curr.next != null)
        {
            System.out.println("Sorting pass " + i + "---------------------------------------------------------------");
            recurSort();
            printS(head); //to show status of stack after each sorting pass
            RSort(curr.next);
        }
    }
    //RecurSort is the actual portion of the recursion that does the sorting.
    //RecurSort pops the two top Nodes in the stack and compared their values.
    //The larger value is passed down the stack for further comparison while the smaller is
    //stored in a temp variable within the stack frame and pushed at the tail end of the recursion
    void recurSort()
    {
        if (!isEmpty())
        {
            Node temp = pop(); //assigned to the top Node in stack to prevent nullPointerException upon tail recursion
            Node smaller = temp;
            if (temp.next != null)
            {
                Node temp2 = pop();
                if (temp.data > temp2.data) //comparison 1
                {
                    push(temp.data);
                    smaller = temp2;
                }
                else if (temp.data < temp2.data) //comparison 2
                {
                    push(temp2.data);
                    smaller = temp;
                }
                else //comparison 3 -- in case the two values are equal
                {
                    push(temp.data);
                }
            }
            recurSort();
            push(smaller.data);
        }
    }
    //main driver method to test other methods/classes
    public static void main(String[] args)
    {
        //arbitrary array to be pushed to stack
        //(by nature of the stack this array will be in opposite order in the stack)
        int[] arr = {5,3,7,1,23,67,4,1,67,8,24};
        StackRecursSort s = new StackRecursSort();
        for(int i=0; i<arr.length;i++)
        {
            s.push(arr[i]);
        }
        System.out.println("Initial stack");
        s.printS(s.head);
        s.RSort(s.head);
        System.out.println("Stack after sorting");
        s.printS(s.head);
    }
}