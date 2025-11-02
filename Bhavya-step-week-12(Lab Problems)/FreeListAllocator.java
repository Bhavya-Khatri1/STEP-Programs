import java.util.*;

class FreeListAllocator {
    // Node for linked list
    private static class Node {
        int start, size;
        Node next;
        Node(int start, int size) {
            this.start = start;
            this.size = size;
        }
    }

    private Node head; // start of free list

    // Constructor - initialize with one big free block
    public FreeListAllocator(int heapSize) {
        head = new Node(0, heapSize);
    }

    // Allocate using first-fit strategy
    public int malloc(int size) {
        Node prev = null;
        Node curr = head;

        while (curr != null) {
            if (curr.size >= size) {
                int allocStart = curr.start;
                curr.start += size;
                curr.size -= size;

                // If block is fully used, remove it
                if (curr.size == 0) {
                    if (prev == null) head = curr.next;
                    else prev.next = curr.next;
                }
                return allocStart;
            }
            prev = curr;
            curr = curr.next;
        }

        return -1; // No block found
    }

    // Free a block and merge with adjacent ones if possible
    public void free(int ptr, int size) {
        Node newNode = new Node(ptr, size);
        Node prev = null;
        Node curr = head;

        // Insert in sorted order
        while (curr != null && curr.start < ptr) {
            prev = curr;
            curr = curr.next;
        }

        newNode.next = curr;
        if (prev == null) head = newNode;
        else prev.next = newNode;

        // Merge adjacent free blocks
        merge();
    }

    // Merge consecutive blocks
    private void merge() {
        Node curr = head;
        while (curr != null && curr.next != null) {
            if (curr.start + curr.size == curr.next.start) {
                curr.size += curr.next.size;
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
    }

    // Return free list as [start, size]
    public List<int[]> freeList() {
        List<int[]> list = new ArrayList<>();
        Node curr = head;
        while (curr != null) {
            list.add(new int[]{curr.start, curr.size});
            curr = curr.next;
        }
        return list;
    }

    // Display free list
    public void displayFreeList() {
        System.out.print("Free list: ");
        for (int[] block : freeList()) {
            System.out.print("[" + block[0] + "," + block[1] + "] ");
        }
        System.out.println();
    }

    // Demo run
    public static void main(String[] args) {
        FreeListAllocator allocator = new FreeListAllocator(20);

        System.out.println("malloc(8) → " + allocator.malloc(8));
        allocator.displayFreeList();

        System.out.println("malloc(4) → " + allocator.malloc(4));
        allocator.displayFreeList();

        allocator.free(0, 8);
        System.out.println("free(0,8)");
        allocator.displayFreeList();

        System.out.println("malloc(6) → " + allocator.malloc(6));
        allocator.displayFreeList();

        allocator.free(8, 4);
        System.out.println("free(8,4)");
        allocator.displayFreeList();

        allocator.free(12, 8);
        System.out.println("free(12,8)");
        allocator.displayFreeList();
    }
}