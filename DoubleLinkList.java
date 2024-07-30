/*Author: Lina Yeo 
  Student ID: 21204647 / 700043539
  University branch: Miri, Malaysia
  Purpose of code: 
*/

import java.util.Iterator;
import java.io.Serializable;
import java.util.*;

public class DoubleLinkList implements Iterable, Serializable{

    public DoubleListNode head;
    public DoubleListNode tail;

    public DoubleLinkList(){
        head = null;
        tail = null;
    }
    
    public Iterator iterator(){
        return new MyLinkedListIterator(this);
    }
      
     private class MyLinkedListIterator implements Iterator{ 
        private DoubleListNode iterNext;
    
        public MyLinkedListIterator(DoubleLinkList theList) {
                iterNext = theList.head; 
        }

        public boolean hasNext() { 
            return (iterNext != null); 
        }

        public DSAGraphNode next(){
            DSAGraphNode value;
            if (iterNext == null) {
                value = null;
            }   
            else {
                value = iterNext.getData(); 
                iterNext = iterNext.getNext(); 
            }
            return value;
        }

        public Object nextW(){
            Object weight;
            if (iterNext == null) {
                weight = null;
            }   
            else {
                weight = iterNext.getDistance(); 
            }
            return weight;
        }

        public void remove(){ 
            throw new UnsupportedOperationException("Not supported."); 
        }
    }

    public void iterateOverList(DoubleLinkList theList) {
        Iterator iter = theList.iterator();
        while (iter.hasNext()) {
            System.out.print ( "- " + ((DoubleLinkList.MyLinkedListIterator) iter).nextW() + " "+ iter.next());
        }
    }

    public DSAGraphNode find(String newValue){
        DSAGraphNode result = null;
        DoubleListNode current = head;
		if(isEmpty()){
			throw new IllegalArgumentException("Sorry, couldn't find the value. The list is empty.");
		}
		while(current != null){
			if(current.getData().getLabel().equals(newValue)){
				result = current.getData();
			}
			current = current.getNext();
		}
		return result;
	}

    public void insertFirst(DSAGraphNode newValue){
        DoubleListNode newNd = new DoubleListNode(newValue);
        if (this.isEmpty()){ //empty list
            head = newNd;
            tail = head;
        }
        else if(head == tail){ //one-item list
            tail.setPrev(newNd); 
            newNd.setNext(tail);
            head = newNd; 
        }
        else{ //multi-item list
            head.setPrev(newNd); 
            newNd.setNext(head);
            head = newNd; 
        }
    }

    public void insertLast(DSAGraphNode newValue){
        DoubleListNode newNd = new DoubleListNode(newValue);
        if (this.isEmpty()){
            tail = newNd;
            head = tail;
        }
        else{
            tail.setNext(newNd);
            newNd.setPrev(tail);
            tail = newNd;
        }
    }
 
    public DSAGraphNode peekFirst(){
        DSAGraphNode nodeValue;
        if (this.isEmpty()){
            throw new IllegalArgumentException("There is nothing on the first list, the list is empty");
        }
        else{
            nodeValue = head.getData();
        }
        return nodeValue;
    }    

    public DSAGraphNode peekLast(){
        DSAGraphNode nodeValue;
        if (this.isEmpty()){
            throw new IllegalArgumentException("There is nothing on the last list, the list is empty");
        }
        else{
            nodeValue = tail.getData();
        }
        return nodeValue;
    }

    public DSAGraphNode removeFirst(){
        DSAGraphNode nodeValue;
        if (this.isEmpty()){
            throw new IllegalArgumentException("There is nothing to delete, the list is empty");
        }
        else{
            nodeValue = head.getData();
            head = head.getNext();
        }
        return nodeValue;
    }

    public void removeSpecific(DSAGraphNode delVertex){ //removes vertex
        DoubleListNode currentNd = head;
        if (this.isEmpty()){
            throw new IllegalArgumentException("There is nothing to delete, the list is empty.");
        }
        if(currentNd.getData().equals(delVertex)){
            removeFirst();
        }
        else
        {
            while(currentNd.getNext() != null && !currentNd.getNext().getData().equals(delVertex)){
                currentNd = currentNd.getNext(); //iterate through vertices list until found or end reached
            }
            if(currentNd.getData().equals(delVertex)){ //when vertex found, delete
                delVertex = currentNd.getData();
                currentNd = currentNd.getNext();
            }
            else if(currentNd.getNext() == null){ //vertex not found when currentNd is at the end
                System.out.println("Sorry could not find the value you want to delete.");
            }
        }
        
    }

    public DSAGraphNode removeLast(){
        DSAGraphNode nodeValue = null;
        if (this.isEmpty()){
            throw new IllegalArgumentException("There is nothing to delete, the list is empty");
        }
        else if(head.getNext() == null){
            nodeValue = tail.getData();
            head = null;
            tail = null;
        }
        else{
            nodeValue = tail.getData();
            tail = tail.getPrev();
            tail.setNext(null);
        }
        return nodeValue;
    }

    public boolean isEmpty(){
        return head == null;
    }
}