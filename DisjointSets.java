import java.io.*;
import java.util.*;
/* Jacqueline Silver
this project is adapted from a class assignment in COMP 251 at McGill
 executes disjoint set operations */


public class DisjointSets {

    private int[] par;
    private int[] rank;

    /* contructor: creates a partition of n elements. */
    /* Each element is in a separate disjoint set */
    DisjointSets(int n) {
        if (n > 0) {
            par = new int[n];
            rank = new int[n];
            for (int i = 0; i < this.par.length; i++) {
                par[i] = i;
            }
        }
    }

    public String toString() {
        int pari, countsets = 0;
        String output = "";
        String[] setstrings = new String[this.par.length];
        /* build string for each set */
        for (int i = 0; i < this.par.length; i++) {
            pari = find(i);
            if (setstrings[pari] == null) {
                setstrings[pari] = String.valueOf(i);
                countsets += 1;
            } else {
                setstrings[pari] += "," + i;
            }
        }
        /* print strings */
        output = countsets + " set(s):\n";
        for (int i = 0; i < this.par.length; i++) {
            if (setstrings[i] != null) {
                output += i + " : " + setstrings[i] + "\n";
            }
        }
        return output;
    }

    /* find resentative of element i */
    public int find(int i) {
        if (par[i] == i) { //if i is the root
            return i;
        } else {
            par[i] = find(par[i]); //path compression
            return par[i];
        }
        /* Fill this method (The statement return 0 is here only to compile) */


    }

    /* merge sets containing elements i and j */
    public int union(int i, int j) {
        if (find(i) == find(j)) {
            return find(i);
        } else {
            if (rank[find(i)] < rank[find(j)]) { //if i has greater rank than j
                int findi = (find(i));
                par[findi] = (find(j));

                return find(j);
            } else if (rank[find(i)] > rank[find(j)]) {
                if (find(j) != find(i)) {
                    int findj = (find(j));
                    par[findj] = (find(i));
                }
                return find(i);
            } else {
                par[find(i)] = find(j);
                rank[(find(j))] += 1;
                return (find(j));
            }
        }
        /* Fill this method (The statement return 0 is here only to compile) */
    }

    public static void main(String[] args) {


        DisjointSets myset = new DisjointSets(6);
        System.out.println(myset);
        System.out.println("-> Union 2 and 3");
        myset.union(2, 3);
        System.out.println(myset);
        System.out.println("-> Union 2 and 3");
        myset.union(2, 3);
        System.out.println(myset);
        System.out.println("-> Union 2 and 1");
        myset.union(2, 1);
        System.out.println(myset);
        System.out.println("-> Union 4 and 5");
        myset.union(4, 5);
        System.out.println(myset);
        System.out.println("-> Union 3 and 1");
        myset.union(3, 1);
        System.out.println(myset);
        System.out.println("-> Union 2 and 4");
        myset.union(2, 4);
        System.out.println(myset);
    }
}