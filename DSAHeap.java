/*Author: Lina Yeo 
  Student ID: 21204647(Perth ID) / 700043539(Miri ID)
  University branch: Miri, Malaysia
  Purpose of code: Heap class, Task 5 Assignment(Code was obtained and upgraded from Practical 8 for the purpose of the assignment)
*/

public class DSAHeap {
    public DSAHeapEntry[] heapArray;
    private int count = 1;                  //start array at one, easier to get parent and child index
    private int heapSize;

    public DSAHeap(int size){
        heapArray = new DSAHeapEntry[size]; //set size of the heap array
        heapSize = size;
    }

    public void add(int priority, Object val, Object temp, Object humid, Object spd){
        DSAHeapEntry insert = new DSAHeapEntry(priority, val, temp, humid, spd);
        if(this.isFull()){                  //check if the array is full, throw exception
            throw new IllegalArgumentException ("Sorry the heap is full, unable to add value into the heap");
        }
        else{
            heapArray[count] = insert;
            if (count != 2)
            { 
                trickleUp();                //only trickleUp if more than one element in heap
            }
            count++;                        //add count when the value is added into the array
        }
    }

    public DSAHeapEntry remove(){
        DSAHeapEntry updateHeap = heapArray[1]; 
        if(this.isEmpty()){                 //check if the array is empty, throw exception
            System.out.println("Sorry, the heap is empty, there is nothing to remove");
        }
        else if(count == 2){                //when there is only one value in the heap array
            updateHeap = null;              //remove the first index value from the heap array
        }
        else if(count == 3){                //when there is only two value in the heap array
            updateHeap = heapArray[3];      //changed the first index value of the array to the second index value, set second index to empty
            heapArray[3] = null;
        }
        else{
            heapArray[1] = heapArray[count - 1];
            count--;                        //minus the count when the value is remove
            trickleDown(count);             //only trickle down when there is more than 2 value in the array
        }
        return updateHeap;
    }

    public void trickleDown(int count){
        int curIdx = 1;
        int largeIdx;
        int lChildIdx = leftChild(curIdx);      //call method to get the index of left child
        int rChildIdx = lChildIdx + 1;      
        boolean keepGoing = true;
        //loop through until the last index with value in array is reached
        while(keepGoing && lChildIdx < count){  
            keepGoing = false;
            largeIdx = lChildIdx;
            //compare the right child value with the parent value in the right child 
            if(rChildIdx < count){             
                if(heapArray[lChildIdx].getPriority() < heapArray[rChildIdx].getPriority()){
                    largeIdx = rChildIdx;
                }
            }
            //compare the left child value with the parent value
            if(heapArray[curIdx].getPriority() < heapArray[largeIdx].getPriority()){ 
                //do swap when left child child value is larger than the parent value
                swap(largeIdx, curIdx);         
                keepGoing = true;
            }
            curIdx = largeIdx;                  //set the current index to the next index after doing comparison
            lChildIdx = leftChild(curIdx);
            rChildIdx = lChildIdx + 1;
        } 
    }

    //display all the value in the array
    public void display(){ 
        for (int i = 1; i <= count - 1; i++){
            System.out.println(heapArray[i].getPriority() + " " + heapArray[i].getValue() + " " + heapArray[i].getTemp() + " " + heapArray[i].getHumidity() + " " + heapArray[i].getSpeed());
        }
    }

    //display the last index in the heap array
    public void displayLastIdx(){
        int priorityIdx = count - 1;
        System.out.println(" ");
        System.out.println("Scale: " + heapArray[priorityIdx].getPriority());
        System.out.println("Location: " + heapArray[priorityIdx].getValue());
        System.out.println("Temperature: " + heapArray[priorityIdx].getTemp());
        System.out.println("Humidity: " + heapArray[priorityIdx].getHumidity());
        System.out.println("Wind Speed: " + heapArray[priorityIdx].getSpeed());
    }
    
    public void trickleUp(){
        int currentIdx = count;
        //loop from the last array to the first array, to compare the value 
        while(currentIdx != 1 && heapArray[currentIdx].getPriority() > heapArray[parent(currentIdx)].getPriority()){
            int newIdx = parent(currentIdx);
            //swap when current array value is bigger than the parent array value
            swap(newIdx, currentIdx);
            currentIdx = newIdx;
        }
    }

    //swapping the value inside an array
    public void swap(int index1, int index2){
        DSAHeapEntry temp = heapArray[index1];
        heapArray[index1] = heapArray[index2];
        heapArray[index2] = temp;
        
    }

    //sorting the heap from lowest to greatest
    public DSAHeapEntry heapSort(){
        DSAHeapEntry sortedHeap = heapArray[1];
        heapify();
        for (int ii = count - 1; ii > 0; ii--){
            swap(1, ii);
            trickleDown(ii);    //ii is number of value inside the array 
        }
        return sortedHeap;
    }

    public DSAHeapEntry heapify(){
        DSAHeapEntry array = heapArray[count-1];
        for (int ii = ((count - 1) / 2) - 1; ii > 0; ii--)
        {
            trickleDown(ii);    //put iith element in correct place in heap
        }
        return array;
    }
    
    //get the leftChild index of the the parent
    public int leftChild(int count){
        return (2 * count); 
    }

    //get the parent index of the child
    public int parent(int count){
        double ans = (((double) count) / 2);
        return (int) Math.round(ans);
    }
 
    //check when the array is full
    public boolean isFull(){
        return count == heapArray.length;
    }

    //check when the array is empty
    public boolean isEmpty(){
        return count == 1;
    }

}