/*Author: Lina Yeo 
  Student ID: 21204647(Perth ID) / 700043539(Miri ID)
  University branch: Miri, Malaysia
  Purpose of code: Test Harness for class DSAHeap and DSAHeapEntry
*/

public class UnitTestDSAHeap {
    public static void main(String[] args) {
        DSAHeap testArray = new DSAHeap(10); //set array size for the heap

        System.out.println("TestHarness_DSAHeap: ");

        //inserting value into the heap array
        testArray.add(5, "A", 1, 2, 3); 
        testArray.add(3, "B", 4, 5, 6);
        testArray.add(2, "C", 7, 8, 9);
        testArray.add(6, "D", 2, 3, 4);
        testArray.add(1, "E", 3, 4, 5);
        testArray.add(4, "F", 6, 7, 8);

        //print out the heap array that should be displayed according to their priority
        System.out.println("The output of max heap should be:");
        System.out.println("|6 D 2 3 4|");
        System.out.println("|5 A 1 2 3|");
        System.out.println("|4 F 6 7 8|");
        System.out.println("|3 B 4 5 6|");
        System.out.println("|1 E 3 4 5|");
        System.out.println("|2 F 7 8 9|");

        System.out.println("\nThe heap output [testing add(), trickleUp(), swap(), display(), parent(), isFull()]:");
        testArray.display(); //testing add and display method in DSAHeap class 

        //print out the heap array that should be displayed according to their priority after removing one value
        System.out.println("\nRemoving one value from the heap [testing remove(), trickleDown(), swap(), display(), leftChild(), isEmpty()]");
        System.out.println("The output of max heap should be:");
        System.out.println("|5 A 1 2 3|");
        System.out.println("|3 B 4 5 6|");
        System.out.println("|4 F 6 7 8|");
        System.out.println("|1 E 3 4 5|");
        System.out.println("|2 F 7 8 9|");

        //testing the output after removing one value in the heap array
        testArray.remove();
        System.out.println("\nThe heap output after removing one value:");
        testArray.display();

        //print out the heap sort array that should be displayed
        System.out.println("\nTesting HeapSort\n");
        System.out.println("The output of heap sort should be:");
        System.out.println("|1 E 3 4 5|");
        System.out.println("|2 F 7 8 9|");
        System.out.println("|3 B 4 5 6|");
        System.out.println("|4 F 6 7 8|");
        System.out.println("|5 A 1 2 3|");

        //testing heap sort method 
        System.out.println("\nThe heap sort output:");
        testArray.heapSort();
        testArray.display();
        System.out.println(" ");
    }
}
