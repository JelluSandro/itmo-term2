package queue;
/*

Model:
    [a1, a2, ...an]
    n -- размер дека
	
Inv:
    n >= 0
    forall i = 1..n: a[i] != null
	
Immutable:
    n = n' && forall i a[i] == a'[i]

*/
import java.util.Arrays;

import static java.lang.System.arraycopy;

public class ArrayQueueModule {

    private static int size;
    private static int head;
    private static Object[] elements = new Object[5];

    // Pred: element != null && not empty && integer
    // Post: a[0] = element && n = n' + 1 && forall i = 1..n a[i] == a'[i]
    public static void push(Object element) {
        assert element != null;
        if (size == elements.length) {
            resize();
        }
        size++;
        head--;
        if (head < 0) {
            head = elements.length - 1;
        }
        elements[head] = element;
    }

    // Pred: n > 0
    // Post: Immutable && R = a[n]
    public static Object peek() {
        assert size > 0;
        return elements[(head + size - 1) % elements.length];
    }

    // Pred: n > 0
    // Post: a[n'] delete && n = n' - 1 && forall i = 1..n' - 1 a[i] == a'[i] && R = a'[n']
    public static Object remove() {
        assert size > 0;
        int tail = (head + size - 1) % elements.length;
        Object x = elements[tail];
        elements[tail] = null;
        size--;
        return x;
    }

    // Pred: true
    // Post: R = String([a[0], a[1], ... a[n]]) && Immutable
    public static String toStr() {
        return Arrays.toString(toArray());
    }

    // Pred: element != null && not empty && integer
    // Post: element in tail && n = n' + 1 && R[n] = element && forall i = 1..n' a[i] == a'[i]
    public static void enqueue(Object element) {
        assert element != null;
        if (size == elements.length) {
            resize();
        }
        size++;
        elements[(head + size - 1) % elements.length] = element;
    }

    // Pred: true
    // Post: R = Array.of{a[0], a[1], ... a[n]} && Immutable
    public static Object[] toArray() {
        Object[] temporary = new Object[size];
        copy(temporary);
        return temporary;
    }

    private static void resize() {
        Object[] temporary = new Object[elements.length * 2 + 1];
        copy(temporary);
        head = 0;
        elements = temporary;
    }

    private static void copy(Object[] temporary) {
        if (head + size < elements.length) {
            arraycopy(elements, head, temporary, 0, size);
        }   else {
            arraycopy(elements, head, temporary, 0, elements.length - head);
            arraycopy(elements, 0, temporary, elements.length - head, (head + size) % elements.length);
        }
    }

    // Pred: true
    // Post: n = 0
    public static void clear() {
        Arrays.fill(elements, null);
        head = 0;
        size = 0;
    }

    // Pred: n > 0
    // Post: a[0] delete && n = n' - 1 && forall i = 2..n' a[i] == a'[i] && R = a'[0]
    public static Object dequeue() {
        assert size > 0;
        Object value = element();
        size--;
        elements[head] = null;
        head = (head + 1) % elements.length;
        return value;
    }

    // Pred: size > 0
    // Post: R = a[0] && Immutable
    public static Object element() {
        assert size > 0;

        return elements[head];
    }

    // Pred: true
    // Post: R = n && Immutable
    public static int size() {
        return size;
    }
    // Pred: true
    // Post: if n == 0 then R = true else R = false && Immutable
    public static boolean isEmpty() {
        return size == 0;
    }
}
