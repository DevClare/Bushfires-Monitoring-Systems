/*Author: Lina Yeo 
  Student ID: 21204647 / 700043539
  University branch: Miri, Malaysia
  Purpose of code: Test harness for DSAGraph class
*/

public class UnitTestDSAGraph {
    public static void main(String args[])
    {
        boolean answer;
        DSAGraph testGp = new DSAGraph();

        System.out.println("TestHarness_DSAGraph: ");

        //add vertices into the graph
        System.out.println("\nAdding three vertices into the graph [testing addVertex(), isEmpty(), DoubleLinkList insertLast()]");
        testGp.addVertex("Z" , 23, 25, 30);
        testGp.addVertex("Y", 15, 21, 12);
        testGp.addVertex("X", 34, 26, 11);

        System.out.println("\n-> Display vertices in the graph [testing displayLocation()]: " );
        //display vertices exist in the graph
        testGp.displayLocation();

        //testing the total number of vertex exist in the graph
        answer = (testGp.getVertexCount() == 3);
        System.out.println("\nThree vertices is in the graph [testing getVertexCount()]: " + answer);

        //testing if the vertex exist in the graph
        System.out.println("Three vertices inserted and exist in the graph [testing hasVertex(), DoubleLinkList find()]: " + testGp.hasVertex("Z") + " " + testGp.hasVertex("Y") + " " + testGp.hasVertex("X"));

        //getting the information of the vertices in the graph
        System.out.println("\nGetting vertices information in the graph [testing getVertex(), getVertexInfo()]: ");
        testGp.getVertexInfo("Z");
        
        //add edges to the graph
        System.out.println("\nAdding edges to the vertices [testing addEdge()]");
        testGp.addEdge("Z", "X", 10);
        testGp.addEdge("Y", "X", 20);

        //display the adjancency list
        System.out.println("\n-> Display adjacency list in the graph [testing displayAsList()]: " );
        testGp.displayAsList();

        //test the adjacency list
        answer = (testGp.getEdgeCount() == 2);
        System.out.println("\nThe edges exist in the graph [testing getEdgeCount()]: " + answer);

        //testing if vertices are adjacent with each other
        System.out.println("The vertices are adjacent to each other [testing isAdjacent(), getAdjacent()]: " + testGp.isAdjacent("Z", "X") + " " + testGp.isAdjacent("Y", "X"));

        //testing if the vertex is removed from the graph
        System.out.println("Remove vertex in the graph [testing removeVertex(), removeEdge, DoubleLinkList removeSpecific()]");
        testGp.removeVertex("Z");
        System.out.println("Vertex is successfully removed: " + !testGp.hasVertex("Z") + "\n");
        

    }
}
        
