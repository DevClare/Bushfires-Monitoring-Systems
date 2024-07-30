/*Author: Lina Yeo 
  Student ID: 21204647 / 700043539
  University branch: Miri, Malaysia
  Purpose of code: Test Harness for class DSAHashTable and DSAHashEntry
*/

public class UnitTestDSAHashTable {
    public static void main(String[] args) {
        boolean test;

        DSAHashTable hashTable = new DSAHashTable(50);

        System.out.println("\nTestHarness_DSAHashTable: ");
        //check if the table size is in prime number, the closest prime number after 50 is 53
        System.out.println("\nTable size value in prime number [test nextPrime()] : " + (test = (hashTable.size == 53)));

        System.out.println("\nInserting two keys into the hash table(key A & B) [testing put(), getLoadFactor(), hash1(), hash2()]");
        
        //add key and values to hash table
        hashTable.put("A", 1, 2, 3);
        hashTable.put("B", 4, 5, 6);

        System.out.println("\nKey A  is inserted and exist in the hash table [testing hasKey()]: " + (test = (hashTable.hasKey("A"))));
        System.out.println("Get key A and the key's value inserted [testing get(), find()]: ");
        hashTable.get("A");

        System.out.println("\nKey B  is inserted and exist in the hash table [testing hasKey()]: " + (test = (hashTable.hasKey("B"))));
        System.out.println("Get key B and the key's value inserted [testing get(), find()]: ");
        hashTable.get("B");

        //check if the table resize after inserting value
        //1st key insert, resize = 53 / 2 = 26.5 (nextPrime = 29)
        //2nd key insert, resize = 29 / 2 = 14.5 (nextPrime = 17)
        System.out.println("\nAfter key is insert, the table resized [testing resize(), putResize()]: " + (test = (hashTable.size == 17)));


        //remove the key from the table
        hashTable.remove("A");
        System.out.println("\nRemove key A [testing remove()]");
        System.out.println("\nThe key left after remove: ");
        hashTable.display();
        System.out.println(" ");
    }
}
