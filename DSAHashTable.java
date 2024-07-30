/*Author: Lina Yeo 
  Student ID: 21204647 / 700043539
  University branch: Miri, Malaysia
  Purpose of code: Hash Table class, Task 4 Assignment(reused the code from Practical 7)
*/ 

public class DSAHashTable{

    private int count;
    private DSAHashEntry hashEntry;
    DSAHashEntry[] hashArray;
    int size;

    //set the status of the array whether it is currently-used, empty, or previously-used
    private static final int free = 0;
    private static final int used = 1;
    private static final int prevUsed = 2;

    public DSAHashTable(int inSize){
        this.size = nextPrime(inSize);      //call method to get the next prime number of the table size 
        hashArray = new DSAHashEntry[size];
        for(int i = 0; i < size; i++){
            hashArray[i] = new DSAHashEntry();
        }
        count = 0;
    }
    
    //to get the index to insert the value in the hash table array
    private int hash1(String key)           //refer to Lecture 7 slides
    {
        int hashIdx = 0;
        for (int ii = 0; ii < key.length(); ii++) {
            hashIdx = (31 * hashIdx) + key.charAt(ii);
        }
        return hashIdx % hashArray.length; 
    } 

    //to get the additional value to add for the index, when the first index is filled
    private int hash2(String key)           
    {
        int hIdx = 0;
        for (int ii = 0; ii < key.length(); ii++) {
            hIdx = (35 * hIdx) + key.charAt(ii);
        }
        return hIdx % hashArray.length;
    } 

    //inserting value to the has table array
    public void put(String sKey, Object temp, Object humid, Object speed){
        //when less than 65% and more than 70% of the array is filled, resize the array
        if (getLoadFactor() < 0.65){    
            resize(nextPrime(size / 2));
        }
        else if (getLoadFactor() > 0.7){
            resize(nextPrime(size * 2));
        }

        int index = hash1(sKey);        //getting the index for the key
        int addhash2 = hash2(sKey);     //get additional value to be added into the index if the first index is filled
        boolean filled = false;
        DSAHashEntry insert = new DSAHashEntry(sKey, temp, humid, speed);
        //loop through the array until the value is inserted
        while(!filled){
            //when there is no value and key inside the index array
            if((hashArray[index].getState() == free) || (hashArray[index].getState() == prevUsed)) 
            {
                hashArray[index] = insert;
                hashArray[index].setState(used);
                filled = true;
                count++;
            }

            //when the key already exist inside the index array 
            else if(hashArray[index].getKey().equals(sKey)){
                filled = true;
            }

            //when the index array is filled, get the next index value to insert the value into the array
            else if(hashArray[index].getState() == used){
                index = (index + addhash2) % size;
                filled = true;
                count++;
            }
        }
    }

    //check if the key exist in the hash table array
    public boolean hasKey(String sKey){
        int idx = find(sKey);
        boolean keyExist = false;
        if (hashArray[idx] == null){
            System.out.println("Sorry couldn't find the value in the table");
        }
        else{
            keyExist = true;
        }
        return keyExist;
    }

    //get the key and the value of the key
    public void get(String sKey){
        int in = find(sKey);
        System.out.println("\nLocation: " + hashArray[in].getKey() + "\nTemperature: " + hashArray[in].getTmp() + "\nHumidity: " + hashArray[in].getHumid() + "\nWind Speed: " + hashArray[in].getSpd());
    }

    //to remove the key from the hash table
    public void remove(String key){
        int removeIdx = find(key);
        hashArray[removeIdx].setState(prevUsed);
        hashArray[removeIdx].setTmp(null);
        hashArray[removeIdx].setHumid(null);
        hashArray[removeIdx].setSpd(null);

        //when less than 65% and more than 70% of the array is filled, resize the array
        if (getLoadFactor() < 0.65){    
            resize(nextPrime(size / 2));
        }
        else if (getLoadFactor() > 0.7){
            resize(nextPrime(size * 2));
        }
    }

    //to find the key inside the hash array table
    private int find(String key){ 
        int hashIdx = hash1(key);
        int startIdx = hashIdx;
        int addIdx = hash2(key);
        boolean found = false;
        boolean notFound = false;
        while((!found) && (!notFound)){
            //if the index array is filled check if it's filled with the key we are looking for
            if(hashArray[hashIdx].getState() == used){
                if(hashArray[hashIdx].getKey().equals(key)){
                    found = true;
                }
                else{
                    //when the key are not the one we are looking for, check the next index array
                    hashIdx = (hashIdx + addIdx) % hashArray.length;
                    if(hashArray[hashIdx].getKey().equals(key)){
                        found = true;
                    }
                    else if(hashIdx == startIdx){
                        notFound = true;
                    }
                }
            }
            //when the index array never occupied before, the key does not exist in hash table
            else if(hashArray[hashIdx].getState() == free){
                notFound = true;
            } 
            //when the index array is used previously
            else if(hashArray[hashIdx].getState() == prevUsed){
                hashIdx = (hashIdx + addIdx) % hashArray.length;
                if(hashArray[hashIdx].getState() == used){
                    if(hashArray[hashIdx].getKey().equals(key)){
                            found = true;
                    }
                    else{
                        hashIdx = (hashIdx + addIdx) % hashArray.length;
                        if(hashArray[hashIdx].getKey().equals(key)){
                            found = true;
                        }
                        else if(hashIdx == startIdx){
                            notFound = true;
                        }
                    }
                }
            }
        }
        if (!found){
            throw new RuntimeException("Key '" + key + "' does not exist!");
        }
        return hashIdx;
    }

    //get the next prime number of the table size
    private int nextPrime(int startSize){
        int primeVal;
        if (startSize % 2 == 0){
            primeVal = startSize - 1;
        }
        else{
            primeVal = startSize;
        }

        boolean isPrime = false;
        do{
            primeVal = primeVal + 2;
            int ii = 3;
            isPrime = true;
            double rootVal = Math.sqrt(primeVal);
            do{
                if (primeVal % ii == 0)
                {
                    isPrime = false;
                }
                else
                {
                    ii = ii + 2;
                }
            }while (ii <= rootVal && isPrime);
        }while (!isPrime);
        return primeVal;
    }

    private void resize(int newSize)
    {
        String prevKey;
        Object prevTemp, prevHumid, prevSpeed;
        DSAHashEntry[] startArray = hashArray;
        hashArray = new DSAHashEntry[newSize];
        size = newSize;
        for (int i = 0; i < size; i++){
            hashArray[i] = new DSAHashEntry();
        }
        for (int i = 0; i < startArray.length; i++){
            if (startArray[i].getState() == used){
                prevKey = startArray[i].getKey();
                prevTemp = startArray[i].getTmp();
                prevHumid = startArray[i].getHumid();
                prevSpeed = startArray[i].getSpd();
                putResize(prevKey, prevTemp, prevHumid, prevSpeed);
            } 
        }
    }

    //to insert back he key and keys' value when the hash table is resize
    private void putResize(String sKey, Object temp, Object humid, Object speed){
        int index = hash1(sKey);
        int addhash2 = hash2(sKey);
        boolean filled = false;
        DSAHashEntry insert = new DSAHashEntry(sKey, temp, humid, speed);
        while(!filled){
            if((hashArray[index].getState() == free) || (hashArray[index].getState() == prevUsed)){
                hashArray[index] = insert;
                hashArray[index].setState(used);
                filled = true;
                count ++;
            }
            else if(hashArray[index].getKey().equals(sKey)){
                filled = true;
            }
            else if(hashArray[index].getState() == used){
                index = (index + addhash2) % size;
                hashArray[index] = insert;
                hashArray[index].setState(used);
                filled = true;
                count ++;
            }
        }
    }

    //display the index array that is filled
    public void display(){
        for(int i = 0; i < size; i++){
            if(hashArray[i].getState() == used){
                System.out.println("\nLocation: " + hashArray[i].getKey() + "\nTemperature: " + hashArray[i].getTmp() + "\nHumidity: " + hashArray[i].getHumid() + "\nWind Speed: " + hashArray[i].getSpd());   
            }
        }
    }

    //get the amount of hash table filled 
    private double getLoadFactor(){
        double calculate = 0;
        double loadFactor;
        for (int i = 0; i < hashArray.length; i++){
            if (hashArray[i].getState() == used){
                calculate ++;
            }
        }
        loadFactor = calculate / size;
        return loadFactor;
    }
}