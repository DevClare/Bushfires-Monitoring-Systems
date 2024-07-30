/*Author: Lina Yeo 
  Student ID: 21204647 / 700043539
  University branch: Miri, Malaysia
  Purpose of code: Setter and getter for the DSAGraph class 
*/

import java.util.Iterator;

public class DSAGraphNode {
        
    private String label;
    private Object temp, humidity, speed;
    private DoubleLinkList list = null;
    private boolean visit;

    public DSAGraphNode(String newLabel, Object newTemp, Object newHumidity, Object newSpeed){
        this.label = newLabel;
        this.temp = newTemp;
        this.humidity = newHumidity;
        this.speed = newSpeed;
        list = new DoubleLinkList();
    }

    public String getLabel(){
        return label;
    }
    public void setLabel(String newLabel){
        label = newLabel;
    }

    public Object getTemp(){
        return temp;
    }
    public void setTemp(Object newTemp){
        temp = newTemp;
    }

    public Object getHumidity(){
        return humidity;
    }
    public void setHumidity(Object newHumidity){
        humidity = newHumidity;
    }

    public Object getSpeed(){
        return speed;
    }
    public void setSpeed(Object newSpeed){
        speed = newSpeed;
    }

    public DoubleLinkList getAdjacentList(){
        return list;
    }
    
    public void addEdge(DSAGraphNode vertex, Object weight){
        list.insertLast(vertex); 
        list.tail.setDistance(weight);
    }

    public void setVisited(){
        visit = true;
    }

    public void clearVisited(){
        visit = false;
    }

    public boolean getVisited(){
        return visit;
    }

    public String toString(){
        return label + " ";
    }
    
    public Iterator iterator(){
        return list.iterator();
    }

    public void iterateOverAdjList(){
        System.out.print(label + " ");
        list.iterateOverList(list);
        System.out.println("");
    }

}
       
