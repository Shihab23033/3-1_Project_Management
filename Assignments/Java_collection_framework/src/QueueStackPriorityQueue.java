import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;


public class QueueStackPriorityQueue {
    public static void main(String[] args) {

        // Queue using PriorityQueue (min-heap behavior)
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(10);
        pq.add(5);
        pq.add(20);

        System.out.println("Queue (PriorityQueue):");
        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }


        Stack<Integer> stack = new Stack<>();
        stack.add(10);
        stack.add(5);
        stack.add(20);

        System.out.println("Stack (output in reverse order):");
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}