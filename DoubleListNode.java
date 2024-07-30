/*Author: Lina Yeo 
  Student ID: 21204647 / 700043539
  University branch: Miri, Malaysia
  Purpose of code:   
*/

import java.io.Serializable;
import java.util.Iterator;

public class DoubleListNode implements Serializable {
    
    DSAGraphNode data;
    Object distance;
    DoubleListNode next, prev;

    public DoubleListNode(DSAGraphNode newValue){
        this.data = newValue;
        this.next = null;
        this.prev = null;
    }

    public DSAGraphNode getData(){
        return data;
    }
    public void setData(DSAGraphNode newValue){
        data = newValue;
    }

    public Object getDistance(){
        return distance;
    }

    public void setDistance(Object newDistance){ 
        this.distance = newDistance;
    }
 
    public DoubleListNode getNext(){
        return next;
    }

    public void setNext(DoubleListNode newNext){
        next = newNext;
    }

    public DoubleListNode getPrev(){
        return prev;
    }
    public void setPrev(DoubleListNode newPrev){
        prev = newPrev;
    }

}
