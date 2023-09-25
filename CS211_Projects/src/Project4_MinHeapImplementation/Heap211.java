package Project4_MinHeapImplementation;


import java.util.*;

public class Heap211 {
	
	
    static public ArrayList <Integer> heap = new ArrayList<>();
    
    Heap211() {
    	/* 
    	 * we need to add zero to the heap because otherwise the index's go wrong.
    	 * If we don't add it, the formula for the child's index (index*2 & index*2+1) doesn't work because
    	 * the min-node's index is zero meaning these operations return a zero. The children also
    	 * cannot find the parent because index/2 will never return zero. To use the same equation for 
    	 * all nodes, a zero must be added to the the index zero of the ArrayList. 
    	 */
    	heap.add(0);
    }
    
    //return parent index
    int parent(int index) {
        return index/2;
    }

    //return leftChild index
    int leftChild(int index) {
        return index*2;
    }

    //return rightChild index
    int rightChild(int index) {
        return index*2+1;
    }

    //checks if index has a parent. Should only return false for min-node
    boolean hasParent(int index) {
    	if(heap.get(parent(index)) == 0) {
    		return false;
    	}else {
    		return true;
    	}
    }

    //checks if index has a leftChild. 
    boolean hasLeftChild(int index) {
    	if(leftChild(index)>heap.size()-1) {
    		return false;
    	}else {
    		return true;
    	}
    }

    //checks if index has a rightChild. 
    boolean hasRightChild(int index) {
    	if(rightChild(index)>heap.size()-1) {
    		return false;
    	}else {
    		return true;
    	}   
    }
        
    //swaps two indexes in the arraylist. 
    void swap(int a, int b) {
    	System.out.println("      Swap " + heap.get(a) + "<-->" + heap.get(b));    
        int temp = heap.get(a);
        heap.set(a, heap.get(b));
        heap.set(b,temp);
        System.out.println("         New Heap: " + printHeap());
    }
   
    //returns min value of heap
    int peekMin() {
        return heap.get(1);
    }
   
    
    //empty if heap size = 1 since 0 is added at the start
    boolean isEmpty() {
        return heap.size() == 1;
    }

    
    
    void add(int value) {
    	
    	heap.add(value);
    	
       	System.out.println("   heap: " +printHeap());
       	System.out.println("   bubble-up: start");
    	
       	//index of added value is heap.size()-1. 
       	bubbleUp(heap.size()-1);
       	
        System.out.println("   bubble-up: end");
    	System.out.println("   new heap: " +printHeap());
    	System.out.println();
    	
    }
    
    int remove() {  
    	
    	System.out.println("   heap: " +printHeap());  

    	int min = peekMin();
    	int lastIndex = heap.size()-1;
    	heap.set(1, heap.get(lastIndex));
    	heap.remove(lastIndex);
    	
   
       	System.out.println("   heap: " +printHeap());   
     	System.out.println("   bubble-down: start");
     	
     	//check if empty since bubbledown cannot run if there is only one number and it is deleted.
     	if(!isEmpty()) {
     		bubbleDown(1);
     	}
     	
        System.out.println("   bubble-down: end");
    	System.out.println("   new heap: " +printHeap());
    	
     	

        return min;
    }
    
    void bubbleUp(int index) {
		
    	int value = heap.get(index);
		//check if the value is > parent
    	//must make sure parent index doesn't equal zero since (int) 1/2 = 0.
    	if(heap.get(parent(index)) > value && parent(index) != 0) {
    		//swaps the value and parent. 
    		swap(index, parent(index));
    		//bubble up again with parent index as the new index since the value is now at the parent index
    		bubbleUp(parent(index));
    	}else {
    		return;
    	}
    }
    
    void bubbleDown(int index) {	
    	
    	//get value of index. 
    	int value = heap.get(index);
    	
    	//if there's no left child, there cannot be a right child per rules of a heap tree.
    	//Therefore it must return as it has no children and cannot bubble down anymore. 
    	
    	if(!hasLeftChild(index)) {
    		return;
    	}
    	
    	//if there is a left child and no right child then it must swap with the left child as long as 
    	//the value is greater than the left child. 
    	else if(!hasRightChild(index)){
    		if(value > heap.get(leftChild(index))) 
    		{
    			swap(index, leftChild(index));
    			//after this swap, we have to return. 
    			//this is because the we only had 1 child, meaning there will be no more children after the swap.
    			return;
    		}
    	
    	//when reaching this point, we assume we have a right and left child. 
    	}else{
    		int leftChild = heap.get(leftChild(index));
    		int rightChild = heap.get(rightChild(index));
    	   	
    		//If there are two children we swap with the smaller child 
    		//if they are equal just swap to the left child
    		//then rerun bubbleDown with the index of the smaller child.
    		 
    		
    		//conditions for left child swap
    		if(leftChild <= rightChild && value > leftChild) {
    			swap(index, leftChild(index));
    			bubbleDown(leftChild(index));
    		}
    		//conditions for right child swap. 
    		else if(leftChild > rightChild && value > rightChild) {
    			swap(index, rightChild(index));
    			bubbleDown(rightChild(index));
    		}  
    	}
    	
    }	
     
    
   
    
    //method given. 
    public String printHeap(){
    	
        StringBuilder result = new StringBuilder("["); 
        
        if (heap.size()>1) {
         result.append(heap.get(1));
        }
        
        for (int i = 2; i < heap.size(); i++){
            result.append(", ").append(heap.get(i));
        }
                     
        return result + "]";
    }
    


}
