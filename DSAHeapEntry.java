/*Author: Lina Yeo 
  Student ID: 21204647(Perth ID) / 700043539(Miri ID)
  University branch: Miri, Malaysia
  Purpose of code: Setter and getter for the heap (reused the code from Practical 8, modified for the needs of the assessment)
*/

public class DSAHeapEntry {

    private int priority; 
    private Object value, temp, humidity, speed;

    public DSAHeapEntry(int newPriority, Object newValue, Object newTemp, Object newHumidity, Object newSpeed){
        this.priority = newPriority;
        this.value = newValue;
        this.temp = newTemp;
        this.humidity = newHumidity;
        this.speed = newSpeed;
    }

    public int getPriority(){
        return priority;
    }
    public void setPriority(int newPriority){
        priority = newPriority;
    }

    public Object getValue(){
        return value;
    }
    public void setValue(Object newValue){
        value = newValue;
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
}
