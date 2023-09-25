package Project4_MinHeapImplementation;

import java.util.*;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;

public class MyHeap {
	final static int HOW_MANY_TESTs = 20;
	   
	   public static void main(String[] args) {
		   
		 
		   System.out.println("Project 4. MinHeap (Winter 2023)");
		   Random rnd = new Random();
		   Heap211 minHeap = new Heap211();	        
	      
	       for (int test = 1; test <= HOW_MANY_TESTs; test++) {
	        	        	
	        	//generate an random number to decide the action is 'add' or 'remove'
	        	int action = rnd.nextInt(2);
	        	  
	            // if the action is 1, we add 'add' {
	        	if(action == 1) {
	        	    // generate a node number using random number between 0-49 
	        	       int node = rnd.nextInt(50);
	        	       
	  	               System.out.println("Action " + test + ": Add " + node);   

	                // add node to the heap                              	                  
	  	             minHeap.add(node);
	  	             
	  	        } 
	          
	        
	            // if the action is 0, we 'remove' {
	  	        if(action == 0) {
	                if (minHeap.isEmpty()) {  
	                	//if the heap is empty, skip this action, do not count this action
	                    test = test - 1;
	                }else{
	                	
	   		           System.out.println("Action " + test+": Remove min: " + minHeap.peekMin());

	   		           // remove min-node                   
	   		           int removedNode = minHeap.remove();
	   		           System.out.println("   Removed: " + removedNode);  
	                 }
	            }
	        }
	        
	    }
}
