/*Author: Lina Yeo 
  Student ID: 21204647 / 700043539
  University branch: Miri, Malaysia
  Purpose of code: Graph class, Task 1 Assignment
*/

import java.util.*;
import java.io.Serializable;

public class DSAGraph{

    private DoubleLinkList vertices;
    private int verticesNum, edgesNum;

    public DSAGraph(){
        vertices = new DoubleLinkList();
    }

    public void addVertex(String label, Object temp, Object humidity, Object speed){
        DSAGraphNode newNd = new DSAGraphNode(label, temp, humidity, speed);
        if (isEmpty()){     //when graph is empty
            vertices.insertLast(newNd);
        }
        else if (!hasVertex(label)){ //when vertex does not exist in the grpah
            vertices.insertLast(newNd);
        }
        else {              //when vertex already exist in the graph
            DSAGraphNode existNode = vertices.find(label); 
            existNode.setTemp(temp);
            existNode.setHumidity(humidity);
            existNode.setSpeed(speed);
        }
        verticesNum++;
    }

    public void addEdge(String src, String dst, Object weight){
        DSAGraphNode srcNode = vertices.find(src); 
        DSAGraphNode dstNode = vertices.find(dst);
        if(!isAdjacent(src, dst)){
            srcNode.addEdge(dstNode, weight); //adding edge to the vertex
            dstNode.addEdge(srcNode, weight);
        }
        edgesNum ++;
    }
    
    /*public String breadthFirstSearch(String label){
        //DSAQueue T = new DSAQueue();
        DSAQueue Q = new DSAQueue();
        DSAGraphNode T;
        DSAGraphNode vertex = vertices.find(label);
        Q.enqueue(vertex);
        boolean visited = false;
        while(!Q.isEmpty()){
            Q.state();
            T = Q.dequeue();
            Iterator<Integer> i = vertices.iterator();
            while (i.hasNext()) {
                DSAGraphNode n = i.next();
                if (!visited) {
                    visited = true;
                    Q.enqueue(n);
                }
            }
            Mark all vertices new and set T = { }
            Mark the start vertex v = old
            insert (Q, v) // Q is a queue
            while Q is nonempty do
            v = dequeue (Q)
            for each vertex w in L[v] marked new do
            T = T ∪ {v,w}
            mark w = old
            insert (Q,w)
    }*/
//  }    
    //}

    //method to check if the vertex exist in the graph
    public boolean hasVertex(String label){
        return vertices.find(label) != null;
    }

    //method to get the number of vertices in the graph
    public int getVertexCount(){
        return verticesNum;
    }

    //method to get the edge number added to the vertix
    public int getEdgeCount(){
        return edgesNum;
    }

    //method to obtain the vertex in the graph
    public DSAGraphNode getVertex(String label){
        return vertices.find(label);
    } 

    //method to get the information of the vertex
    public void getVertexInfo(String findLabel){
        DoubleListNode initialV = vertices.head;
        DSAGraphNode currNode;
        if (!hasVertex(findLabel))
        {
            System.out.println("Cannot find the location.");
        }
        else
        {
            while (initialV.getNext() != null)
            {
                if (initialV.getData().getLabel().equals(findLabel)){
                    currNode = initialV.getData();
                    System.out.println("\nLocation: " + currNode.getLabel());
                    System.out.println("Temperature: " + currNode.getTemp());
                    System.out.println("Air humidity: " + currNode.getHumidity());
                    System.out.println("Wind speed: "  + currNode.getSpeed());
                    System.out.println(" ");
                }
                initialV = initialV.getNext();
            }
        }
    }
    
    //remove vertex in the graph
    public void removeVertex(String delLabel){
        removeEdge(delLabel);   //remove the vertex from the adjacency list 
        vertices.removeSpecific(getVertex(delLabel));
    }

    //remove the vertices in the adjacency list
    public void removeEdge(String delEdge){
        DoubleListNode curVert = vertices.head;
        while (curVert.getNext() != null) //looping until the end of the graph
        {
            String curVertLoc = curVert.getData().getLabel();   
            if (isAdjacent(curVertLoc, delEdge))    //checking if the vertices is adjacent in that specific lines
            {
                DoubleLinkList adjList = getAdjacent(curVertLoc);
                adjList.removeSpecific(getVertex(delEdge));
            }
            curVert = curVert.getNext();
        }
    }

    public DoubleLinkList getAdjacent(String label){
        return getVertex(label).getAdjacentList();
    }

    //checking if the vertices are adjacent to each other
    public boolean isAdjacent(String label1, String label2){
        boolean isAdjacent = false;

        //for when the graph is empty
        if(getAdjacent(label1).isEmpty() || getAdjacent(label2).isEmpty()){
            isAdjacent = false;
        }
        //checking the adjacency list of the vertex
        else if(getAdjacent(label1).find(label2) == null){
            isAdjacent = false;
        }
        else if(getAdjacent(label2).find(label1) == null){
            isAdjacent = false;
        }
        else{
            isAdjacent = true;
        }
        return isAdjacent;
    }

    //display label and the information inside the graph
    public void displayLocation(){
        Iterator <DSAGraphNode> iter = vertices.iterator();
        DSAGraphNode currNode;
        System.out.println ("\nLocation in the system:");
        while (iter.hasNext()){
            currNode = iter.next();
            System.out.println("Location: " + currNode.getLabel());
            System.out.println("Temperature: " + currNode.getTemp());
            System.out.println("Air humidity: " + currNode.getHumidity());
            System.out.println("Wind speed: "  + currNode.getSpeed());
            System.out.println(" ");
        }
    }

    //display the adjacency list of the vertex in the graph
    public void displayAsList(){
        Iterator <DSAGraphNode> iter = vertices.iterator();
        DSAGraphNode currNode;
        System.out.println ("\nBelow is location in Adjacency List:");
        while (iter.hasNext()){
            currNode = iter.next();
            currNode.iterateOverAdjList();
        }
    }

    //checking if the DSAGraph is empty or not
    public boolean isEmpty(){
        return vertices.isEmpty();
    }
}

