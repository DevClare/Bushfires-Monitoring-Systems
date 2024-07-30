/*Author: Lina Yeo 
  Student ID: 21204647 / 700043539
  University branch: Miri, Malaysia
  Purpose of code: Menu selection for Assessment
*/

import java.util.*;
import java.io.*;

public class Menu {
    public static void main(String[] args) {
        DSAHashTable hashTable = new DSAHashTable(100);
        hashTable = hashFile("UAVdata.txt");

        DSAHeap objectArray = heapFile("UAVdata.txt");

        DSAGraph graphDistance = new DSAGraph();
        DSAGraph gp = new DSAGraph();
        graphDistance = readEdge("location.txt");
        gp = readFile("UAVdata.txt");

        Scanner sc = new Scanner(System.in);
        int choice;
        try
        {
            do
            {
                System.out.println("\nWelcome to Bushfires Monitor. What would you like to do ?\n");
                System.out.println("1.Display the location in the program(using graph)");
                System.out.println("2.Display the location in the program(using hash table)");
                System.out.println("3.Display distance between each location");
                System.out.println("4.Insert location into the program");
                System.out.println("5.Delete location from the program");
                System.out.println("6.Search for location in the program(using graph)");
                System.out.println("7.Search for location in the program(using hash table)");
                System.out.println("8.Location with the highest risk of bushfires and need attention(from scale 1 - 9)");
                System.out.println("9.Exit the program");
                System.out.println("\nYour option:");
                choice = sc.nextInt();   
                switch(choice){
                    case 1:
                    {
                        gp.displayLocation();
                        break;
                    }
                    case 2:
                    {
                        hashTable.display();
                        break;
                    }
                    case 3:
                    {   
                        graphDistance.displayAsList();
                        break;
                    }
                    case 4:
                    {
                        userInput(gp);
                        break;
                    }
                    case 5:
                    {
                        deleteLocation(gp, graphDistance);
                        break;
                    }
                    case 6:
                    {
                        findLocationGraph(gp);
                        break;
                    }
                    case 7:
                    {
                        findLocationHash(hashTable);
                        break;
                    }
                    case 8:
                    {
                        objectArray.heapSort();
                        objectArray.displayLastIdx();
                        break;
                    }
                    case 9:
                    {
                        System.out.println("\nThank you for using Bushfires Monitor Program. Have a nice day. Good bye.\n");
                        break;
                    }
                    default:
                    {
                        System.out.println ("You have entered invalid option. Please enter between 1-9 only.\n");  
                        break;
                    }
                }
            } while (choice != 9);
        }
        catch (InputMismatchException error) //catch error and display error message when user input invalid data type of variable 
        {
            System.out.println ("ERROR!! You have entered invalid option. Please enter integer only.");
            System.out.println ("The error: " + error);
        }
    }

    //read file for graph
    public static DSAGraph readFile(String fileName) 
    {
        DSAGraph graphNode = new DSAGraph();
        //Variables for file reading
        FileInputStream fileStream = null;
        InputStreamReader isr;
        BufferedReader bufRdr;
        //variables for string/file manipulation
        String line = null;
        int lineNum = 0;
        String[] splitData;
        try
        {
            fileStream   = new FileInputStream(fileName);
            isr          = new InputStreamReader(fileStream);
            bufRdr       = new BufferedReader(isr);
            lineNum = 0;
            line         = bufRdr.readLine();
            while (line != null)
            {
                try
                {
                    splitData = line.split("\\s+"); 
                    String vertex = splitData[0];
                    String temperature = splitData[1];
                    String humidity = splitData[2]; 
                    String wspeed = splitData[3]; 
                    graphNode.addVertex(vertex, temperature, humidity, wspeed);
                    lineNum += 1;
                }
                catch (IllegalArgumentException ex)
                {
                    lineNum += 0;
                }
                line = bufRdr.readLine();
            }
            fileStream.close();
        }
        catch (FileNotFoundException e) //catch the error if file not found
        {
                System.out.println ("Error: Couldn't found the file " + e.getMessage());
        }
        catch (IOException ex)
        {
                if (fileStream != null)
                {
                        try
                        {
                                fileStream.close();
                        }
                        catch (Exception e)
                        { }
                }
                System.out.println("File IO error: " + ex.getMessage());
        }
        return graphNode;
    }

    //to read  the location.txt file
    public static DSAGraph readEdge(String fileName) 
    {
        DSAGraph dsaGraph = new DSAGraph();
        // variables for file reading
        FileInputStream fileStream = null;
        InputStreamReader isr;
        BufferedReader bufRdr;
        // variables for string/file manipulation
        String line = null;
        int lineNum = 0;
        String[] splitData;
        
        try{
            fileStream   = new FileInputStream(fileName);
            isr          = new InputStreamReader(fileStream);
            bufRdr       = new BufferedReader(isr);
            lineNum = 0;
            bufRdr.readLine(); //to read and skip the first line 
            line         = bufRdr.readLine();
            while (line != null){
                try
                {
                    splitData = line.split("\\s+"); 
                    String vertex1 = splitData[0];
                    String vertex2 = splitData[1];
                    String weight = splitData[2]; 

                    if(dsaGraph.isEmpty()){
                        dsaGraph.addVertex(vertex1, null, null, null);
                        dsaGraph.addVertex(vertex2, null, null, null);
                        dsaGraph.addEdge(vertex1, vertex2, weight);
                    }
                    else if(!dsaGraph.hasVertex(vertex1)){
                        dsaGraph.addVertex(vertex1,null, null, null);
                        dsaGraph.addEdge(vertex1, vertex2, weight);
                    }
                    else if(!dsaGraph.hasVertex(vertex2)){
                        dsaGraph.addVertex(vertex2, null, null, null);
                        dsaGraph.addEdge(vertex1, vertex2, weight);
                    }
                    else{
                        dsaGraph.addEdge(vertex1, vertex2, weight);
                    }
                    
                    lineNum += 1;
                }
                catch (IllegalArgumentException ex)
                {
                        lineNum += 0;
                }
                line = bufRdr.readLine();
            }
            fileStream.close();
        }
        catch (FileNotFoundException e) //catch the error if file not found
        {
                System.out.println ("Error: Couldn't found the file " + e.getMessage());
        }
        catch (IOException ex)
        {
                if (fileStream != null)
                {
                        try
                        {
                                fileStream.close();
                        }
                        catch (Exception e)
                        { }
                }
                System.out.println("File IO error: " + ex.getMessage());
        }
        return dsaGraph;
    }

    //method to receive user input to add new location
    public static void userInput(DSAGraph gph)
    {
        Scanner sc = new Scanner(System.in);
        String label;
        int tmp, humid, speed;
        try
        {
            do
            {
                System.out.println("\nPlease enter the name of the location: ");
                label = sc.nextLine();
                if (gph.getVertex(label) != null)
                {
                    System.out.println("The location already exist in the system. Please enter new location.");
                }
            } while(gph.getVertex(label) != null); //keep on looping when location already exist is insert

            do
            {
                System.out.println("\nPlease enter the temperature of the location(25 - 48 degrees Celsius): ");
                tmp = sc.nextInt();
                if (tmp < 25 || tmp > 48)
                {
                    System.out.println("You have enter invalid value for the temperature. Please enter between 25 - 48 degrees Celsius only");
                }
            } while(tmp < 25 || tmp > 48); //keep on looping when temperature insert is out or less of the limit

            do
            {
                System.out.println("\nPlease enter the humidity of the location(15 - 60%): ");
                humid = sc.nextInt();
                if (humid < 15 || humid > 60){
                    System.out.println("You have enter invalid value for the humidity. Please enter between 15-60% only");
                }
            } while(humid < 15 || humid > 60); //keep on looping when humidity insert is out or less of the limit

            do
            {
                System.out.println("\nPlease enter the wind speed of the location(30 - 100km/h): ");
                speed = sc.nextInt();
                if (speed < 30 || speed > 100){
                    System.out.println("You have enter invalid value for the wind speed. Please enter between 30-100 km/h only");
                }
            } while(speed < 30 || speed > 100); //keep on looping when wind speed insert is out or less of the limit

            gph.addVertex(label, tmp, humid, speed);
            System.out.println("\nThe location have been insert into the program");
        }
        catch(InputMismatchException e) //catch error and display error message when user input invalid data type of variable 
        { 
            System.out.println("\nYou have enter invalid value.\nERROR!!!" + e + "\n");
        }
    }

    //find the location in the graph
    public static void findLocationGraph(DSAGraph gp)
    {
        String findLab;
        Scanner sc = new Scanner(System.in);
        try
        {
            System.out.println("\nPlease enter the location you would like to search: ");
            findLab = sc.nextLine();
            if (gp.getVertex(findLab) != null) //check if the location exist in the graph
            {
                gp.getVertexInfo(findLab);
            }
            else //when location does not exist in the graph
            {
                System.out.println("\nSorry, unable to find the location in the system. Please make sure you have enter the correct location.");
            }
        }
        catch(InputMismatchException err) //catch error and display error message when user input invalid data type of variable 
        { 
            System.out.println("ERROR! " + err);
        }
    }

    //method to delete the location in the graph
    public static void deleteLocation(DSAGraph graphVertex, DSAGraph graphEdge)
    {
        Scanner sc = new Scanner(System.in);
        String delLabel;
        try
        {
            System.out.println("\nPlease enter the location you would like to delete: ");
            delLabel = sc.nextLine();
            if (graphVertex.getVertex(delLabel) != null){ //check if the location exist in the graph
                graphVertex.removeVertex(delLabel);
                graphEdge.removeVertex(delLabel);
                System.out.println("The location has been deleted.");
            }
            else {
                System.out.println("\nSorry, unable to find the location in the system. Please make sure you have enter the correct location.");
            }
        }
        catch(InputMismatchException err) //catch error and display error message when user input invalid data type of variable 
        { 
            System.out.println("You have enter invalid value. ERROR! " + err);
        }
    }
 
    //read file for the heap 
    public static DSAHeap heapFile(String fileName)
    {
        //Variables for file reading
        FileInputStream fileStream = null;
        InputStreamReader isr;
        BufferedReader bufRdr;
        //variables for string/file manipulation
        String line = null;
        int lineNum = 0;
        String[] splitData;
        int fileLine = countLine(fileName);
        DSAHeap heapArray = new DSAHeap(fileLine);
        try
        {
            fileStream   = new FileInputStream(fileName);
            isr          = new InputStreamReader(fileStream);
            bufRdr       = new BufferedReader(isr);
            lineNum = 0;
            line         = bufRdr.readLine();
            while (line != null)
            {
                try
                {
                    splitData = line.split("\\s+"); 
                    String location = splitData[0];
                    int temperature = Integer.parseInt(splitData[1]);
                    int humidity = Integer.parseInt(splitData[2]); 
                    int wspeed = Integer.parseInt(splitData[3]);
                    int score = 0;

                    // set score for the temperature in each location 
                    if (temperature <= 32 && temperature >= 25){
                            score += 1;
                    }
                    else if (temperature <= 40 && temperature >= 33){
                            score += 2;
                    }
                    else if (temperature > 40){
                            score += 3;
                    }

                    // set score for the air humidity in each location
                    if (humidity > 50){
                            score += 1;
                    }
                    else if (humidity >= 31 && humidity <= 50){
                            score += 2;
                    }
                    else if (humidity < 30){
                            score += 3;
                    }

                    // set score for the wind speed in each location
                    if (wspeed < 40){
                            score += 1;
                    }
                    else if (wspeed >= 41 && wspeed <= 55){
                            score += 2;
                    }
                    else if (wspeed < 55){
                            score += 3;
                    }
                    heapArray.add(score, location, temperature, humidity, wspeed);
                }
                catch (IllegalArgumentException ex)
                {
                    lineNum += 0;
                }
                line = bufRdr.readLine();
            }
        fileStream.close();
        }
        catch (FileNotFoundException e) //catch the error if file not found
        {
            System.out.println ("Error: Couldn't found the file " + e.getMessage());
        }
        catch (IOException ex)
        {
            if (fileStream != null)
            {
                try
                {
                    fileStream.close();
                }
                catch (Exception e)
                { }
            }
            System.out.println("File IO error: " + ex.getMessage());
        }
        return heapArray;
    }

    //read file for the hash table
    public static DSAHashTable hashFile(String fileName)
    {
        //Variables for file reading
        FileInputStream fileStream = null;
        InputStreamReader isr;
        BufferedReader bufRdr;
        //variables for string/file manipulation
        String line = null;
        int lineNum = 0;
        String[] splitData;
        int fileLine = countLine(fileName);
        //Some objects
        DSAHashTable array = new DSAHashTable(fileLine);
        try
        {
            fileStream   = new FileInputStream(fileName);
            isr          = new InputStreamReader(fileStream);
            bufRdr       = new BufferedReader(isr);
            lineNum = 0;
            line         = bufRdr.readLine();
            while (line != null)
            {
                try
                {
                    splitData = line.split("\\s+"); 
                    String vertex = splitData[0];
                    String temperature = splitData[1];
                    String humidity = splitData[2]; 
                    String speed = splitData[3]; 
                    array.put(vertex, temperature, humidity, speed);
                    lineNum += 1;
                }
                catch (IllegalArgumentException ex)
                {
                    lineNum += 0;
                }
                line = bufRdr.readLine();
            }
            fileStream.close();
        }
        catch (FileNotFoundException e) //catch the error if file not found
        {
            System.out.println ("Error: Couldn't found the file " + e.getMessage());
        }
        catch (IOException ex)
        {
            if (fileStream != null)
            {
                    try
                    {
                            fileStream.close();
                    }
                    catch (Exception e)
                    { }
            }
            System.out.println("File IO error: " + ex.getMessage());
        }
        return array;
    }
    
    //find location in the hash table
    public static void findLocationHash(DSAHashTable hash)
    {
        String findLabel;
        Scanner sc = new Scanner(System.in);
        try
        {
            System.out.println("\nPlease enter the location you would like to search: ");
            findLabel = sc.nextLine();
            if (hash.hasKey(findLabel) == true){ //check if the key or location exist in the hash table
                hash.get(findLabel);
            }
            else {
                System.out.println("The Location does not exist in the system.");
            }
            
        }
        catch(InputMismatchException err) //catch error and display error message when user input invalid data type of variable 
        {
            System.out.println("ERROR! " + err);
        }
    }

    //method to count the number of line of file
    public static int countLine(String infileName) 
    {
            FileInputStream fileStream = null;
            InputStreamReader isr;
            BufferedReader bufRdr;
            String line = null;
            int lineNo = 0;
            String[] splitData;
            DSAHashEntry nodeCheck;
            try
            {
                    fileStream = new FileInputStream(infileName);
                    isr        = new InputStreamReader(fileStream);
                    bufRdr     = new BufferedReader(isr);
                    line       = bufRdr.readLine();
                    while (line != null)
                    {
                            try
                            {
                                    splitData = line.split("\\s+"); 
                                    //Alternate Constructor put inside try-block for skipping invalid line, got error jump to next line
                                    nodeCheck = new DSAHashEntry(splitData[0], splitData[1], splitData[2], splitData[3]); 
                                    lineNo += 1;
                            }
                            catch (IllegalArgumentException ex)
                            { }
                            line = bufRdr.readLine();
                    }
                    fileStream.close();
            }
            catch (FileNotFoundException error)
            {
                    System.out.println ("Error: Couldn't find the file " + error.getMessage());
            }
            catch (IOException ex)
            {
                    if (fileStream != null)
                    {
                            try
                            {
                                    fileStream.close();
                            }
                            catch (Exception e)
                            { }
                    }
                    System.out.println("File IO error: " + ex.getMessage());
            }
            return lineNo;
    }
}
