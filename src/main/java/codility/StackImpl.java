package codility;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by Mir on 28/10/2016.
 */
public class StackImpl {

    public static void main (String[] args) {
        Deque<String> stack = new ArrayDeque<String>();
        stack.push ("Hello");
        stack.push("there");
        stack.push("how are you");
        stack.push("Alex");

        while (!stack.isEmpty()) {
            System.out.println(stack.pop() + " ");
        }
    }
}
