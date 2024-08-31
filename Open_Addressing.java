import java.io.*;
import java.util.*;
/* Jacqueline Silver
this project is adapted from a class assignment in COMP 251 at McGill
builds a hashtable using open addressing method */
public class Open_Addressing {
     public int m; // number of SLOTS AVAILABLE
     public int A; // the default random number
     int w;
     int r;
     public int[] Table;

     protected Open_Addressing(int w, int seed, int A) {

         this.w = w;
         this.r = (int) (w-1)/2 +1;
         this.m = power2(r);
         if (A==-1){
            this.A = generateRandom((int) power2(w-1), (int) power2(w),seed);
         }
        else{
            this.A = A;
        }
         this.Table = new int[m];
         for (int i =0; i<m; i++) {
             Table[i] = -1;
         }
         
     }
     
                 /** Calculate 2^w*/
     public static int power2(int w) {
         return (int) Math.pow(2, w);
     }
     public static int generateRandom(int min, int max, int seed) {     
         Random generator = new Random(); 
                 if(seed>=0){
                    generator.setSeed(seed);
                 }
         int i = generator.nextInt(max-min-1);
         return i+min+1;
     }
        /**Implements the hash function g(k)*/
        public int probe(int key, int i) {
        int hk = ((this.A * key) % ((int) Math.pow(2, this.w)) >> (this.w - this.r)); //uses h(k) function
        int hash1 = (int) ((hk + i) % (Math.pow(2, this.r)));
        return hash1;
     }
     
     
     /**Inserts key k into hash table. Returns the number of collisions encountered*/
        public int insertKey(int key){
            int collisions = 0;
            int i = 0;
            while (Table[probe(key, i)] != -1 && Table[probe(key, i)] != -2){ //while slot is not empty
                i += 1; //index increasing
                collisions += 1; //number of collisions encountered increasing
                if (i == Table.length){ //if there are no more available slots
                    break;
                }
            }
            if (Table[probe(key, i)] == -1 || Table[probe(key, i)] == -2) { //assuming the index never reached the number of slots available
                Table[probe(key, i)] = key; //inserted into table
            }

            return collisions;
        }
        
        /**Sequentially inserts a list of keys into the HashTable. Outputs total number of collisions */
        public int insertKeyArray (int[] keyArray){
            int collision = 0;
            for (int key: keyArray) {
                collision += insertKey(key);
            }
            return collision;
        }
            
         /**Inserts key k into hash table. Returns the number of collisions encountered*/
        public int removeKey(int key){
            int i = 0;
            int collisions = 0;
            while (Table[probe(key, i)] != key){ //while the value isn't the key
                if (Table[probe(key, i)] == -1){ // if an initially empty (not deleted) slot is reached
                    collisions += 1;
                    break;
                }
                if (i == Table.length){ //if the end of the array is reached
                    break;
                }
                collisions += 1; //add collisions
                i += 1; //go to the next index
            }
            if (Table[probe(key, i)] == key) { //if the element is in the array
                Table[probe(key, i)] = -2; //making the slot 'empty' but distinguishable from initially empty
            }
            return collisions;
        }
}
