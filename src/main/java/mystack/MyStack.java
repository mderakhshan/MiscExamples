package mystack;

import java.util.*;

/**
 * Created by Mir on 26/10/2016.
 */
public class MyStack<E>  {

    class Node {
        E element;
        Node next;
    }
    Node top;

    public MyStack () {
        top = null;
    }

    public E peek() {
        return (top == null) ? null : top.element;
    }

    public void push(E e) {
        Node elem = new Node();
        elem.next = top;
        top = elem;
    }

    public E pop() {
        if (top == null) {
            return null;
        }
        Node currentTop = top;
        top = top.next;
        return currentTop.element;
    }
}
