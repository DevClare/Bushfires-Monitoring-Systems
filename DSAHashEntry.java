/*Author: Lina Yeo 
  Student ID: 21204647 / 700043539
  University branch: Miri, Malaysia
  Purpose of code: Getter and Setter for DSAHashTable class(reused the code from Practical 7)
*/

public class DSAHashEntry{

    private String keys;
    private Object tmp, humid, spd;
    private int state;

    public DSAHashEntry(){
        keys = " ";
        tmp = null;
        humid = null;
        spd = null;
        this.state = 0;
    }

    public DSAHashEntry(String newKey, Object newTmp, Object newHumid, Object newSpeed){
        this.keys = newKey;
        this.tmp = newTmp;
        this.humid = newHumid;
        this.spd = newSpeed;
        this.state = 1;
    }

    public String getKey(){
        return keys;
    }
    public void setKey(String newKey){
        keys = newKey;
    }

    public Object getTmp(){
        return tmp;
    }
    public void setTmp(Object newTmp){
        tmp = newTmp;
    }

    public Object getHumid(){
        return humid;
    }
    public void setHumid(Object newHumid){
        humid = newHumid;
    }

    public Object getSpd(){
        return spd;
    }
    public void setSpd(Object newSpd){
        spd = newSpd;
    }

    public int getState(){
        return state;
    }
    public void setState(int currState){
        state = currState;
    }
}
