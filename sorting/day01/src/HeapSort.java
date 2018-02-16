import java.util.Arrays;

public class HeapSort extends SortAlgorithm {
    int size;
    int[] heap;

    private int parent(int i) {
        return (i-1) / 2;
    }

    private int leftChild(int i) {
        return 2*i + 1;
    }

    private int rightChild(int i) {
        return 2 * (i + 1);
    }

    // Check children, and swap with larger child if necessary.
    // Corrects the position of element indexed i by sinking it.
    // Use either recursion or a loop to then sink the child
    public void sink(int i) {
        // if A[i] >= bothchildren (if they exist);
            // stop
        // child index = index of largest child
        //swap(A, i, index)
        //wink(A, index)
        boolean leftChildExists = false;
        boolean rightChildExists = false;
        if(leftChild(i) >= 0 && leftChild(i) < size){
            leftChildExists = true;
        }
        if(rightChild(i) >= 0 && rightChild(i) < size){
            rightChildExists = true;
        }

        if(!leftChildExists && !rightChildExists){
            return;
        }
        if(leftChildExists){
            if( heap[i] >= heap[leftChild(i)]){
                if(rightChildExists){
                    if(heap[i] >= heap[rightChild(i)]){
                        return;
                    }
                }else{
                    return; // if there is no right child but the left is higher
                }
            }
        }
        int childIndex = 0;
        if(leftChildExists && !rightChildExists || !leftChildExists && rightChildExists){
            if(leftChildExists){
                childIndex = leftChild(i);
                swap(i, childIndex);
                sink(childIndex);
            }else{
                childIndex = rightChild(i);
                swap(i, childIndex);
                sink(childIndex);
            }
        }else{
            if(heap[leftChild(i)]>heap[rightChild(i)]){
                childIndex = leftChild(i);
            }else{
                childIndex = rightChild(i);
            }
            swap(i, childIndex);
            sink(childIndex);
        }
    }

    // Given the array, build a heap by correcting every non-leaf's position, starting from the bottom, then
    // progressing upward
    public void heapify(int[] array) {
        this.heap = array;
        this.size = array.length;

        for (int i=this.size / 2 - 1; i>=0; i--) {
            sink(i);
        }
    }

    public void swap(int i, int index){
        int holder = heap[i];
        heap[i] = heap[index];
        heap[index] = holder;
    }

    /**
     * Best-case runtime: O(N log N), because it goes through everything still
     * Worst-case runtime: O(N log N)
     * Average-case runtime: O(N log N)
     *
     * Space-complexity: O(1), all in place!
     */
    @Override
    public int[] sort(int[] array) {
        heapify(array);

        for (int i=size-1; i>0; i--) {
            int top = heap[0];
            heap[0] = heap[i];
            heap[i] = top;
            size--;
            sink(0);
        }

        return heap;
    }
}
